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

    private val photoApi: PhotoServiceInterface

    init {
        val retrofit = create()

        photoApi = retrofit.create(PhotoServiceInterface::class.java)
    }

    fun getRecentPhotos(page: Int) : Observable<PhotoResponse> = photoApi.getObservableFlickrPhotos(page)


    private fun create(): Retrofit {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/rest/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
}