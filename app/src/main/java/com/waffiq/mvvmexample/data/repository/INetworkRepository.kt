package com.waffiq.mvvmexample.data.repository

import com.waffiq.mvvmexample.data.network.responses.DetailResponse
import com.waffiq.mvvmexample.data.network.responses.ResponseItemResponse
import kotlinx.coroutines.flow.Flow

interface INetworkRepository {
  suspend fun getList(): Flow<List<ResponseItemResponse>>
  suspend fun getDetail(id: Int): Flow<DetailResponse>
}
