package com.waffiq.mvvmexample.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class ListResponse(
	@Json(name="Response")
	val response: List<ResponseItemResponse>
)
