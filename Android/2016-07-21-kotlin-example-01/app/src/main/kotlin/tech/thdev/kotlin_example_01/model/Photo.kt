package tech.thdev.kotlin_example_01.model

import tech.thdev.kotlin_example_01.base.model.BaseItem

/**
 * Created by Tae-hwan on 7/22/16.
 */
data class Photo(val id: String, val owner: String,
                 val secret: String, val server: String,
                 val farm: Long, val title: String,
                 val ispublic: Long, val isfriend: Long,
                 val isfamily: Long, override val viewType: Int): BaseItem