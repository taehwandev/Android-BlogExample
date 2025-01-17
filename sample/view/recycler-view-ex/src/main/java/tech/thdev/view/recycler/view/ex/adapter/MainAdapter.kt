package tech.thdev.view.recycler.view.ex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tech.thdev.view.recycler.view.ex.databinding.ItemViewBinding
import tech.thdev.view.recycler.view.ex.databinding.TrashItemViewBinding
import tech.thdev.view.recycler.view.ex.model.SampleItem

class MainAdapter : ListAdapter<SampleItem, ViewHolder>(
    object : DiffUtil.ItemCallback<SampleItem>() {
        override fun areItemsTheSame(oldItem: SampleItem, newItem: SampleItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SampleItem, newItem: SampleItem): Boolean =
            oldItem == newItem
    }
) {

    private lateinit var onClick: (position: Int) -> Unit

    fun setOnClick(onClick: (position: Int) -> Unit) {
        this.onClick = onClick
    }

    override fun getItemViewType(position: Int): Int =
        if (getItem(position).trash) {
            TYPE_TRASH
        } else {
            TYPE_DEFAULT
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            TYPE_TRASH -> MainTrashViewHolder(
                binding = TrashItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick = onClick,
            )

            else -> MainViewHolder(
                binding = ItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick = onClick,
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(getItem(position))
            is MainTrashViewHolder -> holder.bind(getItem(position))
        }
    }

    companion object {

        const val TYPE_DEFAULT = 1_000
        const val TYPE_TRASH = 2_000
    }
}
