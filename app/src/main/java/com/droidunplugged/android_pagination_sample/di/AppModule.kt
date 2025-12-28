package com.droidunplugged.android_pagination_sample.di

import com.droidunplugged.android_pagination_sample.data.remote.ApiService
import com.droidunplugged.android_pagination_sample.data.repository.RecipeRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        // No need for KotlinJsonAdapterFactory() if using @JsonClass(generateAdapter = true)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRecipeRepository(apiService: ApiService): RecipeRepository =
        RecipeRepository(apiService)
}