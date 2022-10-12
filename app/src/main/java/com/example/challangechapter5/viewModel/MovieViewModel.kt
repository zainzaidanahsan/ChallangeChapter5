package com.example.challangechapter5.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challangechapter5.model.GetDetailMovie
import com.example.challangechapter5.service.MovieApi
import com.example.challangechapter5.service.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private val _getDetailMovieLiveData = MutableLiveData<GetDetailMovie?>()
    val getDetailMovieLiveData: LiveData<GetDetailMovie?> = _getDetailMovieLiveData


    fun getMovieDetailById(id: Int) {
        MovieApi.instance.getMovieDetail(id)
            .enqueue(object : Callback<GetDetailMovie> {
                override fun onResponse(
                    call: Call<GetDetailMovie>,
                    response: Response<GetDetailMovie>
                ) {
                    if (response.isSuccessful) {
                        _getDetailMovieLiveData.value = response.body()
                    } else {
                        _getDetailMovieLiveData.value = null
                    }
                }

                override fun onFailure(call: Call<GetDetailMovie>, t: Throwable) {

                }

            })
    }
}