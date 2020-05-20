package com.example.homescom_code_challenge.Repository

import androidx.lifecycle.LiveData
import com.example.homescom_code_challenge.Model.MovieResponse.MovieResult
import com.example.homescom_code_challenge.Model.TVResponse.TVResult
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object HomesRepository {

    private var job : CompletableJob? = null

    private var movies : List<MovieResult> = mutableListOf()
    private var tvShows : List<TVResult> = mutableListOf()

    fun getPopularMovies() : LiveData<List<MovieResult>> {
        job = Job()

        return object : LiveData<List<MovieResult>>() {
            override fun onActive() {
                super.onActive()
                job?.let {job ->
                    CoroutineScope(IO + job).launch {
                        movies = APIServiceProvider.tmdbService.getPopularMovies().results
                        withContext(Main) {
                            value = movies
                        }
                    }
                }
            }
        }
    }

    fun getPopularTVShows() : LiveData<List<TVResult>> {
        job = Job()

        return object : LiveData<List<TVResult>>() {
            override fun onActive() {
                super.onActive()
                job?.let { job ->
                    CoroutineScope(IO + job).launch {
                        tvShows = APIServiceProvider.tmdbService.getPopularTVShows().results
                        withContext(Main) {
                            value = tvShows
                        }
                    }
                }
            }
        }
    }

    fun cancelJob() {
        job?.cancel()
    }
}