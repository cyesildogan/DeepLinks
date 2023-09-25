package com.example.deeplinksxml.repository

import com.example.deeplinksxml.model.Model
import com.example.deeplinksxml.util.Resource
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun getImage() : Flow<Resource<Model>>

    suspend fun getImageId(id: String) : Flow<Resource<Model>>

}