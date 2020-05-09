package tech.thdev.app.data

import com.google.gson.annotations.SerializedName

/**
 * Created by tae-hwan on 12/12/2016.
 */

data class DailyBoxOfficeContainer(
    @SerializedName("boxOfficeResult") val boxOfficeResult: DailyBoxOfficeResult
)