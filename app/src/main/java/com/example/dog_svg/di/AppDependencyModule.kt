package com.example.dog_svg.di

import android.content.Context
import com.example.dog_svg.data.apiservice.DogApiService
import com.example.dog_svg.data.apiservice.repository.DogImageRepository
import com.example.dog_svg.data.apiservice.repository.DogImageRepositoryImpl
import com.example.dog_svg.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDependencyModule {

    @Provides
    @Singleton
    fun provideDogApi(
        @ApplicationContext context: Context
    ): DogApiService{
        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.MINUTES)
            .connectionPool(ConnectionPool(5, 5, TimeUnit.MINUTES))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(client)
            .build()

        return retrofit.create(DogApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDogImageRepository(api: DogApiService): DogImageRepository {
        return DogImageRepositoryImpl(api)
    }


}