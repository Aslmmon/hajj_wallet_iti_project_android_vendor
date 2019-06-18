package com.example.finalproject.Login.SignUpActivity

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Model.UserLogin.UserSignUp
import com.example.finalproject.Model.UserSignUp.SignUpResponse
import com.example.finalproject.Retrofit.service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {

    private val _response = MutableLiveData<SignUpResponse>()
    val response: LiveData<SignUpResponse>
        get() = _response


    fun signUp(vendor: UserSignUp, context: Context) {


        Log.i("eshta","Sending Data is ${vendor}")
        service.retrofitService.SignUpVendor(vendor)
                .enqueue(object : Callback<SignUpResponse> {
                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                        Toast.makeText(context, t.message + "Try Again", Toast.LENGTH_SHORT).show()

                    }

                    override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                        if (response.isSuccessful) {
                            _response.value = response.body()
                            Toast.makeText(context, "Account Created Successfully , Waiting for Approval", Toast.LENGTH_SHORT).show()
                            Log.i("eshta", "data return is ${response.body()}")

                        } else {
                            Toast.makeText(context,"The Error is  ${response.message()}",Toast.LENGTH_SHORT).show()
                            Log.i("eshta", "Errrrrooooorr   data return is ${response.body()}")
                            Log.i("eshta", "Errrrrooooorr   data return is ${response.errorBody()}")
                            Log.i("eshta", "Errrrrooooorr   data return is ${response.message()}")
                            Log.i("eshta", "Errrrrooooorr   data return is ${response.body()}")

                        }
                    }

                })
    }
}



