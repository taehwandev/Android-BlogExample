package tech.thdev.view.recycler.view.ex.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tech.thdev.view.recycler.view.ex.databinding.ItemViewBinding
import tech.thdev.view.recycler.view.ex.model.SampleItem

class MainViewHolder(
    private val binding: ItemViewBinding,
    private val onClick: (adapterIndex: Int) -> Unit,
) : ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onClick(absoluteAdapterPosition)
        }
    }

    fun bind(item: SampleItem) {
        binding.tvMessage.text = item.message
        binding.ivIcon.setImageResource(item.icon)
    }
}
