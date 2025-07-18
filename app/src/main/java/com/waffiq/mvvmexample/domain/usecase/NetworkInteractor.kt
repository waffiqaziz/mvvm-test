package com.waffiq.mvvmexample.domain.usecase

import com.waffiq.mvvmexample.data.network.responses.DetailResponse
import com.waffiq.mvvmexample.data.network.responses.ResponseItemResponse
import com.waffiq.mvvmexample.data.repository.INetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetworkInteractor @Inject constructor(
  private val networkRepository: INetworkRepository,
) : INetworkUseCase {
  override suspend fun getList(): Flow<List<ResponseItemResponse>> =
    networkRepository.getList()

  override suspend fun getDetail(id: Int): Flow<DetailResponse> =
    networkRepository.getDetail(id)
}
