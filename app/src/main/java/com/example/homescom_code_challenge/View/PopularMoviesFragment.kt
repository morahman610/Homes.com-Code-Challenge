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
import com.example.homescom_code_challenge.Adapter.MoviesRecyclerViewAdapter
import com.example.homescom_code_challenge.Adapter.MoviesRecyclerViewAdapter.OnItemClickListener
import com.example.homescom_code_challenge.Model.MovieResponse.MovieResult
import com.example.homescom_code_challenge.R
import com.example.homescom_code_challenge.Repository.HomesRepository
import com.example.homescom_code_challenge.ViewModel.PopularMoviesViewModel
import com.example.homescom_code_challenge.ViewModel.PopularMoviesViewModelFactory

class PopularMoviesFragment : Fragment() {

    private val TAG : String? = "PopularMoviesFragment"

    private lateinit var popularMoviesViewModel: PopularMoviesViewModel
    private lateinit var viewModelFactory: PopularMoviesViewModelFactory
    private lateinit var navController : NavController

 //   lateinit var moviesList : List<MovieResult>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navController = Navigation.findNavController(view!!)
        getData()
     //   inizializeUI(view!!)

    }

    private fun inizializeUI(view: View, moviesList : List<MovieResult> ) {
        val moviesRecyclerView : RecyclerView = view.findViewById(R.id.popularRecyclerView)
        val moviesRecyclerViewAdapter = MoviesRecyclerViewAdapter(requireContext(), moviesList)
        moviesRecyclerView.adapter = moviesRecyclerViewAdapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(context)
        moviesRecyclerViewAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun OnItemClicked(position: Int) {
                popularMoviesViewModel.selectMovie(moviesList.get(position))
                navController.navigate(R.id.popular_movies_to_movieDetailFragment)
            }
        })
    }

    private fun getData() {
        viewModelFactory = PopularMoviesViewModelFactory(HomesRepository)

        popularMoviesViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(PopularMoviesViewModel::class.java)

        popularMoviesViewModel.getPopularMovies().observe(this, Observer {
            val moviesList = it
            inizializeUI(view!!, moviesList)
            popularMoviesViewModel.upsertMovie(it, requireContext())
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        popularMoviesViewModel.cancelJob()
    }

}
