package tech.thdev.kotlin_example_01.network

import rx.Observable
import tech.thdev.kotlin_example_01.model.PhotoResponse
import tech.thdev.kotlin_example_01.network.retorfit.createRetrofit

/**
 * Created by Tae-hwan on 7/22/16.
 */
class FlickrModule {

    val FLICKER_URL: String = "https://api.flickr.com/services/rest/"

    private val photoApi: FlickrServiceInterface

    init {
        photoApi = createRetrofit(FlickrServiceInterface::class.java, FLICKER_URL)
    }

    /**
     * get Recent photo list
     */
    fun getRecentPhotos(page: Int):
            Observable<PhotoResponse> = photoApi.getFlickrRecentPhotos(page)

    /**
     * Search photo list
     */
    fun getSearchPhotos(page: Int, safeSearch: Int, text: String):
            Observable<PhotoResponse> = photoApi.getFlickrPhotosSearch(page, safeSearch, text)
}