package com.example.homescom_code_challenge.Repository

import com.example.homescom_code_challenge.Model.MovieResponse.MovieResponse
import com.example.homescom_code_challenge.Model.TVResponse.TVResponse
import retrofit2.http.GET

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies() : MovieResponse

    @GET("tv/popular")
    suspend fun getPopularTVShows() : TVResponse
}