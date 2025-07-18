package com.waffiq.mvvmexample.data.network.datasource

import com.waffiq.mvvmexample.data.network.responses.DetailResponse
import com.waffiq.mvvmexample.data.network.responses.ResponseItemResponse
import kotlinx.coroutines.flow.Flow

interface INetworkDataSource {
  suspend fun getList(): Flow<List<ResponseItemResponse>>
  suspend fun getDetail(id: Int): Flow<DetailResponse>
}
