package tech.thdev.app.data

/**
 * Created by tae-hwan on 12/13/16.
 */
data class WeeklyBoxOfficeResult(val boxofficeType: String,
                                val showRange: String,
                                val weeklyBoxOfficeList: List<BoxOfficeItem>)