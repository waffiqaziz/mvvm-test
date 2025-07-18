package com.waffiq.mvvmexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waffiq.mvvmexample.data.network.responses.ResponseItemResponse
import com.waffiq.mvvmexample.domain.usecase.INetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
  private val iNetworkUseCase: INetworkUseCase,
) : ViewModel() {

  private val _listResponse = MutableLiveData<List<ResponseItemResponse>>()
  val listResponse: LiveData<List<ResponseItemResponse>> get() = _listResponse

  private val _isLoading = MutableLiveData<Boolean>()
  val isLoading: LiveData<Boolean> get() = _isLoading

  fun getList() {
    viewModelScope.launch(Dispatchers.IO) {
      _isLoading.postValue(true)
      try {
        iNetworkUseCase.getList().collect {
          _listResponse.postValue(it)
        }
      } finally {
        _isLoading.postValue(false)
      }
    }
  }
}
