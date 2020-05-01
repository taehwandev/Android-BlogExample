package tech.thdev.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText
import tech.thdev.app.R
import tech.thdev.app.base.view.BaseFragment
import tech.thdev.app.constant.KEY_INTENT_URL
import tech.thdev.app.databinding.FragmentMainBinding
import tech.thdev.app.ui.main.presenter.MainContract
import tech.thdev.app.util.hideKeyboard
import tech.thdev.app.webkit.CustomWebChromeClient
import tech.thdev.app.webkit.CustomWebViewClient

/**
 * Created by Tae-hwan on 8/8/16.
 */
class MainFragment : BaseFragment<MainContract.Presenter>(), MainContract.View {

    companion object {
        const val DEFAULT_URL = "https://thdev.tech/blog-sample/2016-08-11-WebViewJavascriptInterafce/"

        fun newInstance(): MainFragment =
            MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var etUrl: AppCompatEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initView()
    }

    private fun FragmentMainBinding.initView() {
        etUrl = requireActivity().findViewById<AppCompatEditText>(R.id.et_url)
        etUrl.run {
            setOnEditorActionListener { view, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_GO -> {
                        val newUrl = view.text.toString().takeIf { it.isNotEmpty() } ?: DEFAULT_URL
                        loadUrl(newUrl)
                        view.hideKeyboard()
                        true
                    }
                    else -> false
                }
            }
            setText(DEFAULT_URL)
        }
        etKeyword.setOnEditorActionListener { view, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    setKeyword()
                    true
                }
                else -> false
            }
        }

        btnSearch.setOnClickListener {
            setKeyword()
            it.hideKeyboard()
        }

        webView.webViewClient = CustomWebViewClient(presenter)
        webView.setWebChromeClient(CustomWebChromeClient(requireActivity()))

        /*
         * This method can be used to allow JavaScript to control the host application.
         * This is a powerful feature, but also presents a security risk for apps targeting JELLY_BEAN or earlier.
         * Apps that target a version later than JELLY_BEAN are still vulnerable if the app runs on a device running
         * Android earlier than 4.2. The most secure way to use this method is to target JELLY_BEAN_MR1 and to ensure
         * the method is called only when running on Android 4.2 or later.
         * With these older versions, JavaScript could use reflection to access an injected object's public fields.
         * Use of this method in a WebView containing untrusted content could allow an attacker to manipulate
         * the host application in unintended ways, executing Java code with the permissions of the host application.
         * Use extreme care when using this method in a WebView which could contain untrusted content.
         */
        webView.addJavascriptInterface(presenter?.customJavaScript, "WebViewTest")
        webView.init()
        loadUrl(DEFAULT_URL)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_reload, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_reload -> {
                binding.webView.reload()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun loadUrl(url: String) {
        binding.webView.loadUrl(url)
    }

    override fun updateKeyword(keyword: String) {
        binding.etKeyword.setText(keyword)
    }

    override fun changeUrl(url: String) {
        etUrl.setText(url)
    }

    override fun changeWebView(url: String) {
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            putExtra(KEY_INTENT_URL, url)
        }
        startActivity(intent)
    }

    override fun hideKeyboard() {
        binding.webView.hideKeyboard()
    }

    private fun setKeyword() {
        loadUrl("javascript:setKeyword('" + binding.etKeyword!!.text.toString() + "')")
    }
}