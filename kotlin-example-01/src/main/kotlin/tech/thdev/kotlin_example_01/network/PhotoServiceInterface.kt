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

    @GET("?format=json&nojsoncallback=1&method=flickr.interestingness.getList&api_key=" + BuildConfig.FLICKER_API_KEY)
    fun getObservableFlickrPhotos(@Query("page") page: Int): Observable<PhotoResponse>
}