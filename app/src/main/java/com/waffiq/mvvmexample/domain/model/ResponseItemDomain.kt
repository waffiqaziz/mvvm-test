package com.waffiq.mvvmexample.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseItemDomain(
  val id: Int,
  val completed: Boolean,
  val title: String,
  val userId: Int
):Parcelable
