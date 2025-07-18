package com.waffiq.mvvmexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waffiq.mvvmexample.data.utils.Mappers.toDomain
import com.waffiq.mvvmexample.domain.model.DetailDomain
import com.waffiq.mvvmexample.domain.usecase.INetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val iNetworkUseCase: INetworkUseCase,
) : ViewModel() {

  private val _detailResponse = MutableLiveData<DetailDomain>()
  val detailResponse: LiveData<DetailDomain> get() = _detailResponse

  private val _isLoading = MutableLiveData<Boolean>()
  val isLoading: LiveData<Boolean> get() = _isLoading

  fun getDetail(id: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      _isLoading.postValue(true)
      try {
        iNetworkUseCase.getDetail(id).collect {
          _detailResponse.postValue(it.toDomain())
        }
      } finally {
        _isLoading.postValue(false)
      }
    }
  }
}