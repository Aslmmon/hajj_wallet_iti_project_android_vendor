package com.example.finalproject.Fragments.CashOut

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Model.CheckWallet.Success
import com.example.finalproject.Model.QrCodeData.Data
import com.example.finalproject.Retrofit.service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CashOutViewModel : ViewModel() {
    private val _getCheckResult = MutableLiveData<Success>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val getCheckResult: MutableLiveData<Success>
        get() = _getCheckResult

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)



//    init {
//        getResult()
//    }

    public fun getResult(token:String) {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var tokenGot = "077f89fe6b85ed46cf9c18e17d592ce1886435ae"
            var getResult  = service.retrofitService.getCheckWalletExistence("Token "+tokenGot)
            try {
                // this will run on a thread managed by Retrofit
                val Result = getResult.await()
                Log.i("zamalek ", "success of ${Result}")
                if (Result != null) {
                    _getCheckResult.value = Result.success
                }

            } catch (e: Exception) {
                Log.i("eror", "this is error ${e.message.toString()}")
            }
        }
    }


}
