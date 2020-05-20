package com.example.homescom_code_challenge.View

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

    private val TAG : String? = "PopularTVShowsFragment"
    private lateinit var viewModel : PopularTVShowsViewModel
    private lateinit var viewModelFactory: PopularTVShowsViewModelFactory
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navController = Navigation.findNavController(view!!)
        getData()

        val sharedPreference =  context!!.getSharedPreferences("HOMES_SHARED_PREFS", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("LAST_FEATURE", TAG)
        editor.apply()
    }

    private fun getData() {
        viewModelFactory = PopularTVShowsViewModelFactory(HomesRepository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(PopularTVShowsViewModel::class.java)

        viewModel.getPopularTVShows().observe(this, Observer {tvShows ->
            initUI(view!!, tvShows)
            viewModel.upsertShows(requireContext(), tvShows)
        })
    }

    private fun initUI(view : View, tvShows : List<TVResult>) {
        val tvRecyclerView = view.findViewById<RecyclerView>(R.id.popularRecyclerView)
        val recyclerViewAdapter = TVShowsRecyclerViewAdapter(requireContext(), tvShows)
        val searchBtn = view.findViewById<Button>(R.id.searchBtn)
        val searchEditTxt = view.findViewById<EditText>(R.id.searchEditTxt)

        view.findViewById<TextView>(R.id.fragmentLable).text = getString(R.string.shows_fragment_lable)
        tvRecyclerView.adapter = recyclerViewAdapter
        tvRecyclerView.layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter.setOnItemClickListener(object : TVShowsRecyclerViewAdapter.OnItemClickListener{
            override fun OnItemClicked(position: Int) {
                viewModel.selectTVShow(tvShows.get(position))
                navController.navigate(R.id.popular_tv_to_TVShowDetailFragment)
            }

        })

        searchBtn.setOnClickListener {
            viewModel.searchTVShows(searchEditTxt.text.toString()).observe(this, Observer { tvShows ->
                initUI(view!!, tvShows)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }

}
