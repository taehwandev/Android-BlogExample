package tech.thdev.app_kotlin.view

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import tech.thdev.app_kotlin.R
import tech.thdev.app_kotlin.view.presenter.MainContract
import tech.thdev.app_kotlin.view.presenter.MainPresenter
import tech.thdev.base.view.BasePresenterActivity

class MainActivity : BasePresenterActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    val REQ_CODE_OVERLAY_PERMISSION = 9999

    private lateinit var windowView: View

    override fun onCreatePresenter() = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btn_start_overlay.setOnClickListener {
            presenter?.startOverlayWindowService(this@MainActivity)
        }

        btn_prev_version.setOnClickListener {
            showStartOverlay()
        }
    }

    override fun showStartOverlay() {
        initWindowLayout(getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun showObtainingPermissionOverlayWindow() {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName))
        startActivityForResult(intent, REQ_CODE_OVERLAY_PERMISSION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            REQ_CODE_OVERLAY_PERMISSION -> presenter?.onOverlayResult(this)

            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    /**
     * Window View 를 초기화 한다. X, Y 좌표는 0, 0으로 지정한다.
     */
    private fun initWindowLayout(layoutInflater: LayoutInflater) {
        windowView = layoutInflater.inflate(R.layout.window_view, null)
        val windowViewLayoutParams: WindowManager.LayoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                30, 30, // X, Y 좌표
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT)
        windowViewLayoutParams.gravity = Gravity.TOP or Gravity.START
        windowManager.addView(windowView, windowViewLayoutParams)

        initWindowCreate()
    }

    private fun initWindowCreate() {
        val button = windowView.findViewById(R.id.btn_hide) as Button
        button.setOnClickListener {
            windowManager?.removeView(windowView)
        }
    }
}
