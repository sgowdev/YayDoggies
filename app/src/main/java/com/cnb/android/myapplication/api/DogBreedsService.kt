package com.cnb.android.myapplication.api

import com.cnb.android.myapplication.data.ListAllBreedsResponse
import com.cnb.android.myapplication.data.RandomImageResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedsService {

    @GET("breeds/list/all")
    suspend fun listAllBreeds(): ListAllBreedsResponse

    @GET("breed/{name}/images/random")
    suspend fun randomImageUrlForDogBreed(
        @Path(
            "name",
            encoded = true
        ) dogBreedName: String
    ): RandomImageResponse

    companion object {
        private const val BASE_URL = "https://dog.ceo/api/"

        fun create(): DogBreedsService {
            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client: OkHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(interceptor)
            }.build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DogBreedsService::class.java)
        }
    }
}
