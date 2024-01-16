package com.example.dog_svg.presentation.imageList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dog_svg.databinding.RandomImageItemBinding
import com.example.dog_svg.datamodels.DogApiResponse

class DogApiResponseAdapter :
    ListAdapter<DogApiResponse, DogApiResponseAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: RandomImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dogApiResponse: DogApiResponse) {
            // Load image using Glide
            Glide.with(binding.imageViewValue)
                .load(dogApiResponse.message)
                .into(binding.imageViewValue)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RandomImageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<DogApiResponse>() {
        override fun areItemsTheSame(oldItem: DogApiResponse, newItem: DogApiResponse): Boolean {
            return oldItem.message == newItem.message
        }

        override fun areContentsTheSame(oldItem: DogApiResponse, newItem: DogApiResponse): Boolean {
            return oldItem == newItem
        }
    }
}