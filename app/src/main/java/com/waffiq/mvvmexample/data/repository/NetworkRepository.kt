package com.waffiq.mvvmexample.data.repository

import com.waffiq.mvvmexample.data.network.datasource.NetworkDataSource
import com.waffiq.mvvmexample.data.network.responses.DetailResponse
import com.waffiq.mvvmexample.data.network.responses.ResponseItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(
  private val networkDataSource: NetworkDataSource,
) : INetworkRepository {
  override suspend fun getList(): Flow<List<ResponseItemResponse>> =
    networkDataSource.getList().flowOn(Dispatchers.IO)

  override suspend fun getDetail(id: Int): Flow<DetailResponse> =
    networkDataSource.getDetail(id).flowOn(Dispatchers.IO)
}
