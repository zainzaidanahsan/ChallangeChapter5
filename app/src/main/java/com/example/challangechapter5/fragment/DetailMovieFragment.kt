package com.example.challangechapter5.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.challangechapter5.R
import com.example.challangechapter5.databinding.FragmentDetailMovieBinding
import com.example.challangechapter5.model.GetDetailMovie
import com.example.challangechapter5.service.MovieApi
import com.example.challangechapter5.viewModel.MovieViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieFragment : Fragment() {


    lateinit var binding: FragmentDetailMovieBinding
    lateinit var viewModel: MovieViewModel
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        val id = arguments?.getInt("ID")
        if (id != null) {
            response(id)
        }
    }

    private fun response(id: Int) {
        MovieApi.instance.getMovieDetail(id)
            .enqueue(object : Callback<GetDetailMovie> {
                override fun onResponse(
                    call: Call<GetDetailMovie>,
                    response: Response<GetDetailMovie>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.d(ContentValues.TAG, "onResponse: ${responseBody.toString()}")
                            binding.apply {
                                titleMovie.text = responseBody.title.toString()
                                releaseDataMovie.text = responseBody.releaseDate.toString()
                                Glide.with(requireContext())
                                    .load(IMAGE_BASE + responseBody.backdropPath)
                                    .into(binding.movieImage)
                                descMovie.text = responseBody.overview.toString()
                            }
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Movie Tidak Ditemukan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<GetDetailMovie>, t: Throwable) {

                }

            })
    }

}