package com.waffiq.mvvmexample.domain.model

data class DetailDomain(
	val id: Int,
	val completed: Boolean,
	val title: String,
	val userId: Int
)
