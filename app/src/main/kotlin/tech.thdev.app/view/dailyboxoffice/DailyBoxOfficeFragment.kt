package tech.thdev.app.view.dailyboxoffice

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_box_office.*
import tech.thdev.app.R
import tech.thdev.app.adapter.movie.adapter.BoxOfficeAdapter
import tech.thdev.app.data.source.MovieSearchRepository
import tech.thdev.app.view.dailyboxoffice.presenter.DailyBoxOfficeContract
import tech.thdev.app.view.dailyboxoffice.presenter.DailyBoxOfficePresenter
import tech.thdev.base.view.BasePresenterFragment
import java.text.SimpleDateFormat

/**
 * Created by Tae-hwan on 12/12/2016.
 */

class DailyBoxOfficeFragment : BasePresenterFragment<DailyBoxOfficeContract.View, DailyBoxOfficeContract.Presenter>(), DailyBoxOfficeContract.View {

    override fun onCreatePresenter() = DailyBoxOfficePresenter()

    override fun getLayout() = R.layout.fragment_box_office

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sampleOneAdapter = BoxOfficeAdapter(context)
        recycler_view.adapter = sampleOneAdapter

        presenter?.boxOfficeModel = sampleOneAdapter
        presenter?.boxOfficeView = sampleOneAdapter

        presenter?.movieSearchRepository = MovieSearchRepository

        presenter?.getDailyBoxOffice(context, SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis() - (1000 * 60 * 60 * 24)))

        btn_reload.setOnClickListener {
            presenter?.getDailyBoxOffice(context, SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis() - (1000 * 60 * 60 * 24)))
        }
    }

    override fun isFinishing(): Boolean {
        return activity.isFinishing
    }

    override fun showLoadFail(message: String) {
        tv_no_content.text = message
        rl_empty_view.visibility = View.VISIBLE
    }
}