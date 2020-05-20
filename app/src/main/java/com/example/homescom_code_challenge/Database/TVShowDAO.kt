package com.example.homescom_code_challenge.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homescom_code_challenge.Model.TVResponse.TVResult

@Dao
interface TVShowDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(movie : List<TVResult>)

    @Query("select * from tv_shows")
    fun getAllMovies() : LiveData<List<TVResult>>
}