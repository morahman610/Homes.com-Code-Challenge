package com.example.homescom_code_challenge.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homescom_code_challenge.Repository.HomesRepository

class PopularMoviesViewModelFactory(private val homesRepository: HomesRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularMoviesViewModel::class.java)) {
            return PopularMoviesViewModel(homesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}