package com.example.homescom_code_challenge.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homescom_code_challenge.Repository.HomesRepository

class PopularTVShowsViewModelFactory(private val homesRepository: HomesRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularTVShowsViewModel::class.java)) {
            return PopularTVShowsViewModel(homesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}