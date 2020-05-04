package tech.thdev.app.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import androidx.collection.LruCache
import tech.thdev.app.R
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.URL
import java.net.URLConnection
import java.util.*
import java.util.concurrent.RejectedExecutionException

/**
 * Created by tae-hwan on 10/25/16.
 */
class ImageDownload private constructor() {

    companion object {
        private var imageDownload: ImageDownload? = null
        fun newInstance(): ImageDownload =
            imageDownload ?: synchronized(ImageDownload::class.java) {
                imageDownload ?: ImageDownload().also { imageDownload = it }
            }
    }

    private val map = LinkedHashMap<String, ImageAsync>()

    /**
     * LruCache를 사용하여 Image를 캐쉬처리
     */
    private val cache = LruCache<String?, WeakReference<Bitmap?>?>(5 * 1024 * 1024) // 5MiB

    private fun clearAsync() {
        synchronized(map) {
            for (imageAsync in map.values) {
                cancelAsync(imageAsync)
            }
            Log.e("TAG", "cancel Async size : " + map.size)
            map.clear()
        }
    }

    private fun cancelAsync(async: ImageAsync?) {
        if (async!!.status == AsyncTask.Status.RUNNING) {
            async.cancel(true)
        }
    }

    fun startImageDownload(imageView: ImageView, url: String?) {
        synchronized(map) {
            if (map.containsKey(url)) {
                cancelAsync(map[url])
                map.remove(url)
            }
            try {
                val weakReference = cache[url!!]
                if (weakReference?.get() == null) {
                    // 이미지 뷰 동기화를 위해서 tag 추가
                    imageView.tag = url
                    val imageAsync = ImageAsync(imageView)
                    imageAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url)
                    map.put(url, imageAsync)
                } else {
                    imageView.setImageBitmap(weakReference.get())
                }
            } catch (e: RejectedExecutionException) {
                clearAsync()
            }
        }
    }

    private inner class ImageAsync(imageView: ImageView) : AsyncTask<String?, String?, String?>() {
        private val weakReferenceImageView: WeakReference<ImageView> = WeakReference(imageView)
        private var connection: URLConnection? = null
        private var inputStream: InputStream? = null
        private var bufferedInputStream: BufferedInputStream? = null

        override fun doInBackground(vararg params: String?): String? {
            try {
                val urlString = params[0] ?: ""
                val url = URL(urlString)
                connection = url.openConnection()
                connection?.connect()
                inputStream = connection?.getInputStream()
                bufferedInputStream = BufferedInputStream(inputStream!!)
                cache.put(
                    urlString,
                    WeakReference(
                        BitmapFactory.decodeStream(bufferedInputStream)
                    )
                )
                return urlString
            } catch (e: IOException) {
                closeStream()
            } finally {
                closeStream()
            }
            return null
        }

        private fun closeStream() {
            try {
                if (inputStream != null) {
                    inputStream!!.close()
                    inputStream = null
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream!!.close()
                    bufferedInputStream = null
                }
            } catch (e1: IOException) {
                e1.printStackTrace()
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()
            weakReferenceImageView.get()!!.setImageResource(R.mipmap.ic_launcher)
        }

        override fun onPostExecute(url: String?) {
            super.onPostExecute(url)
            if (isCancelled) {
                return
            }
            val imageView = weakReferenceImageView.get()
            if (!TextUtils.isEmpty(url) && imageView!!.tag != null && imageView.tag == url && cache[url!!] != null && cache[url]!!.get() != null
            ) {
                imageView.setImageBitmap(cache[url]!!.get())
            }
        }
    }
}