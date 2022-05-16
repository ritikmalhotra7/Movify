package com.example.movieapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.complete.newsreporter.utils.Resources
import com.example.movieapplication.database.MovieRepository
import com.example.movieapplication.models.MovieResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(val repo : MovieRepository):ViewModel() {
    var popularMovieLs : MutableLiveData<Resources<MovieResponse>> = MutableLiveData()

    fun getPopular() = viewModelScope.launch {
        popularMovieLs.postValue(Resources.Loading())
        val pm = repo.popularMovies()
        popularMovieLs.postValue(handlePopularReponse(pm))
    }
    private fun handlePopularReponse(response: Response<MovieResponse>):Resources<MovieResponse>{
        if(response.isSuccessful){
            response.body()?.let{ resultResponse->
                return Resources.Success(resultResponse)
            }
        }
        return Resources.Error(response.message())
    }


}