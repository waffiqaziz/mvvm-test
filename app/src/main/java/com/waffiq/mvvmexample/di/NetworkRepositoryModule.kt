package com.waffiq.mvvmexample.di

import com.waffiq.mvvmexample.data.repository.INetworkRepository
import com.waffiq.mvvmexample.data.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("Unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkRepositoryModule {

  @Binds
  abstract fun bindNetworkRepository(
    networkRepository: NetworkRepository
  ): INetworkRepository
}
