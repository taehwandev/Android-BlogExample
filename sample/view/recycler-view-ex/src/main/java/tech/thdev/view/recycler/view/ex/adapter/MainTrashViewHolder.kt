package tech.thdev.view.recycler.view.ex.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tech.thdev.view.recycler.view.ex.databinding.TrashItemViewBinding
import tech.thdev.view.recycler.view.ex.model.SampleItem

class MainTrashViewHolder(
    private val binding: TrashItemViewBinding,
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
        binding.tvTimer.text = "Time out ${item.time}"
    }
}
