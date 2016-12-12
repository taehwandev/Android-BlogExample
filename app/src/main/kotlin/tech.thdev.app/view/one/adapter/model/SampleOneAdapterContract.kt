package tech.thdev.app.view.one.adapter.model

/**
 * Created by Tae-hwan on 12/12/2016.
 */

interface SampleOneAdapterContract {

    interface View {
        fun reload()
    }

    interface Model {

        fun addItem(item: String)

        fun getItem(position: Int): String

        fun getItemCount(): Int

        fun getItems(): List<String>
    }
}