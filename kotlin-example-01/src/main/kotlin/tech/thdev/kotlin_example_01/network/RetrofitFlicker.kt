package tech.thdev.kotlin_example_01.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import tech.thdev.kotlin_example_01.model.PhotoResponse

/**
 * Created by Tae-hwan on 7/22/16.
 */
class RetrofitFlicker {

    val FLICKER_URL: String = "https://api.flickr.com/services/rest/"

    private val photoApi: PhotoServiceInterface

    init {
        // val : read-only
        val retrofit = create()

        photoApi = retrofit.create(PhotoServiceInterface::class.java)
    }

    /**
     * get Recent photo list
     */
    fun getRecentPhotos(page: Int) :
            Observable<PhotoResponse> = photoApi.getRecentFlickrPhotos(page)

    /**
     * Search photo list
     */
    fun getSearchPhotos(page: Int, safeSearch: Int, text: String):
            Observable<PhotoResponse> = photoApi.getFlickrPhotosSearch(page, safeSearch, text)


    /**
     * Retrofit create
     */
    private fun create(): Retrofit {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
                .baseUrl(FLICKER_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
}