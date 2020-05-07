package tech.thdev.app.view.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.thdev.app.data.BottomData
import java.util.*

/**
 * Created by Tae-hwan on 09/12/2016.
 */
class RecyclerBottomSheetAdapter : RecyclerView.Adapter<RecyclerBottomSheetViewHolder>() {

    private val itemList = ArrayList<BottomData>()
    fun setItem(item: BottomData) {
        itemList.add(item)
    }

    fun getItem(position: Int): BottomData {
        return itemList[position]
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerBottomSheetViewHolder {
        return RecyclerBottomSheetViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerBottomSheetViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}