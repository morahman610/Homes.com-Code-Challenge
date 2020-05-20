package com.example.homescom_code_challenge.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homescom_code_challenge.Model.MovieResponse.MovieResult
import com.example.homescom_code_challenge.Model.TVResponse.TVResult

@Database(
    entities = [MovieResult::class, TVResult::class],
    version = 1
)
abstract class HomesDB : RoomDatabase() {

    abstract fun movieDAO() : MovieDAO
    abstract fun tvShowDAO() : TVShowDAO

    companion object {

        @Volatile private var instance : HomesDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance
                ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): HomesDB {
            return Room.databaseBuilder(context.applicationContext, HomesDB::class.java, "homes.db")
                .build()
        }

    }
}