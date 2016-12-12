package tech.thdev.app.data.source

/**
 * Created by tae-hwan on 12/12/2016.
 */

object MovieSearchRepository : MovieSearchDataSource {

    private val remoteDataSource: MovieSearchRemoteDataSource

    init {
        remoteDataSource = MovieSearchRemoteDataSource()
    }

    override fun getBoxOffice(targetDate: String)
            = remoteDataSource.getBoxOffice(targetDate)
}