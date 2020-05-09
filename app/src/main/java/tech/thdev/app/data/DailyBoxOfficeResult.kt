package tech.thdev.app.data

import com.google.gson.annotations.SerializedName

/**
 * Created by tae-hwan on 12/12/2016.
 */

data class DailyBoxOfficeResult(
    @SerializedName("boxofficeType") val boxOfficeType: String,
    @SerializedName("showRange") val showRange: String,
    @SerializedName("dailyBoxOfficeList") val dailyBoxOfficeList: List<BoxOfficeItem>
)