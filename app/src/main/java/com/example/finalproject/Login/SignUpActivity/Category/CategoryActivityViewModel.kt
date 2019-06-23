package com.example.finalproject.Login.SignUpActivity.Category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Model.Categories.CategoriesResponse
import com.example.finalproject.Retrofit.service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivityViewModel : ViewModel() {
    private val _response = MutableLiveData<List<CategoriesResponse>>()
    val response: LiveData<List<CategoriesResponse>>
        get() = _response

    fun getCategories() {
        service.retrofitService.getCategories().enqueue(object:Callback<List<CategoriesResponse>>{
            override fun onFailure(call: Call<List<CategoriesResponse>>, t: Throwable) {
                _response.value = null

            }

            override fun onResponse(call: Call<List<CategoriesResponse>>, response: Response<List<CategoriesResponse>>) {
                _response.value = response.body()
            }

        })
    }
}