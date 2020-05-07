package tech.thdev.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import tech.thdev.app.view.basic.BasicBottomSheetActivity
import tech.thdev.app.view.recycler.RecyclerBottomSheetActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        findViewById<Button>(R.id.btn_basic_bottom_sheet).setOnClickListener {
            startActivity(Intent(this, BasicBottomSheetActivity::class.java))
        }

        findViewById<Button>(R.id.btn_recycler_bottom_sheet).setOnClickListener {
            startActivity(Intent(this, RecyclerBottomSheetActivity::class.java))
        }
    }
}