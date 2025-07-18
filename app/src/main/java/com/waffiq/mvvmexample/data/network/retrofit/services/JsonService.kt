package com.waffiq.mvvmexample.data.network.retrofit.services

import com.waffiq.mvvmexample.data.network.responses.DetailResponse
import com.waffiq.mvvmexample.data.network.responses.ResponseItemResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonService {

  @GET("/todos")
  suspend fun getList(): List<ResponseItemResponse>

  @GET("todos/{id}")
  suspend fun getDetail(
    @Path("id") id: Int
  ): DetailResponse
}