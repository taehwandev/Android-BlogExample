package tech.thdev.app.view.one.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.thdev.app.view.one.adapter.holder.SampleViewHolder
import tech.thdev.app.view.one.adapter.model.SampleOneAdapterContract
import java.util.*

/**
 * Created by Tae-hwan on 12/12/2016.
 */

class SampleOneAdapter(val context: Context) : RecyclerView.Adapter<SampleViewHolder>(), SampleOneAdapterContract.Model, SampleOneAdapterContract.View {

    private val itemList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SampleViewHolder {
        return SampleViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: SampleViewHolder?, position: Int) {
        holder?.onBind(getItem(position), position)
    }

    override fun getItemCount() = itemList.size

    override fun addItem(item: String) {
        itemList.add(item)
    }

    override fun getItem(position: Int): String {
        return itemList[position]
    }

    override fun getItems(): List<String> {
        return itemList
    }

    override fun reload() {
        notifyDataSetChanged()
    }
}