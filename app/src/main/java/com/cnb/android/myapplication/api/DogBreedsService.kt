package com.cnb.android.myapplication.api

import com.cnb.android.myapplication.data.ListAllBreedsResponse
import com.cnb.android.myapplication.data.RandomImageResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedsService {

    @GET("breeds/list/all")
    suspend fun listAllBreeds(): ListAllBreedsResponse

    @GET("breed/{name}/images/random")
    suspend fun randomImageUrlForDogBreed(@Path("name") dogBreedName: String): RandomImageResponse

    companion object {
        private const val BASE_URL = "https://dog.ceo/api/"

        fun create(): DogBreedsService {
            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DogBreedsService::class.java)
        }
    }
}
