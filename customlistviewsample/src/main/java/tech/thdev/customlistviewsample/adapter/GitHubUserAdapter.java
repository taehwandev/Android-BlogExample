package tech.thdev.customlistviewsample.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import tech.thdev.customlistviewsample.R;
import tech.thdev.customlistviewsample.data.GitHubItem;

/**
 * Created by tae-hwan on 10/24/16.
 */

public class GitHubUserAdapter extends ArrayAdapter<GitHubItem> {

    private View rootView;
    private ViewHolder viewHolder;

    /**
     * LruCache를 사용하여 Image를 캐쉬처리
     */
    private LruCache<String, WeakReference<Bitmap>> cache = new LruCache<>(5 * 1024 * 1024); // 5MiB

    public GitHubUserAdapter(Context context, int resource, List<GitHubItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            rootView = inflater.inflate(R.layout.item_github_user, null);

            viewHolder = new ViewHolder();
            viewHolder.imgUserAvater = (ImageView) rootView.findViewById(R.id.img_user_avater);
            viewHolder.tvUserName = (TextView) rootView.findViewById(R.id.tv_user_name);
            viewHolder.tvUserScore = (TextView) rootView.findViewById(R.id.tv_user_score);

            // setTag
            rootView.setTag(viewHolder);

        } else {
            rootView = convertView;
            viewHolder = (ViewHolder) rootView.getTag();
        }

        final GitHubItem gitHubItem = getItem(position);
        if (gitHubItem != null) {
            viewHolder.tvUserName.setText(gitHubItem.login);
            viewHolder.tvUserScore.setText(String.format("%f", gitHubItem.score));
            new ImageAsync(viewHolder.imgUserAvater).execute(gitHubItem.avatar_url);
        }

        return rootView;
    }

    private class ImageAsync extends AsyncTask<String, String, String> {

        private WeakReference<ImageView> weakReferenceImageView;

        public ImageAsync(ImageView imageView) {
            this.weakReferenceImageView = new WeakReference<>(imageView);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String urlString = strings[0];

                WeakReference<Bitmap> weakReference = cache.get(urlString);

                if (weakReference == null || weakReference.get() == null) {
                    URL url = new URL(urlString);
                    Log.d("TA", urlString);
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    weakReference = new WeakReference<>(BitmapFactory.decodeStream(bis));
                    cache.put(urlString, weakReference);
                    bis.close();
                    is.close();
                }
                return urlString;

            } catch (IOException e) {
                // TODO: handle exception
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakReferenceImageView.get().setImageResource(R.mipmap.ic_launcher);
        }

        @Override
        protected void onPostExecute(String url) {
            super.onPostExecute(url);
            Log.e("TAG", "bitmap " + url);
            if (!TextUtils.isEmpty(url)) {
                weakReferenceImageView.get().setImageBitmap(cache.get(url).get());
            }
        }
    }

    private class ViewHolder {
        ImageView imgUserAvater;
        TextView tvUserName;
        TextView tvUserScore;
    }
}
