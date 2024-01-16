package com.example.dog_svg.data.apiservice

import com.example.dog_svg.datamodels.DogApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface DogApiService {
    @GET("api/breeds/image/random")
    suspend fun getRandomDogImage(): DogApiResponse
}
