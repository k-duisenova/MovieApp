package com.example.movieapp.model.data

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MoviesData>,
    @SerializedName("total_pages") val pages: Int
)
