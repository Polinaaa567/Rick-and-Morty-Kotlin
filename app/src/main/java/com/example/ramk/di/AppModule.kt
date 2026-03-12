package com.example.ramk.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import com.example.ramk.core.remote.api.ApiService
import com.example.ramk.core.remote.network.RetrofitInstance
import com.example.ramk.core.repository.CharacterRepository
import com.example.ramk.domain.repository.ICharacterRepository

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//    @Provides
//    @Singleton
//    fun provideApiService(): ApiService {
//        return RetrofitInstance.api
//    }
//
//    @Provides
//    @Singleton
//    fun provideCharacterRepository(): ICharacterRepository {
//        return CharacterRepository()
//    }
//}
