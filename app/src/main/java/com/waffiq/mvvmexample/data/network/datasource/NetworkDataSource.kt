package com.waffiq.mvvmexample.data.network.datasource

import com.waffiq.mvvmexample.data.network.responses.DetailResponse
import com.waffiq.mvvmexample.data.network.responses.ListResponse
import com.waffiq.mvvmexample.data.network.responses.ResponseItemResponse
import com.waffiq.mvvmexample.data.network.retrofit.services.JsonService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataSource @Inject constructor(
  private val jsonService: JsonService,
) : INetworkDataSource {
  override suspend fun getList(): Flow<List<ResponseItemResponse>> = flow {
    emit ( jsonService.getList() )
  }

  override suspend fun getDetail(id: Int): Flow<DetailResponse> = flow{
    emit (jsonService.getDetail(id))
  }
}
