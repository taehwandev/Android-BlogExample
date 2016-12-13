package tech.thdev.app.data.source

import io.reactivex.Flowable
import tech.thdev.app.data.DailyBoxOfficeContainer
import tech.thdev.app.network.KobisApiService
import tech.thdev.app.network.createRetrofit

/**
 * Created by tae-hwan on 12/12/2016.
 */

class MovieSearchRemoteDataSource : MovieSearchDataSource {

    private val kobisApiService: KobisApiService

    init {
        kobisApiService = createRetrofit(KobisApiService::class.java, "http://www.kobis.or.kr/")
    }

    override fun getDailyBoxOffice(targetDate: String): Flowable<DailyBoxOfficeContainer> {
        return kobisApiService.searchMovie(targetDate)
    }

    override fun getWeekBoxOffice(targetDate: String) = kobisApiService.getWeekBoxOffice(targetDate)
}