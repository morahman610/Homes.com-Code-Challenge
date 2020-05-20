package com.example.homescom_code_challenge.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homescom_code_challenge.Model.MovieResponse.MovieResult

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(movie : List<MovieResult> )

    @Query("select * from movies ")
    fun getAllMovies() : LiveData<List<MovieResult>>
}