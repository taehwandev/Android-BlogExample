package tech.thdev.kotlin_example_01.view.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import tech.thdev.kotlin_example_01.R
import tech.thdev.kotlin_example_01.constant.Constant

class DetailActivity : AppCompatActivity() {

    val image by lazy {
        findViewById(R.id.img) as ImageView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val url = intent.getStringExtra(Constant.KEY_IMAGE_URL)
        Glide.with(this)
                .load(url)
                .centerCrop()
                .into(image)
    }
}
