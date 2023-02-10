package com.example.omdbapp.repository.networkCall

import com.example.omdbapp.repository.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET("/")
    suspend fun getMovie(@Query("apikey") apiKey: String, @Query("s") searchKey: String, @Query("type") type: String, @Query("page") page: Int): MovieList
}