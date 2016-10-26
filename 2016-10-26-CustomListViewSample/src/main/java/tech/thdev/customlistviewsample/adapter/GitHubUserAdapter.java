package tech.thdev.customlistviewsample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tech.thdev.customlistviewsample.R;
import tech.thdev.customlistviewsample.async.ImageDownload;
import tech.thdev.customlistviewsample.async.ImageDownloadThread;
import tech.thdev.customlistviewsample.data.GitHubItem;

/**
 * Created by tae-hwan on 10/24/16.
 */

public class GitHubUserAdapter extends ArrayAdapter<GitHubItem> {

    private View rootView;
    private ViewHolder viewHolder;

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
//            ImageDownload.getInstance().startImageDownload(viewHolder.imgUserAvater, gitHubItem.avatar_url);
            ImageDownloadThread.getInstance().loadImage(R.mipmap.ic_launcher, viewHolder.imgUserAvater, gitHubItem.avatar_url);
        }

        return rootView;
    }

    private class ViewHolder {
        ImageView imgUserAvater;
        TextView tvUserName;
        TextView tvUserScore;
    }
}
