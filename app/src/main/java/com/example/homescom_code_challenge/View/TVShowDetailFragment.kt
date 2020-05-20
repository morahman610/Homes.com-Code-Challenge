package com.example.homescom_code_challenge.View

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.homescom_code_challenge.Model.TVResponse.TVResult
import com.example.homescom_code_challenge.R
import com.example.homescom_code_challenge.Repository.HomesRepository
import com.example.homescom_code_challenge.ViewModel.PopularTVShowsViewModel
import com.example.homescom_code_challenge.ViewModel.PopularTVShowsViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class TVShowDetailFragment : Fragment() {

    private lateinit var popularTVShowsViewModel: PopularTVShowsViewModel
    private lateinit var viewModelFactory: PopularTVShowsViewModelFactory
    private lateinit var selectedShow: TVResult

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getDetails()
    }

    private fun initializeUI(selectedShow : TVResult) {
        val uri = Uri.parse("https://image.tmdb.org/t/p/w92${selectedShow.poster_path}")

        itemTitleTxt.text = selectedShow.name
        itemTitleTxt.text = selectedShow.name
        itemOverviewTxt.text = selectedShow.overview
        itemAverageVotesTxt.text = selectedShow.vote_average.toString()
        itemAirDateTxt.text = selectedShow.firstAirDate
        Glide.with(this).load(uri).into(itemImage)

    }

    private fun getDetails() {
        viewModelFactory = PopularTVShowsViewModelFactory(HomesRepository)

        popularTVShowsViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(PopularTVShowsViewModel::class.java)

        popularTVShowsViewModel.getTVShow().observe(this, Observer {
            val selectedShow = it
            initializeUI(selectedShow)
        })

    }

}