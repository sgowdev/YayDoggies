package com.cnb.android.myapplication.di

import com.cnb.android.myapplication.api.DogBreedsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideDogBreedsService(): DogBreedsService {
        return DogBreedsService.create()
    }
}
