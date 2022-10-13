package com.example.challangechapter5.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangechapter5.R
import com.example.challangechapter5.adapter.MovieAdapter
import com.example.challangechapter5.databinding.FragmentHomeBinding
import com.example.challangechapter5.model.DataMovie
import com.example.challangechapter5.model.GetListMovie
import com.example.challangechapter5.service.MovieApi
import com.example.challangechapter5.utils.Constant
import com.example.challangechapter5.utils.PreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var sharedPreferences: PreferencesHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences = PreferencesHelper(requireContext())
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtWelcomeUsername.text = "Hi, ${sharedPreferences.getString(Constant.PREF_USERNAME)}!"
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHome.setHasFixedSize(true)
        getMovieData { movies: List<DataMovie> ->
            binding.rvHome.adapter = MovieAdapter(movies)
        }
        binding.imgProfile.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    private fun getMovieData(callback: (List<DataMovie>) -> Unit) {
        MovieApi.instance.getMovieList().enqueue(object : Callback<GetListMovie> {
            override fun onFailure(call: Call<GetListMovie>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<GetListMovie>,
                response: Response<GetListMovie>
            ) {
                return callback(response.body()!!.movies)
            }

        })
    }
}