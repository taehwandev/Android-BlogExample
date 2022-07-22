package tech.thdev.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.thdev.ksp.sample.annotation.GenerateAnnotation
import tech.thdev.other.databinding.OtherActivityBinding

@GenerateAnnotation
class OtherActivity : AppCompatActivity() {

    private lateinit var binding: OtherActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OtherActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }
}

@GenerateAnnotation
class ClassTest