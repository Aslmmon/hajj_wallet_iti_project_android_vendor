package com.example.finalproject.Fragments.CashOut.StepperFragments

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Model.CreateWallet.CreateWalletBody
import com.example.finalproject.Model.CreateWallet.CreateWalletData
import com.example.finalproject.Model.CreateWallet.SuccessWalletCreated
import com.example.finalproject.Retrofit.service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateWalletViewModel : ViewModel() {


    private val _response = MutableLiveData<SuccessWalletCreated>()
    val response: LiveData<SuccessWalletCreated>
        get() = _response


      fun CreateWallet( createWalletBody: CreateWalletBody, context: Context,token:String) {
         // var tokenGot = "077f89fe6b85ed46cf9c18e17d592ce1886435ae"
          service.retrofitService.createWallet("Token "+token,createWalletBody).enqueue(object:Callback<SuccessWalletCreated>{
              override fun onFailure(call: Call<SuccessWalletCreated>, t: Throwable) {
                  Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                Log.i("ok","${t.message}")
              }

              override fun onResponse(call: Call<SuccessWalletCreated>, response: Response<SuccessWalletCreated>) {
                  if (response.isSuccessful){
                      _response.value = response.body()
                      Log.i("Ok","Created")
                      Log.i("Ok","Created with response ${response.body()!!.success}")

                      Toast.makeText(context,"Done Created ViewModel ",Toast.LENGTH_SHORT).show()
                  }

              }
        })
    }
}
