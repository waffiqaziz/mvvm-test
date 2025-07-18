package com.waffiq.mvvmexample.domain.di

import com.waffiq.mvvmexample.domain.usecase.NetworkInteractor
import com.waffiq.mvvmexample.domain.usecase.INetworkUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class NetworkUseCaseModule {

  @Binds
  @ViewModelScoped
  abstract fun bindNetworkUseCase(
    networkInteractor: NetworkInteractor
  ): INetworkUseCase
}
