package tech.thdev.app.view.recycler

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import tech.thdev.app.R
import tech.thdev.app.data.BottomData
import tech.thdev.app.view.recycler.adapter.RecyclerBottomSheetAdapter

/**
 * Created by tae-hwan on 07/12/2016.
 */
class RecyclerBottomSheetActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<RelativeLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        initView()
    }

    private fun initView() {
        val rlBottomSheet = findViewById<RelativeLayout>(R.id.rl_bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet).apply {
            peekHeight = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 300f, resources.displayMetrics
            ).toInt()

            addBottomSheetCallback(bottomSheetCallback)
        }

        // BottomSheet에 사용할 Adapter을 정의한다
        val adapter = RecyclerBottomSheetAdapter().apply {
            setItem(BottomData("Facebook", R.drawable.icon_01))
            setItem(BottomData("Google", R.drawable.icon_02))
            setItem(BottomData("Pinterest", R.drawable.icon_03))
            setItem(BottomData("Google+", R.drawable.icon_04))
            setItem(BottomData("LinkedIn", R.drawable.icon_05))
            setItem(BottomData("Twitter", R.drawable.icon_06))
        }
        findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter

        findViewById<Button>(R.id.btn_show).setOnClickListener {
            // height 만큼 화면에 노출
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
        findViewById<Button>(R.id.btn_hidden).setOnClickListener {
            // 숨김 처리
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN)
        }
        findViewById<Button>(R.id.btn_expanded).setOnClickListener {
            // 확장
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (::bottomSheetBehavior.isInitialized) {
            bottomSheetBehavior.removeBottomSheetCallback(bottomSheetCallback)
        }
    }

    private val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {

        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when (newState) {
                BottomSheetBehavior.STATE_EXPANDED -> setTitle(R.string.recycler_sample_bottom_title)
                else -> setTitle(R.string.label_recycler_activity)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Bottom Sheet state change.
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        } else {
            super.onBackPressed()
        }
    }
}