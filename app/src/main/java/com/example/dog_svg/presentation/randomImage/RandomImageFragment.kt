package com.example.dog_svg.presentation.randomImage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.dog_svg.R
import com.example.dog_svg.databinding.FragmentRandomImageBinding
import com.example.dog_svg.datamodels.DogApiResponse
import com.example.dog_svg.utils.ImageCache
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class RandomImageFragment : Fragment() {

    private val viewModel: randomImageViewModel by viewModels()
    private lateinit var binding: FragmentRandomImageBinding
    lateinit var snackBarExitConfirmation: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomImageBinding.inflate(layoutInflater, container, false)
        viewModel.getRandomDogImage()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.imageUrl.observe(viewLifecycleOwner, Observer{
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.convo_agent_bg) // optional placeholder image
                .error(R.drawable.ic_reset)      // optional error image
                .into(binding.dogImage)
            ImageCache.addToCache(Random(100).toString(), DogApiResponse(it,"success"))
        })

        binding.generateRandomImage.setOnClickListener {
            viewModel.getRandomDogImage()
        }
    }

}