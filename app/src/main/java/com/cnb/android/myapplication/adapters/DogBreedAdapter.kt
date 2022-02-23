package com.cnb.android.myapplication.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cnb.android.myapplication.DetailActivity
import com.cnb.android.myapplication.DetailActivity.Companion.EXTRA_DOG_BREED_KEY
import com.cnb.android.myapplication.DetailActivity.Companion.EXTRA_DOG_BREED_NAME
import com.cnb.android.myapplication.data.DogBreed

class DogAdapter(private val activity: Activity) :
    ListAdapter<DogBreed, DogAdapter.DogBreedViewHolder>(DogBreedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedViewHolder {
        return DogBreedViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DogBreedViewHolder, position: Int) {
        val dogBreed = getItem(position)
        holder.view.findViewById<TextView>(android.R.id.text1).text = dogBreed.dogBreedName

        holder.view.setOnClickListener {
            activity.apply {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(EXTRA_DOG_BREED_NAME, dogBreed.dogBreedName)
                intent.putExtra(EXTRA_DOG_BREED_KEY, dogBreed.dogBreedKey)
                startActivity(intent)
            }
        }
    }

    class DogBreedViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}

private class DogBreedDiffCallback : DiffUtil.ItemCallback<DogBreed>() {

    override fun areItemsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
        return oldItem.dogBreedKey == newItem.dogBreedKey
    }

    override fun areContentsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
        return oldItem == newItem
    }
}
