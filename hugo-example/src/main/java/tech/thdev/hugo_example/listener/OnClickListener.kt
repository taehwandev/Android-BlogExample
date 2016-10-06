package tech.thdev.hugo_example.listener

import tech.thdev.base.adapter.BaseRecyclerAdapter
import tech.thdev.base.model.BaseItem

/**
 * Created by rgpkorea on 06/10/2016.
 */
interface OnClickListener {

    fun <T : BaseItem> onItemClick(baseRecyclerView: BaseRecyclerAdapter<T>?, position: Int)
}