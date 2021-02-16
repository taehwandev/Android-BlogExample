package tech.thdev.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tech.thdev.app.databinding.SampleAdapterSelectItemViewBinding
import tech.thdev.app.databinding.SampleAdapterSelectSectionItemViewBinding
import java.lang.Exception

class SampleAdapter(
    private val viewModel: SampleAdapterSelectViewModel
) : RecyclerView.Adapter<SampleViewHolder<*>>() {

    private val list = mutableListOf<SampleAdapterSelectViewModel.SelectItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder<*> =
        when (viewType) {
            R.layout.sample_adapter_select_section_item_view -> SampleSectionViewHolder(parent, viewType, viewModel)
            R.layout.sample_adapter_select_item_view -> SampleItemViewHolder(parent, viewType, viewModel)
            else -> throw Exception("Not found viewType")
        }

    override fun onBindViewHolder(holder: SampleViewHolder<*>, position: Int) {
        when (holder) {
            is SampleSectionViewHolder -> holder.bind(list[position] as SampleAdapterSelectViewModel.SelectItem.Section)
            is SampleItemViewHolder -> holder.bind(list[position] as SampleAdapterSelectViewModel.SelectItem.Item)
        }
    }

    override fun getItemCount(): Int =
        list.size

    override fun getItemViewType(position: Int): Int = when (list[position]) {
        is SampleAdapterSelectViewModel.SelectItem.Item -> R.layout.sample_adapter_select_item_view
        is SampleAdapterSelectViewModel.SelectItem.Section -> R.layout.sample_adapter_select_section_item_view
        else -> super.getItemViewType(position)
    }

    fun setItemsDiff(items: List<SampleAdapterSelectViewModel.SelectItem>) {
        val diff = DiffDefault(list, items)
        val diffResult = DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    private class DiffDefault(
        private val oldItems: List<SampleAdapterSelectViewModel.SelectItem>,
        private val newItems: List<SampleAdapterSelectViewModel.SelectItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int =
            oldItems.size

        override fun getNewListSize(): Int =
            newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]

            return oldItem == newItem
        }
    }

    fun setItemsDiffId(items: List<SampleAdapterSelectViewModel.SelectItem>) {
        val diff = DiffId(list, items)
        val diffResult = DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    private class DiffId(
        private val oldItems: List<SampleAdapterSelectViewModel.SelectItem>,
        private val newItems: List<SampleAdapterSelectViewModel.SelectItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int =
            oldItems.size

        override fun getNewListSize(): Int =
            newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]

            return if (oldItem is SampleAdapterSelectViewModel.SelectItem.Section && newItem is SampleAdapterSelectViewModel.SelectItem.Section) {
                oldItem.title == newItem.title
            } else if (oldItem is SampleAdapterSelectViewModel.SelectItem.Item && newItem is SampleAdapterSelectViewModel.SelectItem.Item) {
                oldItem.title == newItem.title
            } else {
                oldItem == newItem
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]

            return oldItem == newItem
        }
    }
}

abstract class SampleViewHolder<BINDING : ViewDataBinding>(
    parent: ViewGroup,
    layoutRes: Int,
    protected val binding: BINDING = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), layoutRes, parent, false
    )
) : RecyclerView.ViewHolder(binding.root)

class SampleSectionViewHolder(
    parent: ViewGroup,
    layoutRes: Int,
    viewModel: SampleAdapterSelectViewModel
) : SampleViewHolder<SampleAdapterSelectSectionItemViewBinding>(parent, layoutRes) {

    init {
        binding.viewModel = viewModel
    }

    fun bind(item: SampleAdapterSelectViewModel.SelectItem.Section) {
        binding.item = item
        binding.executePendingBindings()
    }
}

class SampleItemViewHolder(
    parent: ViewGroup,
    layoutRes: Int,
    viewModel: SampleAdapterSelectViewModel
) : SampleViewHolder<SampleAdapterSelectItemViewBinding>(parent, layoutRes) {

    init {
        binding.viewModel = viewModel
    }

    fun bind(item: SampleAdapterSelectViewModel.SelectItem.Item) {
        binding.item = item
        binding.executePendingBindings()
    }
}