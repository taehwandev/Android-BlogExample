package tech.thdev.app.data

import com.google.gson.annotations.SerializedName

/**
 * Created by tae-hwan on 12/12/2016.
 */

data class BoxOfficeItem(
    @SerializedName("rnum") val rankNum: String,
    @SerializedName("rank") val rank: String,
    @SerializedName("rankInten") val rankInten: String,
    @SerializedName("rankOldAndNew") val rankOldAndNew: String,
    @SerializedName("movieCd") val movieCd: String,
    @SerializedName("movieNm") val movieNm: String,
    @SerializedName("audiAcc") val audiAcc: String,
    @SerializedName("openDt") val openDt: String
)