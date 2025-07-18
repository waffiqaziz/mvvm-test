package com.waffiq.mvvmexample.data.utils

import com.waffiq.mvvmexample.data.network.responses.DetailResponse
import com.waffiq.mvvmexample.data.network.responses.ListResponse
import com.waffiq.mvvmexample.data.network.responses.ResponseItemResponse
import com.waffiq.mvvmexample.domain.model.DetailDomain
import com.waffiq.mvvmexample.domain.model.ListDomain
import com.waffiq.mvvmexample.domain.model.ResponseItemDomain

object Mappers {

  fun ListResponse.toDomain() =
    ListDomain(
      response = response.map {
        it.toDomain()
      }
    )

  fun ResponseItemResponse.toDomain() =
    ResponseItemDomain(
      id = id,
      completed = completed,
      title = title,
      userId = userId
    )

  fun DetailResponse.toDomain() =
    DetailDomain(
      id = id,
      completed = completed,
      title = title,
      userId = userId
    )
}