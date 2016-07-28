package tech.thdev.kotlin_example_01.view.adapter.model

import tech.thdev.kotlin_example_01.data.Photo

/**
 * Created by Tae-hwan on 7/22/16.
 */
interface PhotoDataModel {

    fun addItem(item: Photo)

    fun clean()
}