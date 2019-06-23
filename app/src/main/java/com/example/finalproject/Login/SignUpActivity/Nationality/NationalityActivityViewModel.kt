package com.example.finalproject.Login.SignUpActivity.Nationality

import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Model.Nationalities.NationalitiesResponse
import com.example.finalproject.Retrofit.service
import retrofit2.Callback
import retrofit2.Response

class NationalityActivityViewModel :ViewModel() {

    private val _response = MutableLiveData<List<NationalitiesResponse>>()
    val response: LiveData<List<NationalitiesResponse>>
        get() = _response

    fun getNationalities() {
        service.retrofitService.getNationalities().enqueue(object : Callback<List<NationalitiesResponse>> {
            override fun onFailure(call: retrofit2.Call<List<NationalitiesResponse>>, t: Throwable) {
                _response.value = null

            }

            override fun onResponse(call: retrofit2.Call<List<NationalitiesResponse>>, response: Response<List<NationalitiesResponse>>) {
                _response.value = response.body()
            }

        })
    }

}