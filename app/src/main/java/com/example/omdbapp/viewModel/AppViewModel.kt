package com.example.omdbapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.omdbapp.repository.AppRepository
import com.example.omdbapp.repository.model.Movie
import com.example.omdbapp.repository.model.MovieList

class AppViewModel: ViewModel() {

    fun getMovie(search: String, page: Int) : LiveData<MovieList> {
        val servicesLiveData = AppRepository().getMovieApiCall(search, page)
        return servicesLiveData!!
    }
}