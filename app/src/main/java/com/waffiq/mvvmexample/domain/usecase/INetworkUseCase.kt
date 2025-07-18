package com.waffiq.mvvmexample.domain.usecase

import com.waffiq.mvvmexample.data.network.responses.DetailResponse
import com.waffiq.mvvmexample.data.network.responses.ResponseItemResponse
import kotlinx.coroutines.flow.Flow

interface INetworkUseCase {
  suspend fun getList(): Flow<List<ResponseItemResponse>>
  suspend fun getDetail(id: Int): Flow<DetailResponse>
}
