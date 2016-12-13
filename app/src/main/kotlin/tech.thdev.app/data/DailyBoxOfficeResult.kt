package tech.thdev.app.data

/**
 * Created by tae-hwan on 12/12/2016.
 */

data class DailyBoxOfficeResult(val boxofficeType: String,
                                val showRange: String,
                                val dailyBoxOfficeList: List<BoxOfficeItem>)