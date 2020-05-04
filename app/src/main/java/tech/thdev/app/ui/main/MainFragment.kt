package tech.thdev.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.webkit.WebSettings
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.ContentLoadingProgressBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tech.thdev.app.R
import tech.thdev.app.base.view.BaseFragment
import tech.thdev.app.databinding.FragmentMainBinding
import tech.thdev.app.ui.main.presenter.MainContract
import tech.thdev.app.util.hideKeyboard
import tech.thdev.app.webkit.CustomWebChromeClient
import tech.thdev.app.webkit.CustomWebViewClient

/**
 * Created by tae-hwan on 8/14/16.
 */
class MainFragment : BaseFragment<MainContract.Presenter>(), MainContract.View {

    companion object {
        fun newInstance(): MainFragment =
            MainFragment()
    }

    private lateinit var binding: FragmentMainBinding

    private val etUrlView: AppCompatEditText by lazy {
        requireActivity().findViewById<AppCompatEditText>(R.id.et_url_input)
    }

    private val floatingActionButton: FloatingActionButton by lazy {
        requireActivity().findViewById<FloatingActionButton>(R.id.fab)
    }

    private val loadingProgressBar: ContentLoadingProgressBar by lazy {
        requireActivity().findViewById<ContentLoadingProgressBar>(R.id.web_view_load_progress_bar)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMainBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        etUrlView.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    validationUrl()
                    true
                }
                else -> false
            }
        }
        floatingActionButton.setOnClickListener {
            binding.webView.reload()
        }

        binding.webView.run {
            setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN) {
                    when (keyCode) {
                        KeyEvent.KEYCODE_BACK -> if (canGoBack()) {
                            goBack()
                            return@setOnKeyListener true
                        }
                    }
                }
                false
            }

            // Add WebView ScrollChangeListener M
            binding.webView.setOnScrollChangeListener { _, scrollX, scrollY, oldScrollX, oldScrollY ->
                Log.d(
                    "TAG",
                    "ScrollX $scrollX, ScrollY $scrollY oldScrollX $oldScrollX oldScrollY $oldScrollY"
                )
            }

            setOnWebViewListener(presenter)
            webChromeClient = CustomWebChromeClient(requireContext(), presenter)
            webViewClient = CustomWebViewClient(presenter)
            defaultInit(WebSettings.LOAD_DEFAULT)
        }

        presenter.defaultLoadUrl()
    }

    /**
     * WebView Can go back.
     */
    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    /**
     * WebView go back.
     */
    fun goBack() {
        binding.webView.goBack()
    }

    private fun validationUrl() {
        presenter.validationUrl(etUrlView.text.toString())
    }

    override fun loadUrl(url: String) {
        binding.webView.loadUrl(url)
        etUrlView.hideKeyboard()
    }

    override fun setUrl(url: String?) {
        etUrlView.setText(url)
    }

    override fun webViewScrollChanged(isTop: Boolean) {
        if (isTop) {
            floatingActionButton.show()
        } else {
            floatingActionButton.hide()
        }
    }

    override fun webViewProgressChanged(newProgress: Int) {
        loadingProgressBar.progress = newProgress
        if (newProgress >= 100) {
            loadingProgressBar.visibility = View.GONE
        } else if (loadingProgressBar.visibility != View.VISIBLE) {
            loadingProgressBar.visibility = View.VISIBLE
        }
    }

    fun urlShare() {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, binding.webView.title)
        sendIntent.putExtra(Intent.EXTRA_TEXT, binding.webView.url)
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "Share link!"))
    }
}