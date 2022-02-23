package com.cnb.android.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cnb.android.myapplication.data.DogBreedsRepository
import com.cnb.android.myapplication.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject internal constructor(
    private val dogBreedsRepository: DogBreedsRepository
) : ViewModel() {

    private val _dogBreedImageUrl = MutableLiveData<String?>()
    val dogBreedImageUrl: LiveData<String?> = _dogBreedImageUrl

    fun fetchRandomImageOfDogBreed(dogBreedKey: String) {
        viewModelScope.launch {
            val response = dogBreedsRepository.getRandomImageUrlForDogBreed(dogBreedKey)
            if (response is Result.Success) {
                _dogBreedImageUrl.value = response.data.imageUrl
            } else {
                _dogBreedImageUrl.value = null
            }
        }
    }
}
