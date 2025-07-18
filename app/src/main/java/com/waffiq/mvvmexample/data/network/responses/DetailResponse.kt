package com.waffiq.mvvmexample.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class DetailResponse(

	@Json(name="id")
	val id: Int,

	@Json(name="completed")
	val completed: Boolean,

	@Json(name="title")
	val title: String,

	@Json(name="userId")
	val userId: Int
)
