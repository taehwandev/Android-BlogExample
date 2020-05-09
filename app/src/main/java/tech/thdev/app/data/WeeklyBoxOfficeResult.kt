package tech.thdev.app.data

import com.google.gson.annotations.SerializedName

/**
 * Created by tae-hwan on 12/13/16.
 */
data class WeeklyBoxOfficeResult(
    @SerializedName("boxofficeType") val boxOfficeType: String,
    @SerializedName("showRange") val showRange: String,
    @SerializedName("weeklyBoxOfficeList") val weeklyBoxOfficeList: List<BoxOfficeItem>
)