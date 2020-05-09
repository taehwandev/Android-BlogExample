package tech.thdev.app.ui.weekboxoffice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.thdev.app.R
import tech.thdev.app.base.BasePresenterFragment
import tech.thdev.app.data.source.MovieSearchRepository
import tech.thdev.app.common.adapter.BoxOfficeAdapter
import tech.thdev.app.ui.weekboxoffice.presenter.WeeklyBoxOfficeContract
import tech.thdev.app.ui.weekboxoffice.presenter.WeeklyBoxOfficePresenter
import java.text.SimpleDateFormat

/**
 * Created by tae-hwan on 12/13/16.
 */
class WeeklyBoxOfficeFragment :
    BasePresenterFragment<WeeklyBoxOfficeContract.View, WeeklyBoxOfficeContract.Presenter>(),
    WeeklyBoxOfficeContract.View {

    companion object {
        fun newInstance(): WeeklyBoxOfficeFragment =
            WeeklyBoxOfficeFragment()
    }

    private val boxOfficeAdapter by lazy {
        BoxOfficeAdapter()
    }

    override fun onCreatePresenter(): WeeklyBoxOfficeContract.Presenter =
        WeeklyBoxOfficePresenter(
            boxOfficeAdapter,
            boxOfficeAdapter,
            MovieSearchRepository
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

        view.findViewById<RecyclerView>(R.id.recycler_view).run {
            adapter = boxOfficeAdapter
        }

        view.findViewById<Button>(R.id.btn_reload).setOnClickListener {
            loadItem()
        }

        loadItem()
    }

    private fun loadItem() {
        presenter.getWeeklyBoxOffice(
            SimpleDateFormat("yyyyMMdd")
                .format(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 7)
        )
    }

    override fun loadFail(messageRes: Int) {
        view?.findViewById<TextView>(R.id.tv_no_content)?.setText(messageRes)
        view?.findViewById<View>(R.id.rl_empty_view)?.visibility = View.VISIBLE
    }
}