package com.example.homescom_code_challenge.Adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homescom_code_challenge.Model.MovieResponse.MovieResult
import com.example.homescom_code_challenge.R
import kotlinx.android.synthetic.main.listing_item_view.view.*

class MoviesRecyclerViewAdapter(
    private val context: Context,
    private val moviesList: List<MovieResult>
) :
    RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder>() {

    lateinit var mListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.listing_item_view, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList.get(position)
        val uri = Uri.parse("https://image.tmdb.org/t/p/w92${movie.poster_path}")

        holder.movieNameTxt.text = movie.title
        holder.voteAverageTxt.text = movie.vote_average.toString()
        Glide.with(context).load(uri).into(holder.movieImage)

        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                mListener.OnItemClicked(position)
            }

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieNameTxt = itemView.listingName
        val voteAverageTxt = itemView.voteAverageTxt
        val movieImage = itemView.movieImage
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    interface OnItemClickListener {
        fun OnItemClicked(position: Int)
    }
}