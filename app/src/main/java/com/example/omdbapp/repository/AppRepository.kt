package com.example.omdbapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.omdbapp.repository.model.Movie
import com.example.omdbapp.repository.model.MovieList
import com.example.omdbapp.repository.networkCall.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository {
    val movieList = MutableLiveData<MovieList>()

    suspend fun getMovieApiCall(search: String, page: Int) : LiveData<MovieList> {
        val call = RetrofitBuilder.apiService.getMovie("b9bd48a6", search, "movie", page)
        if (call != null) {
            movieList.postValue(call)
        }
        return movieList
    }
}