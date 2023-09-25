package com.example.deeplinksxml.repository

import com.example.deeplinksxml.api.RetrofitAPI
import com.example.deeplinksxml.model.Model
import com.example.deeplinksxml.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val retrofitAPI: RetrofitAPI
): BaseRepository(), ImageRepository {
    override suspend fun getImage(): Flow<Resource<Model>> {
        return getResult {
            retrofitAPI.getImage()
        }
    }

    override suspend fun getImageId(id: String): Flow<Resource<Model>> {
        return getResult {
            retrofitAPI.getImageId(id = id)
        }
    }
}