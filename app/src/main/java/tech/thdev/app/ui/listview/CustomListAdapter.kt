package tech.thdev.app.ui.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import tech.thdev.app.R
import tech.thdev.app.data.GitHubItem

/**
 * Created by tae-hwan on 10/30/16.
 */
class CustomListAdapter(
    context: Context,
    resource: Int,
    objects: List<GitHubItem>
) : ArrayAdapter<GitHubItem>(context, resource, objects) {

    private var holder: ViewHolder? = null
    private lateinit var view: View

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_github_user, parent, false)

            holder = ViewHolder(view)

            // setTag
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as? ViewHolder
        }

        val userInfo = getItem(position)
        holder?.bindView(userInfo)

        return view
    }

    class ViewHolder(private val itemView: View) {

        private val tvUserName by lazy {
            itemView.findViewById<AppCompatTextView>(R.id.tv_user_name)
        }

        private val tvUserScore by lazy {
            itemView.findViewById<AppCompatTextView>(R.id.tv_user_score)
        }
        private val imgAvater by lazy {
            itemView.findViewById<AppCompatImageView>(R.id.img_user_avater)
        }

        fun bindView(item: GitHubItem?) {
            tvUserName.text = item?.login
            tvUserScore.text = String.format(
                itemView.context.getString(R.string.score),
                item?.score ?: ""
            )

            Glide.with(itemView.context)
                .load(item?.avatarUrl)
                .centerCrop()
                .into(imgAvater)
        }
    }
}