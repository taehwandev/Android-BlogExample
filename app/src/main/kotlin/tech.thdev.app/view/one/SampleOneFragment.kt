package tech.thdev.app.view.one

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_sample_one.*
import tech.thdev.app.R
import tech.thdev.app.data.source.MovieSearchRepository
import tech.thdev.app.view.one.adapter.SampleOneAdapter
import tech.thdev.app.view.one.presenter.SampleOneContract
import tech.thdev.app.view.one.presenter.SampleOnePresenter
import tech.thdev.base.view.BasePresenterFragment

/**
 * Created by Tae-hwan on 12/12/2016.
 */

class SampleOneFragment : BasePresenterFragment<SampleOneContract.View, SampleOneContract.Presenter>(), SampleOneContract.View {

    override fun onCreatePresenter() = SampleOnePresenter()

    override fun getLayout() = R.layout.fragment_sample_one

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sampleOneAdapter = SampleOneAdapter(context)
        recycler_view.adapter = sampleOneAdapter

        presenter?.sampleOneOneModel = sampleOneAdapter
        presenter?.sampleOneOneView = sampleOneAdapter

        presenter?.movieSearchRepository = MovieSearchRepository
        presenter?.searchMovie("20161211")
    }

    override fun isFinishing() {
        activity.isFinishing
    }
}