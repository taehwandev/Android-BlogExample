package tech.thdev.app.adapter.pager.model

/**
 * Created by tae-hwan on 12/11/16.
 */

interface SectionsPagerModel {

    fun setListItem(position: Int)

    fun getListItem(position: Int): Int

    fun getCount(): Int
}