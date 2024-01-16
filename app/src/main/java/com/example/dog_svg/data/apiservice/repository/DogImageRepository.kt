package com.example.dog_svg.data.apiservice.repository
import com.example.dog_svg.datamodels.DogApiResponse

interface DogImageRepository {
    suspend fun getRandomDogImage() : DogApiResponse?
}