package com.example.deeplinksxml.viewmodel

import android.app.Application
import com.example.deeplinksxml.model.ImageViewState
import com.example.deeplinksxml.repository.ImageRepository
import com.example.deeplinksxml.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    application: Application,
    private val repository: ImageRepository
) : BaseViewModel(application) {

    private val _imageIdState = MutableStateFlow(ImageViewState())
    val imageIdState: StateFlow<ImageViewState> = _imageIdState.asStateFlow()

   suspend fun getImageId(id: String) {
        repository.getImageId(id).collect {model->
            when (model) {
                is Resource.Success -> {
                    _imageIdState.value = model.data?.let {
                        ImageViewState(
                            imageList = it,
                            isLoading = false,
                            isSuccess = true,
                            error = ""
                        )
                    }!!
                }
                is Resource.Error ->{
                    _imageIdState.update {
                        it.copy(
                            error = "Error!!"
                        )
                    }
                }
                is Resource.Loading -> {
                    _imageIdState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

}