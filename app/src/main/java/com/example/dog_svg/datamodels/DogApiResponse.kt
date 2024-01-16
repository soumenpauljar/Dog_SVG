package com.example.dog_svg.datamodels

import com.google.gson.annotations.SerializedName

data class DogApiResponse(
    @SerializedName("message") val message: String,
    val status: String
)
