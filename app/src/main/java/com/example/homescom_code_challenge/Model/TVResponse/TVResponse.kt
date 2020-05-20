package com.example.homescom_code_challenge.Model.TVResponse

data class TVResponse(
    val page: Int,
    val results: List<TVResult>,
    val totalPages: Int,
    val totalResults: Int
)