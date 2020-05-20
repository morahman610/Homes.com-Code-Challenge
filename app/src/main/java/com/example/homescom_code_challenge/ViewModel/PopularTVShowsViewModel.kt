package com.example.homescom_code_challenge.ViewModel

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
}