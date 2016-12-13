package tech.thdev.app.network

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import tech.thdev.app.BuildConfig
import tech.thdev.app.data.DailyBoxOfficeContainer
import tech.thdev.app.data.WeeklyBoxOfficeContainer

/**
 * Created by Tae-hwan on 12/12/2016.
 *
 * Movie Boxoffice
 */

interface KobisApiService {

    // http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101
    @GET("kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=${BuildConfig.KOBIS_API_KEY}")
    fun searchMovie(@Query("targetDt") targetDate: String): Flowable<DailyBoxOfficeContainer>

    // http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20161212
    @GET("kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key=${BuildConfig.KOBIS_API_KEY}")
    fun getWeekBoxOffice(@Query("targetDt") targetDate: String): Flowable<WeeklyBoxOfficeContainer>
}