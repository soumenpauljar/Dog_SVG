package com.example.dog_svg.presentation.randomImage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dog_svg.datamodels.DogApiResponse
import com.example.dog_svg.usecases.randomImageUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class randomImageViewModel @Inject constructor(val randomImageUsecase: randomImageUsecase) : ViewModel(){

    private val _imageUrl : MutableLiveData<String> = MutableLiveData()
    val imageUrl: LiveData<String>
        get() = _imageUrl

    fun getRandomDogImage() = viewModelScope.launch(Dispatchers.IO) {
        var dogApiResponse: DogApiResponse? = null
        viewModelScope.launch {
            Log.d("soumen",dogApiResponse.toString())
            dogApiResponse = randomImageUsecase.invoke()
            if(dogApiResponse!=null) {
                Log.d("soumen not null",dogApiResponse?.message.toString())
                _imageUrl.postValue(dogApiResponse!!.message)
            }
        }
    }

}