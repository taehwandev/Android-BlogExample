package tech.thdev.app.ui.github

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import tech.thdev.app.R
import tech.thdev.app.data.GitHubItem
import tech.thdev.app.imageloader.ImageDownloadThread

/**
 * Created by tae-hwan on 10/24/16.
 */
class GitHubUserAdapter(
    context: Context,
    resource: Int,
    objects: List<GitHubItem>
) : ArrayAdapter<GitHubItem?>(context, resource, objects) {
    private var rootView: View? = null
    private var viewHolder: ViewHolder? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (convertView == null) {
            val inflate = LayoutInflater.from(context)
            rootView = inflate.inflate(R.layout.item_github_user, parent, false)
            viewHolder = ViewHolder(
                imgUserAvater = rootView!!.findViewById<AppCompatImageView>(R.id.img_user_avater),
                tvUserName = rootView!!.findViewById<AppCompatTextView>(R.id.tv_user_name),
                tvUserScore = rootView!!.findViewById<AppCompatTextView>(R.id.tv_user_score)
            )

            // setTag
            rootView!!.tag = viewHolder
        } else {
            rootView = convertView
            viewHolder = rootView!!.tag as ViewHolder
        }

        getItem(position)?.let { item ->
            viewHolder!!.run {
                tvUserName.text = item.login
                tvUserScore.text = String.format("%f", item.score)
                item.avatarUrl?.let {
                    // ImageDownload.newInstance().startImageDownload(imgUserAvater, it)

                    ImageDownloadThread.newInstance()
                        .loadImage(R.mipmap.ic_launcher, imgUserAvater, it)
                }
            }
        }

        return rootView!!
    }

    private class ViewHolder(
        val imgUserAvater: AppCompatImageView,
        val tvUserName: AppCompatTextView,
        val tvUserScore: AppCompatTextView
    )
}