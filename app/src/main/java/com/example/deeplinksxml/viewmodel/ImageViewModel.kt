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
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository, application: Application
) : BaseViewModel(application){

    private val _imageState = MutableStateFlow(ImageViewState())
    val imageState : StateFlow<ImageViewState> = _imageState.asStateFlow()

    suspend fun getImage(){
        repository.getImage().collect{result->
            when(result){
                is Resource.Success -> {
                    _imageState.value = result.data?.let {
                        ImageViewState(isSuccess = true,isLoading = false, imageList = it, error = "")
                    }!!
                }
                is Resource.Error -> {
                    _imageState.update {
                        it.copy(
                            error = "Error!!"
                        )
                    }
                }
                is Resource.Loading -> {
                    _imageState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
            }

        }
    }


}