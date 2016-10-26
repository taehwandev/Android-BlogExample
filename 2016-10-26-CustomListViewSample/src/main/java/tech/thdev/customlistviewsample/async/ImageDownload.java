package tech.thdev.customlistviewsample.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.concurrent.RejectedExecutionException;

import tech.thdev.customlistviewsample.R;

/**
 * Created by tae-hwan on 10/25/16.
 */

public class ImageDownload {

    private final LinkedHashMap<String, ImageAsync> map = new LinkedHashMap<>();

    /**
     * LruCache를 사용하여 Image를 캐쉬처리
     */
    private LruCache<String, WeakReference<Bitmap>> cache = new LruCache<>(5 * 1024 * 1024); // 5MiB

    private ImageDownload() {

    }

    private static ImageDownload imageDownload;

    public static ImageDownload getInstance() {
        if (imageDownload == null) {
            synchronized (ImageDownload.class) {
                if (imageDownload == null) {
                    imageDownload = new ImageDownload();
                }
            }
        }
        return imageDownload;
    }

    private void clearAsync() {
        synchronized (map) {
            for (ImageAsync imageAsync : map.values()) {
                cancelAsync(imageAsync);
            }

            Log.e("TAG", "cancel Async size : " + map.size());
            map.clear();
        }
    }


    private void cancelAsync(ImageAsync async) {
        if (async.getStatus().equals(AsyncTask.Status.RUNNING)) {
            async.cancel(true);
        }
    }

    public void startImageDownload(final ImageView imageView, final String url) {
        synchronized (map) {
            if (map.containsKey(url)) {
                cancelAsync(map.get(url));
                map.remove(url);
            }

            try {
                WeakReference<Bitmap> weakReference = cache.get(url);
                if (weakReference == null || weakReference.get() == null) {
                    // 이미지 뷰 동기화를 위해서 tag 추가
                    imageView.setTag(url);

                    ImageAsync imageAsync = new ImageAsync(imageView);
                    imageAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
                    map.put(url, imageAsync);

                } else {
                    imageView.setImageBitmap(weakReference.get());
                }
            } catch (RejectedExecutionException e) {
                clearAsync();
            }
        }
    }

    private class ImageAsync extends AsyncTask<String, String, String> {

        private WeakReference<ImageView> weakReferenceImageView;

        URLConnection connection;
        InputStream inputStream;
        BufferedInputStream bufferedInputStream;

        public ImageAsync(ImageView imageView) {
            this.weakReferenceImageView = new WeakReference<>(imageView);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String urlString = strings[0];

                URL url = new URL(urlString);
                connection = url.openConnection();
                connection.connect();

                inputStream = connection.getInputStream();
                bufferedInputStream = new BufferedInputStream(inputStream);

                cache.put(urlString, new WeakReference<>(BitmapFactory.decodeStream(bufferedInputStream)));
                return urlString;

            } catch (IOException e) {
                closeStream();

            } finally {
                closeStream();
            }
            return null;
        }

        private void closeStream() {
            try {
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                    bufferedInputStream = null;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakReferenceImageView.get().setImageResource(R.mipmap.ic_launcher);
        }

        @Override
        protected void onPostExecute(String url) {
            super.onPostExecute(url);
            if (isCancelled()) {
                return;
            }
            ImageView imageView = weakReferenceImageView.get();
            if (!TextUtils.isEmpty(url) &&
                    imageView.getTag() != null &&
                    imageView.getTag().equals(url) &&
                    cache.get(url) != null &&
                    cache.get(url).get() != null) {
                imageView.setImageBitmap(cache.get(url).get());
            }
        }
    }
}
