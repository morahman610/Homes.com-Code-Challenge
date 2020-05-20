package com.example.homescom_code_challenge.Model.MovieResponse

data class MovieResponse(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)