package com.cnb.android.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cnb.android.myapplication.databinding.ActivityDetailBinding
import com.cnb.android.myapplication.viewmodels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DOG_BREED_NAME = "EXTRA_DOG_BREED_NAME"
        const val EXTRA_DOG_BREED_KEY = "EXTRA_DOG_BREED_KEY"
    }

    private lateinit var binding: ActivityDetailBinding
    private var dogBreedKey: String = ""
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dogBreedName = intent.getStringExtra(EXTRA_DOG_BREED_NAME)
        title = dogBreedName

        dogBreedKey = intent.getStringExtra(EXTRA_DOG_BREED_KEY) ?: ""

        viewModel.dogBreedImageUrl.observe(this) {
            binding.progressHorizontal.root.visibility = View.GONE
            if (it.isNullOrEmpty()) {
                Toast.makeText(this, R.string.toast_error, Toast.LENGTH_SHORT).show()
            } else {
                binding.image.apply {
                    Glide.with(context)
                        .load(it)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(this)
                }
            }
        }

        fetchData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
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
        viewModel.fetchRandomImageOfDogBreed(dogBreedKey)
    }
}
