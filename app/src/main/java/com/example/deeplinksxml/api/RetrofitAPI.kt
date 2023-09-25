package com.example.deeplinksxml.api

import com.example.deeplinksxml.model.Model
import com.example.deeplinksxml.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("api/")
    suspend fun getImage(@Query("key") apiKey : String = API_KEY): Response<Model>

    @GET("api/{id}")
    suspend fun getImageId(@Query("key") apiKey: String = API_KEY, @Path("id") id : String) : Response<Model>

}