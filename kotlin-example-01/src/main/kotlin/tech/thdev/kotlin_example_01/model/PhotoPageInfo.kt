package tech.thdev.kotlin_example_01.model

/**
 * Created by Tae-hwan on 7/22/16.
 */
data class PhotoPageInfo(val page: Int, val pages: Int,
                         val perpage: Int, val total: Int,
                         val photo: List<Photo>)