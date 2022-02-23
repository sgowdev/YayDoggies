package com.cnb.android.myapplication.data

import com.cnb.android.myapplication.api.DogBreedsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogBreedsRepository @Inject constructor(private val service: DogBreedsService) {
    suspend fun getListOfAllDogBreeds(): Result<ListAllBreedsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(service.listAllBreeds())
            } catch (e: Exception) {
                Result.Error(Exception("Network request failed"))
            }
        }
    }

    suspend fun getRandomImageUrlForDogBreed(dogBreedKey: String): Result<RandomImageResponse> {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(service.randomImageUrlForDogBreed(dogBreedKey))
            } catch (e: Exception) {
                Result.Error(Exception("Network request failed"))
            }
        }
    }
}
