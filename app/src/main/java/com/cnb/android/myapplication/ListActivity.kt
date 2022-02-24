package com.cnb.android.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cnb.android.myapplication.adapters.DogAdapter
import com.cnb.android.myapplication.databinding.ActivityListBinding
import com.cnb.android.myapplication.viewmodels.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DogAdapter(this)
        binding.list.adapter = adapter
        viewModel.dogBreeds.observe(this) { dogBreeds ->
            binding.progressHorizontal.root.visibility = View.GONE
            if (dogBreeds.success) {
                adapter.submitList(dogBreeds.dogBreeds)
            } else {
                adapter.submitList(null)
                Toast.makeText(this, R.string.toast_error, Toast.LENGTH_SHORT).show()
            }
        }

        fetchData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_retry -> {
                fetchData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchData() {
        binding.progressHorizontal.root.visibility = View.VISIBLE
        viewModel.getListOfDogBreeds()
    }
}
