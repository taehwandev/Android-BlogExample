package tech.thdev.kotlin_example_01.view.flickr

import android.app.SearchManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import tech.thdev.kotlin_example_01.R
import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerAdapter
import tech.thdev.kotlin_example_01.base.view.BaseFragment
import tech.thdev.kotlin_example_01.listener.LongClickListener
import tech.thdev.kotlin_example_01.view.flickr.adapter.PhotoAdapter
import tech.thdev.kotlin_example_01.view.flickr.presenter.MainContract

/**
 * Created by Tae-hwan on 7/21/16.
 */
class MainFragment : BaseFragment<MainContract.Presenter>(), MainContract.View {

    val TYPE_SAFE_SEARCH_SAFE: Int = 1
    val TYPE_SAFE_SEARCH_MODERATE: Int = 2
    val TYPE_SAFE_SEARCH_RESTRICTED: Int = 3

    private var loading: Boolean = false
    private var page: Int = 0

    private var adapter: PhotoAdapter? = null
    private lateinit var recyclerView: RecyclerView

    private var clBlur: ConstraintLayout? = null
    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // ?.let { } = Safe calls == if (null != obj) { }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater?.let { it.inflate(R.layout.fragment_main, container, false) }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PhotoAdapter(context)
        adapter?.longClickListener = object: LongClickListener {

            override fun onLongClickListener(baseRecyclerAdapter: BaseRecyclerAdapter<*>, position: Int): Boolean {
                presenter?.updateLongClickItem(position)
                return false
            }
        }
        recyclerView = view?.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.addOnScrollListener(InfiniteScrollListener({ presenter!!.loadPhotos(page) }, recyclerView.layoutManager as StaggeredGridLayoutManager))
        recyclerView.adapter = adapter

        presenter!!.setDataModel(adapter!!)
        initPhotoList()

        clBlur = activity?.findViewById(R.id.constraintLayout) as ConstraintLayout
        imageView = activity?.findViewById(R.id.image_view) as ImageView
    }

    override fun showBlurDialog(imageUrl: String?) {
        clBlur?.visibility = View.VISIBLE

        val rootView = activity.window.decorView.findViewById(android.R.id.content)
        rootView.isDrawingCacheEnabled = true
        val bitmap: Bitmap? = rootView.drawingCache

        imageView?.setImageBitmap(bitmap)

        Toast.makeText(context, "ShowBlurDialog", Toast.LENGTH_SHORT).show()
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

        presenter?.unSubscribeSearch()
        recyclerView.removeOnScrollListener(InfiniteScrollListener({ presenter!!.loadPhotos(page) }, recyclerView.layoutManager as StaggeredGridLayoutManager))
    }

    override fun initPhotoList() {
        page = 0
        presenter?.loadPhotos(page)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        inflater?.inflate(R.menu.menu_search, menu)

        val searchView: SearchView = MenuItemCompat.getActionView(menu?.findItem(R.id.action_search)) as SearchView
        val searchManager: SearchManager? = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        page = 0
                        presenter?.searchPhotos(page, TYPE_SAFE_SEARCH_SAFE, query)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        page = 0
                        presenter?.searchPhotos(page, TYPE_SAFE_SEARCH_SAFE, newText)
                        return true
                    }
                }
        )

        super.onCreateOptionsMenu(menu, inflater)
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
