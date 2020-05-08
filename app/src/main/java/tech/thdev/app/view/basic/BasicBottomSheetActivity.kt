package tech.thdev.app.view.basic

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import tech.thdev.app.R
import tech.thdev.app.databinding.ActivityBasicBinding
import tech.thdev.app.databinding.BottomBasicBinding
import tech.thdev.app.databinding.ContentBasicBinding

/**
 * Created by tae-hwan on 07/12/2016.
 */
class BasicBottomSheetActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<RelativeLayout>

//    private val rlBottomSheet: RelativeLayout by lazy {
//        findViewById<RelativeLayout>(R.id.rl_bottom_sheet)
//    }

    private lateinit var binding: ActivityBasicBinding
    private lateinit var content: ContentBasicBinding
    private lateinit var bottomSheetBinding: BottomBasicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicBinding.inflate(layoutInflater)
        content = ContentBasicBinding.inflate(layoutInflater, binding.root, true)
        bottomSheetBinding = BottomBasicBinding.inflate(layoutInflater, binding.rlBottomSheet, true)
        setContentView(binding.root)
//        setContentView(R.layout.activity_basic)
//        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        setSupportActionBar(binding.toolbar)

        initView()
    }

    private fun initView() {
//        bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet).apply {
//            addBottomSheetCallback(bottomSheetCallback)
//        }
        bottomSheetBehavior = BottomSheetBehavior.from(binding.rlBottomSheet).apply {
            addBottomSheetCallback(bottomSheetCallback)
        }

        content.btnShow.setOnClickListener {
            // height 만큼 화면에 노출
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
        content.btnHidden.setOnClickListener {
            // 숨김 처리
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN)
        }
        content.btnExpanded.setOnClickListener {
            // 확장
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
    }

    private val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            // Slide ...
            // 1이면 완전 펼쳐진 상태
            // 0이면 peekHeight인 상태
            // -1이면 숨김 상태
            Log.i("TAG", "slideOffset $slideOffset")
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when (newState) {
                BottomSheetBehavior.STATE_EXPANDED -> setTitle(R.string.basic_sample_bottom_title)
                else -> setTitle(R.string.label_basic_activity)
            }
            Log.d("TAG", "newState $newState")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::bottomSheetBehavior.isInitialized) {
            bottomSheetBehavior.removeBottomSheetCallback(bottomSheetCallback)
        }
    }
}