package com.example.homescom_code_challenge.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homescom_code_challenge.Model.TVResponse.TVResult
import com.example.homescom_code_challenge.Repository.HomesRepository

class PopularTVShowsViewModel(val homesRepository : HomesRepository) : ViewModel() {

    private val TAG : String? = "PopularTVShowsViewModel"

    private val _selectedTVShow = MutableLiveData<TVResult>()
    private val selectedTVShow: LiveData<TVResult>
        get() = _selectedTVShow

    fun getPopularTVShows() : LiveData<List<TVResult>> {

        return homesRepository.getPopularTVShows()
    }

    fun selectTVShow(tvShow : TVResult) {
        _selectedTVShow.value = tvShow
    }

    fun getTVShow(): LiveData<TVResult> {
        return selectedTVShow
    }

    fun searchTVShows(query : String) : LiveData<List<TVResult>> {
        return homesRepository.searchTVShows(query, false, 1)
    }

    fun upsertShows(context: Context, shows : List<TVResult>) {
        homesRepository(context).upsertShows(shows)
    }

    fun cancelJob() {
        homesRepository.cancelJob()
    }
}