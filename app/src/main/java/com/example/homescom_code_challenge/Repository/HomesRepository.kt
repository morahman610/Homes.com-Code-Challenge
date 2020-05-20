package com.example.homescom_code_challenge.Repository

import android.app.job.JobInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homescom_code_challenge.Model.MovieResponse.MovieResult
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object HomesRepository {

    private var job : CompletableJob? = null

    private var movies : List<MovieResult> = mutableListOf()

    private val _moviesList : MutableLiveData<List<MovieResult>> = MutableLiveData<List<MovieResult>>()
    private val moviesList : LiveData<List<MovieResult>>
    get() = _moviesList

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

    fun cancelJob() {
        job?.cancel()
    }
}