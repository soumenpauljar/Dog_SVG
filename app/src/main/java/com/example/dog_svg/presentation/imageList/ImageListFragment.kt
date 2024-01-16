package com.example.dog_svg.presentation.imageList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.dog_svg.databinding.FragmentImageListBinding
import com.example.dog_svg.datamodels.DogApiResponse
import com.example.dog_svg.utils.ImageCache
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListFragment : Fragment() {

    private lateinit var binding: FragmentImageListBinding
    private val adapter = DogApiResponseAdapter()
    private val dogApiResponseList: MutableList<DogApiResponse> = mutableListOf()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageListBinding.inflate(layoutInflater, container, false)
        clickListeners()

        val cacheSnapshot = ImageCache.cache.snapshot()
        for ((key, value) in cacheSnapshot) {
            println("Cache Entry - Key: $key, Value: $value")
            dogApiResponseList.add(value)
        }

        adapter.submitList(dogApiResponseList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(),HORIZONTAL,false)

        return binding.root
    }

    private fun clickListeners() {
        binding.clearCache.setOnClickListener {

            ImageCache.clearCache()
            dogApiResponseList.clear()

            val newDogApiResponseList = dogApiResponseList
            adapter.submitList(newDogApiResponseList.toList())
            adapter.notifyDataSetChanged()
        }
    }


}