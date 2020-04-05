package tech.thdev.app.view

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
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import tech.thdev.app.R
import tech.thdev.app.view.presenter.MainContract
import tech.thdev.app.view.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    private var windowView: View? = null
    private var manager: WindowManager? = null

    private val presenter: MainContract.Presenter by lazy {
        MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        presenter.view = this

        initView()
    }

    private fun initView() {
        btn_start_overlay.setOnClickListener {
            presenter.startOverlayWindowService(this@MainActivity)
        }

        btn_prev_version.setOnClickListener {
            showStartOverlay()
        }
    }

    override fun showStartOverlay() {
        initWindowLayout(getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun showObtainingPermissionOverlayWindow() {
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:$packageName")
        )
        startActivityForResult(intent, REQ_CODE_OVERLAY_PERMISSION)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        when (requestCode) {
            REQ_CODE_OVERLAY_PERMISSION -> presenter.onOverlayResult(
                this
            )
            else -> {
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Window View 를 초기화 한다. X, Y 좌표는 0, 0으로 지정한다.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initWindowLayout(layoutInflater: LayoutInflater) {
        windowView = layoutInflater.inflate(R.layout.window_view, null, false)
        val windowViewLayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
            30, 30,  // X, Y 좌표

            /**
             * Android O 부터 권한 변경으로 SYSTEM_ALERT을 사용하지 못하고, TYPE_APPLICATION_OVERLAY을 사용해야 함.
             */
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY.takeIf { Build.VERSION.SDK_INT >= Build.VERSION_CODES.O }
                ?: WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )
        windowViewLayoutParams.gravity = Gravity.TOP or Gravity.START
        manager!!.addView(windowView, windowViewLayoutParams)
        initWindowCreate()
    }

    private fun initWindowCreate() {
        val button =
            windowView!!.findViewById<View>(R.id.btn_hide) as Button
        button.setOnClickListener {
            if (windowView != null && manager != null) {
                manager!!.removeView(windowView)
            }
            windowView = null
        }
    }

    companion object {
        const val REQ_CODE_OVERLAY_PERMISSION = 9999
    }
}