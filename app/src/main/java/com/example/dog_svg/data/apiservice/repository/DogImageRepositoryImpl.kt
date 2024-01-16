package com.example.dog_svg.data.apiservice.repository

import android.util.Log
import com.example.dog_svg.data.apiservice.DogApiService
import com.example.dog_svg.datamodels.DogApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class DogImageRepositoryImpl @Inject constructor(val apiService: DogApiService) : DogImageRepository {

    override suspend fun getRandomDogImage(): DogApiResponse? = withContext(Dispatchers.IO) {
        return@withContext try {
                val response = apiService.getRandomDogImage()
                val dogApiResponse = response
                val imageUrl = dogApiResponse?.message
                val imageStatus = dogApiResponse?.status
                println("Random Dog Image URL: $imageUrl")
                dogApiResponse
        } catch (e: HttpException) {
            // Handle HTTP errors
            Log.d("soumen error", e.message.toString())
            println("HTTP error: ${e.message}")
            null
        } catch (e: Exception) {
            // Handle network errors or failures
            Log.d("soumen error", e.message.toString())
            println("Network error: ${e.message}")
            null
        }
    }
}