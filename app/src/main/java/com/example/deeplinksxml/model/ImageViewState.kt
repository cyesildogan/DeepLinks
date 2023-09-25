package com.example.deeplinksxml.model

data class ImageViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = true,
    val imageList: Model? = null,
    val error: String = "",
    val imageId: String =""
    )