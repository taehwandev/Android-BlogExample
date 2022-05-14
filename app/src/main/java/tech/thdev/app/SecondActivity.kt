package tech.thdev.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.thdev.app.databinding.MainActivityBinding
import tech.thdev.ksp.sample.annotation.GenerateAnnotation

@GenerateAnnotation
class SecondActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }
}