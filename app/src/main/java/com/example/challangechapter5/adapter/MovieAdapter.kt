package com.example.challangechapter5.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challangechapter5.R
import com.example.challangechapter5.databinding.ListMovieBinding
import com.example.challangechapter5.databinding.ListMovieBinding.inflate
import com.example.challangechapter5.model.DataMovie

class MovieAdapter (val listMovies: List<DataMovie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(private val binding: ListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(dataMovie: DataMovie) {
            binding.titleMovie.text = dataMovie.title
            binding.releaseMovie.text = "Release at: " + dataMovie.release
            Glide.with(itemView).load(IMAGE_BASE + dataMovie.poster).into(binding.imgMovie)
            binding.cardView.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt("ID", dataMovie.id.toString().toInt())
                }
                it.findNavController().navigate(R.id.action_homeFragment_to_detailMovieFragment, bundle)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(listMovies.get(position))
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }
}