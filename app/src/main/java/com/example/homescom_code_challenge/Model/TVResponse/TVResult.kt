package com.example.homescom_code_challenge.Model.TVResponse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
data class TVResult(
    val backdrop_path: String,
    val firstAirDate: String?,
    @PrimaryKey
    val id: Int,
    val name: String,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)