package tech.thdev.kotlin_example_01.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import tech.thdev.kotlin_example_01.R
import tech.thdev.kotlin_example_01.base.BaseFragment
import tech.thdev.kotlin_example_01.view.adapter.PhotoAdapter
import tech.thdev.kotlin_example_01.view.presenter.MainContract

/**
 * Created by Tae-hwan on 7/21/16.
 */
class MainFragment : BaseFragment<MainContract.Presenter>(), MainContract.View {

    private var loading: Boolean = false
    private var page: Int = 0

    private var adapter: PhotoAdapter? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater!!.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PhotoAdapter(context)
        recyclerView = view?.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.addOnScrollListener(InfiniteScrollListener({ presenter()?.loadPhotos(page) }, recyclerView.layoutManager as StaggeredGridLayoutManager))
        recyclerView.adapter = adapter

        presenter()?.setDataModel(adapter!!)
        presenter()?.loadPhotos(page)
    }

    override fun showProgress() {
        loading = true
    }

    override fun hideProgress() {
        loading = false
    }

    override fun showFailLoad() {
        Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        recyclerView.removeOnScrollListener(InfiniteScrollListener({ presenter()?.loadPhotos(page) }, recyclerView.layoutManager as StaggeredGridLayoutManager))
    }

    inner class InfiniteScrollListener(
            val func: () -> Unit,
            val layoutManager: StaggeredGridLayoutManager) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = recyclerView?.childCount as Int
            val totalItemCount = adapter?.itemCount as Int
            var firstVisibleItem: IntArray? = null
            firstVisibleItem = layoutManager.findFirstCompletelyVisibleItemPositions(firstVisibleItem)

            var firstItemNumber = 0
            if (firstVisibleItem != null && firstVisibleItem.size > 0) {
                firstItemNumber = firstVisibleItem[0]
            }

            Log.d("TAG", "loading " + loading + ", firstItemNumber " + firstItemNumber + ", visibleItemCount " + visibleItemCount + ", totalItemCount " + totalItemCount)
            if (!loading && (firstItemNumber + visibleItemCount) >= totalItemCount - 10) {
                ++page
                func()
            }
        }
    }
}
