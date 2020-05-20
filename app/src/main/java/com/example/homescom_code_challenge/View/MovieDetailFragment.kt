package com.example.homescom_code_challenge.View

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.homescom_code_challenge.Model.MovieResponse.MovieResult

import com.example.homescom_code_challenge.R
import com.example.homescom_code_challenge.Repository.HomesRepository
import com.example.homescom_code_challenge.ViewModel.PopularMoviesViewModel
import com.example.homescom_code_challenge.ViewModel.PopularMoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    private lateinit var popularMoviesViewModel: PopularMoviesViewModel
    private lateinit var viewModelFactory: PopularMoviesViewModelFactory
    private lateinit var selectedMovie : MovieResult

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragView = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        return fragView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getDetails()
    }

    private fun initializeUI(selectedMovie : MovieResult) {
        val uri = Uri.parse("https://image.tmdb.org/t/p/w92${selectedMovie.poster_path}")

        itemTitleTxt.text = selectedMovie.title
        itemTitleTxt.text = selectedMovie.title
        itemOverviewTxt.text = selectedMovie.overview
        itemAverageVotesTxt.text = selectedMovie.vote_average.toString()
        itemAirDateTxt.text = selectedMovie.release_date
        Glide.with(this).load(uri).into(itemImage)

    }

    private fun getDetails() {
        viewModelFactory = PopularMoviesViewModelFactory(HomesRepository)

        popularMoviesViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(PopularMoviesViewModel::class.java)

        popularMoviesViewModel.getMovie().observe(this, Observer {
            val selectedMovie = it
            initializeUI(selectedMovie)
        })

    }
}
