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

    fun getMovieApiCall(search: String, page: Int) : LiveData<MovieList> {
        val call = RetrofitBuilder.apiService.getMovie("b9bd48a6", search, "movie", page)
        call.enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.isSuccessful){
                    val movieData = response.body()
                    movieList.postValue(movieData)
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("MOVIES", t.message.toString())
            }
        })
        return movieList
    }
}