package com.example.dog_svg.usecases

import com.example.dog_svg.data.apiservice.repository.DogImageRepository
import com.example.dog_svg.datamodels.DogApiResponse
import javax.inject.Inject

class randomImageUsecase @Inject constructor(private val dogImageRepository: DogImageRepository) {

    suspend operator fun invoke(): DogApiResponse? {
        val response = dogImageRepository.getRandomDogImage()
        return try {
            response
        } catch (e: Exception) {
            e.printStackTrace()
            response
        }
    }

}