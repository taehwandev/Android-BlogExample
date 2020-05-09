package tech.thdev.app.ui.dailyboxoffice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.thdev.app.R
import tech.thdev.app.base.BasePresenterFragment
import tech.thdev.app.data.source.MovieSearchRepository
import tech.thdev.app.common.adapter.BoxOfficeAdapter
import tech.thdev.app.ui.dailyboxoffice.presenter.DailyBoxOfficeContract
import tech.thdev.app.ui.dailyboxoffice.presenter.DailyBoxOfficePresenter
import java.text.SimpleDateFormat

/**
 * Created by Tae-hwan on 12/12/2016.
 */
class DailyBoxOfficeFragment :
    BasePresenterFragment<DailyBoxOfficeContract.View, DailyBoxOfficeContract.Presenter>(),
    DailyBoxOfficeContract.View {

    companion object {
        fun newInstance(): DailyBoxOfficeFragment =
            DailyBoxOfficeFragment()
    }

    private val boxOfficeAdapter: BoxOfficeAdapter by lazy {
        BoxOfficeAdapter()
    }

    private val tvNoContent by lazy {
        view?.findViewById<TextView>(R.id.tv_no_content)
    }

    private val emptyView by lazy {
        view?.findViewById<View>(R.id.rl_empty_view)
    }

    private val progressBar by lazy {
        view?.findViewById<ProgressBar>(R.id.progress_bar)
    }

    override fun onCreatePresenter() =
        DailyBoxOfficePresenter(
            boxOfficeModel = boxOfficeAdapter,
            boxOfficeView = boxOfficeAdapter,
            movieSearchRepository = MovieSearchRepository
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_box_office, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = boxOfficeAdapter

        loadDailyBoxOffice()

        view.findViewById<View>(R.id.btn_reload).setOnClickListener {
            loadDailyBoxOffice()
        }
    }

    private fun loadDailyBoxOffice() {
        presenter.getDailyBoxOffice(
            SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis() - (1000 * 60 * 60 * 24))
        )
    }

    override fun showLoadFail(messageId: Int) {
        tvNoContent?.setText(messageId)
        emptyView?.visibility = View.VISIBLE
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }
}