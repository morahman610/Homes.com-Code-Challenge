package com.example.homescom_code_challenge.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homescom_code_challenge.Adapter.TVShowsRecyclerViewAdapter
import com.example.homescom_code_challenge.Model.TVResponse.TVResult
import com.example.homescom_code_challenge.R
import com.example.homescom_code_challenge.Repository.HomesRepository
import com.example.homescom_code_challenge.ViewModel.PopularTVShowsViewModel
import com.example.homescom_code_challenge.ViewModel.PopularTVShowsViewModelFactory


class PopularTVShowsFragment : Fragment() {

    private val TAG : String? = "PopularMoviesFragment"
    private lateinit var viewModel : PopularTVShowsViewModel
    private lateinit var viewModelFactory: PopularTVShowsViewModelFactory
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_popular_tvshows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navController = Navigation.findNavController(view!!)
        getData()
    }

    private fun getData() {
        viewModelFactory = PopularTVShowsViewModelFactory(HomesRepository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(PopularTVShowsViewModel::class.java)

        viewModel.getPopularTVShows().observe(this, Observer {tvShows ->
            initUI(view!!, tvShows)
        })
    }

    private fun initUI(view : View, tvShows : List<TVResult>) {
        val tvRecyclerView = view.findViewById<RecyclerView>(R.id.popularTVRecyclerView)
        val recyclerViewAdapter = TVShowsRecyclerViewAdapter(requireContext(), tvShows)
        tvRecyclerView.adapter = recyclerViewAdapter
        tvRecyclerView.layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter.setOnItemClickListener(object : TVShowsRecyclerViewAdapter.OnItemClickListener{
            override fun OnItemClicked(position: Int) {
                viewModel.selectTVShow(tvShows.get(position))
                navController.navigate(R.id.popular_tv_to_TVShowDetailFragment)
            }

        })
    }

}
