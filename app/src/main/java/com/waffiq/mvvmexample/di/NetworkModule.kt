package com.waffiq.mvvmexample.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.waffiq.mvvmexample.BuildConfig
import com.waffiq.mvvmexample.BuildConfig.BASE_URL
import com.waffiq.mvvmexample.data.network.retrofit.services.JsonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
      } else {
        HttpLoggingInterceptor.Level.NONE
      }
    }
  }

  @Provides
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()
  }

  @Provides
  fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
      .readTimeout(TIME_OUT, TimeUnit.SECONDS)
      .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Provides
  fun provideJsonApiService(client: OkHttpClient): JsonService {
    val newClient = client.newBuilder()
      .build()
    val retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
      .client(newClient)
      .build()
    return retrofit.create(JsonService::class.java)
  }

  companion object {
    private const val TIME_OUT = 30L
  }
}
