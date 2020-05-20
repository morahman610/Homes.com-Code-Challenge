package com.example.homescom_code_challenge.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homescom_code_challenge.Model.TVResponse.TVResult
import com.example.homescom_code_challenge.R
import kotlinx.android.synthetic.main.listing_item_view.view.*

class TVShowsRecyclerViewAdapter(private val context: Context, private val tvShowList: List<TVResult>) :
    RecyclerView.Adapter<TVShowsRecyclerViewAdapter.ViewHolder>() {

    lateinit var mListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.listing_item_view, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShowList.get(position)
        val uri = Uri.parse("https://image.tmdb.org/t/p/w92${tvShow.poster_path}")

        holder.tvShowNameTxt.text = tvShow.name
        holder.voteAverageTxt.text = tvShow.vote_average.toString()
        Glide.with(context).load(uri).into(holder.tvShowImage)

        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                mListener.OnItemClicked(position)
            }

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvShowNameTxt = itemView.listingName
        val voteAverageTxt = itemView.voteAverageTxt
        val tvShowImage = itemView.movieImage
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    interface OnItemClickListener {
        fun OnItemClicked(position: Int)
    }
}