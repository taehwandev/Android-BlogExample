package tech.thdev.customlistviewsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_github_user.view.*
import tech.thdev.customlistviewsample.R
import tech.thdev.customlistviewsample.data.GitHubItem

/**
 * Created by tae-hwan on 10/30/16.
 */
class CustomListAdapter(context: Context, resource: Int, objects: List<GitHubItem>) :
        ArrayAdapter<GitHubItem>(context, resource, objects) {

    private var holder: ViewHolder? = null
    private var view: View? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_github_user, parent, false)

            holder = ViewHolder(view)

            // setTag
            view?.tag = holder
        } else {
            view = convertView
            holder = view?.tag as ViewHolder?
        }

        val userInfo = getItem(position)
        holder?.bindView(userInfo)

        return view
    }

    inner class ViewHolder(val itemView: View?) {

        fun bindView(item: GitHubItem?) {
            itemView?.let {
                with(it) {
                    tv_user_name.text = item?.login
                    tv_user_score.text = kotlin.String.format(context.getString(tech.thdev.customlistviewsample.R.string.score, item?.score))
                    com.bumptech.glide.Glide.with(context)
                            .load(item?.avatar_url)
                            .centerCrop()
                            .crossFade()
                            .into(img_user_avater)
                }
            }
        }
    }
}