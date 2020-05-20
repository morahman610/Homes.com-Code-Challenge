package com.example.homescom_code_challenge.View

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.homescom_code_challenge.Model.MovieResponse.MovieResult

import com.example.homescom_code_challenge.R
import com.example.homescom_code_challenge.Repository.HomesRepository
import com.example.homescom_code_challenge.ViewModel.PopularMoviesViewModel
import com.example.homescom_code_challenge.ViewModel.PopularMoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import kotlinx.android.synthetic.main.fragment_movie_detail.view.itemAverageVotesTxt

class MovieDetailFragment : Fragment() {

    private lateinit var popularMoviesViewModel: PopularMoviesViewModel
    private lateinit var viewModelFactory: PopularMoviesViewModelFactory
    private lateinit var selectedMovie : MovieResult

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragView = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        getDetails()

    //    initializeUI(fragView)

        return fragView
    }

    private fun initializeUI(selectedMovie : MovieResult) {
     //   val itemTitleText = fragView.itemTitleTxt
      //  val movie = popularMoviesViewModel.selectedMovie
        itemTitleTxt.text = selectedMovie.title
   //     itemTitleTxt.text = selectedMovie.title
   /*     itemOverviewTxt.text = selectedMovie.overview
        itemAverageVotesTxt.text = selectedMovie.vote_average.toString()
        itemAirDateTxt.text = selectedMovie.release_date */

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
