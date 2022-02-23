package com.cnb.android.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cnb.android.myapplication.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject internal constructor(
    private val dogBreedsRepository: DogBreedsRepository
) : ViewModel() {

    private val _dogBreeds = MutableLiveData<DogBreedsUi>()
    val dogBreeds: LiveData<DogBreedsUi> = _dogBreeds

    fun getListOfDogBreeds() {
        viewModelScope.launch {
            val response = dogBreedsRepository.getListOfAllDogBreeds()
            if (response is Result.Success) {
                _dogBreeds.value = transformDogBreedsResponseToUiData(response.data)
            } else {
                _dogBreeds.value = DogBreedsUi(null, false)
            }
        }
    }

    private fun transformDogBreedsResponseToUiData(response: ListAllBreedsResponse): DogBreedsUi {
        if (response.status != "success") {
            return DogBreedsUi(null, false)
        }

        val dogBreeds = mutableListOf<DogBreed>()
        response.message.forEach { (key, value) ->
            val baseBreedNameCap = key.capitalize()
            if (value.isEmpty()) {
                dogBreeds.add(DogBreed(dogBreedName = baseBreedNameCap, dogBreedKey = key))
            } else {
                value.forEach {
                    val specializedNameCap = it.capitalize()
                    dogBreeds.add(
                        DogBreed(
                            dogBreedName = "$specializedNameCap $baseBreedNameCap",
                            dogBreedKey = "$key/$it"
                        )
                    )
                }
            }
        }

        return DogBreedsUi(dogBreeds, true)
    }
}
