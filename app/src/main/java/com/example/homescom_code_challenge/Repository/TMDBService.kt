package com.example.homescom_code_challenge.Repository

import com.example.homescom_code_challenge.Model.MovieResponse.MovieResponse
import com.example.homescom_code_challenge.Model.TVResponse.TVResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies() : MovieResponse

    @GET("tv/popular")
    suspend fun getPopularTVShows() : TVResponse

    @GET("search/tv")
    suspend fun searchTVShows(
        @Query("query") query : String,
        @Query("page") page : Int,
        @Query("include_adult") includeAdult : Boolean) : TVResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query : String,
        @Query("page") page : Int,
        @Query("include_adult") includeAdult : Boolean) : MovieResponse
}