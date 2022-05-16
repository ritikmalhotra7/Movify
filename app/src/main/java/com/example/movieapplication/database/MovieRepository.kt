package com.example.movieapplication.database

import com.example.movieapplication.api.RetrofitInstance

class MovieRepository(val db : MovieDatabase) {

    suspend fun  popularMovies() = RetrofitInstance.api.getPopular()
    suspend fun  topRatedMovies() = RetrofitInstance.api.getTopRated()

}