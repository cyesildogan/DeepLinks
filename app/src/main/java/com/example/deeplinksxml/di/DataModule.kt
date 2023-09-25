package com.example.deeplinksxml.di

import com.example.deeplinksxml.api.RetrofitAPI
import com.example.deeplinksxml.repository.ImageRepository
import com.example.deeplinksxml.repository.ImageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideApiImageRepository(retrofitAPI: RetrofitAPI) : ImageRepository{
        return ImageRepositoryImpl(retrofitAPI)
    }

}