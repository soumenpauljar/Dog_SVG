package com.example.dog_svg.presentation.homescreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dog_svg.R
import com.example.dog_svg.databinding.FragmentHomeBinding
import com.example.dog_svg.utils.ImageCache
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.generateRandomImage.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_randomImageFragment)
        }

        binding.textView.setOnClickListener {

            val cacheSnapshot = ImageCache.cache.snapshot()

            for ((key, value) in cacheSnapshot) {
                Log.d("soumen-list","Cache Entry - Key: $key, Value: $value")
                println("Cache Entry - Key: $key, Value: $value")
            }
            findNavController().navigate(R.id.action_homeFragment_to_imageListFragment)
        }


        return binding.root
    }

}