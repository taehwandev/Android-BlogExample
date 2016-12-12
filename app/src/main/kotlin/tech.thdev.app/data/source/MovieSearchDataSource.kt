package tech.thdev.app.data.source

import io.reactivex.Flowable
import tech.thdev.app.data.BoxOfficeContainer

/**
 * Created by tae-hwan on 12/12/2016.
 */

interface MovieSearchDataSource {

    // 조회하고자 하는 날짜를 yyyymmdd 형식으로 입력합니다.
    fun getBoxOffice(targetDate: String): Flowable<BoxOfficeContainer>
}