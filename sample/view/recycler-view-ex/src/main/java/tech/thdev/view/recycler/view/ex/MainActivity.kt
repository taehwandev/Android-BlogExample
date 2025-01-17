package tech.thdev.view.recycler.view.ex

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tech.thdev.view.recycler.view.ex.adapter.MainAdapter
import tech.thdev.view.recycler.view.ex.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter()
    }

    private val mainTrashAdapter: MainAdapter by lazy {
        MainAdapter()
    }

    private val binding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.recyclerViewOne.run {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        binding.recyclerViewTrash.run {
            adapter = mainTrashAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mainAdapter.setOnClick {
            viewModel.actionClick(it)
        }

        mainTrashAdapter.setOnClick {
            viewModel.actionClickTwo(it)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listOne.collectLatest {
                    mainAdapter.submitList(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.trashList.collectLatest {
                    mainTrashAdapter.submitList(it)
                }
            }
        }

        viewModel.loadData()
    }
}
