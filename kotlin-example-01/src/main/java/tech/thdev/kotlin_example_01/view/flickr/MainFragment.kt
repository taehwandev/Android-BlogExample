package tech.thdev.kotlin_example_01.view.flickr

import android.annotation.TargetApi
import android.app.SearchManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.support.constraint.ConstraintLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import tech.thdev.kotlin_example_01.R
import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerAdapter
import tech.thdev.kotlin_example_01.base.view.BaseFragment
import tech.thdev.kotlin_example_01.listener.LongClickListener
import tech.thdev.kotlin_example_01.view.flickr.adapter.PhotoAdapter
import tech.thdev.kotlin_example_01.view.flickr.presenter.MainContract
import java.util.concurrent.TimeUnit

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
    private var imgBlurBackground: ImageView? = null
    private var imgView: ImageView? = null

    private var containerMain: CoordinatorLayout? = null

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
        adapter?.longClickListener = object : LongClickListener {

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
        imgBlurBackground = activity?.findViewById(R.id.img_blur_background) as ImageView
        imgView = activity?.findViewById(R.id.img_view) as ImageView

        containerMain = activity?.findViewById(R.id.container_main) as CoordinatorLayout
    }

    override fun showBlurDialog(imageUrl: String?) {
        clBlur?.visibility = View.VISIBLE
        imgView?.visibility = View.VISIBLE
        imgBlurBackground?.visibility = View.VISIBLE

        drawBackgroundImage()

        Glide.with(context)
                .load(imageUrl)
                .listener(object : RequestListener<String, GlideDrawable> {
                    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        /*
                         * Timer
                         */
                        Observable.timer(5, TimeUnit.SECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    clBlur?.visibility = View.GONE
                                    imgView?.visibility = View.GONE
                                    imgBlurBackground?.visibility = View.GONE
                                }
                        return false
                    }

                    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                        clBlur?.visibility = View.GONE
                        imgView?.visibility = View.GONE
                        imgBlurBackground?.visibility = View.GONE

                        Toast.makeText(context, "Image load fail", Toast.LENGTH_SHORT).show()
                        return false
                    }
                })
                .placeholder(android.R.drawable.ic_menu_gallery)
                .centerCrop()
                .crossFade()
                .into(imgView)
    }

    /**
     * Root capture...
     */
    private fun drawBackgroundImage() {
        containerMain?.isDrawingCacheEnabled = true
        containerMain?.buildDrawingCache(true)
        val bitmap: Bitmap? = containerMain?.drawingCache
        val image: Bitmap? = Bitmap.createBitmap(bitmap)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val temp = createBlurImage(image, 25.0f)
            temp?.let { imgBlurBackground?.setImageBitmap(it) }

        } else
            imgBlurBackground?.setImageBitmap(image)

        containerMain?.isDrawingCacheEnabled = false
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun createBlurImage(src: Bitmap?, r: Float): Bitmap? {
        src?.let {
            val bitmap: Bitmap = Bitmap.createBitmap(src.width, src.height, Bitmap.Config.ARGB_8888)

            val renderScript: RenderScript = RenderScript.create(context)

            val blurInput: Allocation = Allocation.createFromBitmap(renderScript, src)
            val blurOutput: Allocation = Allocation.createFromBitmap(renderScript, bitmap)

            val blur: ScriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))

            blur.setInput(blurInput)
            blur.setRadius(r)
            blur.forEach(blurOutput)

            blurOutput.copyTo(bitmap)
            renderScript.destroy()
            return bitmap
        }

        return null
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
