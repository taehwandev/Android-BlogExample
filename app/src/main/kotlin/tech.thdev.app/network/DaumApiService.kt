package tech.thdev.app.network

import retrofit2.http.GET
import retrofit2.http.Query
import tech.thdev.app.BuildConfig

/**
 * Created by Tae-hwan on 12/12/2016.
 */

interface DaumApiService {

    // https://apis.daum.net/contents/movie?apikey={apikey}&q=미생&output=json
    @GET("/contents/movie?apikey=${BuildConfig.DAUM_API_KEY}&output=json")
    fun searchMovie(@Query("q") query: String)
}