package tech.thdev.app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import tech.thdev.app.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<SampleAdapterSelectViewModel>()
    private val adapterOne by lazy {
        SampleAdapter(viewModel)
    }
    private val adapterTwo by lazy {
        SampleAdapter(viewModel)
    }

    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.container.recyclerViewDiffDefault.adapter = adapterOne
        binding.container.recyclerViewDiffId.adapter = adapterTwo

        viewModel.updateDiffDefault.observe(this) {
            adapterOne.setItemsDiff(it)
        }
        viewModel.updateDiffId.observe(this) {
            adapterTwo.setItemsDiffId(it)
        }

        viewModel.loadItems()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
