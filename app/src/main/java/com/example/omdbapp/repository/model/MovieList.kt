package com.example.omdbapp.repository.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("Search")
    val search: List<Movie>,

    @SerializedName("totalResults")
    val totalResult: String,

    @SerializedName("Response")
    val response: String
)
