package tech.thdev.kotlin_example_01.network

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import tech.thdev.kotlin_example_01.BuildConfig
import tech.thdev.kotlin_example_01.model.PhotoResponse

/**
 * Created by Tae-hwan on 7/22/16.
 */
interface PhotoServiceInterface {

    @GET("?method=flickr.interestingness.getList&format=json&nojsoncallback=1&api_key=" + BuildConfig.FLICKR_API_KEY)
    fun getRecentFlickrPhotos(@Query("page") page: Int): Observable<PhotoResponse>

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&apk_key=" + BuildConfig.FLICKR_API_KEY)
    fun getFlickrPhotosSearch(
        @Query("page") page: Int,
        @Query("safe_search") safeSearch: Int,
        @Query("text") text: String): Observable<PhotoResponse>
}
