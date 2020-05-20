package com.example.homescom_code_challenge.View


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

    private lateinit var viewModel: PopularMoviesViewModel
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
        val searchBtn = view.findViewById<Button>(R.id.searchBtn)
        val searchEditTxt = view.findViewById<EditText>(R.id.searchEditTxt)

        view.findViewById<TextView>(R.id.fragmentLable).text = getString(R.string.movies_fragment_lable)
        moviesRecyclerView.adapter = moviesRecyclerViewAdapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(context)
        moviesRecyclerViewAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun OnItemClicked(position: Int) {
                viewModel.selectMovie(moviesList.get(position))
                navController.navigate(R.id.popular_movies_to_movieDetailFragment)
            }
        })

        searchBtn.setOnClickListener {
            viewModel.searchMovies(searchEditTxt.text.toString()).observe(this, Observer { movies ->
                inizializeUI(view!!, movies)
            })
        }
    }

    private fun getData() {
        viewModelFactory = PopularMoviesViewModelFactory(HomesRepository)

        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(PopularMoviesViewModel::class.java)

        viewModel.getPopularMovies().observe(this, Observer {
            val moviesList = it
            inizializeUI(view!!, moviesList)
            viewModel.upsertMovie(it, requireContext())
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }

}
