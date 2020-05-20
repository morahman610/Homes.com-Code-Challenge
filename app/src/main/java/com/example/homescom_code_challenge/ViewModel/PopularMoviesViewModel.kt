package com.example.homescom_code_challenge.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.homescom_code_challenge.Model.MovieResponse.MovieResult
import com.example.homescom_code_challenge.Repository.HomesRepository
import kotlin.math.log

class PopularMoviesViewModel(val homesRepository : HomesRepository) : ViewModel() {

    private val TAG : String? = "PopularMoviesViewModel"

    private val _popularMoviesList : MutableLiveData<List<MovieResult>> = MutableLiveData()
    private val popularMoviesList : LiveData<List<MovieResult>>
    get() = _popularMoviesList

    private val _selectedMovie = MutableLiveData<MovieResult>()
    private val selectedMovie : LiveData<MovieResult>
    get() = _selectedMovie

    fun getPopularMovies() : LiveData<List<MovieResult>> {

        val movies = homesRepository.getPopularMovies()

        return movies
    }

    fun selectMovie(movie : MovieResult) {
        _selectedMovie.value = movie
        Log.d(TAG, selectedMovie.value!!.title)
    }

    fun getMovie() : LiveData<MovieResult> {
        return selectedMovie
    }

}