package com.example.finalproject.Fragments.TransActions

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.example.finalproject.Model.TransactionsData.Error

import com.example.finalproject.Model.TransactionsData.transactionsData
import com.example.finalproject.Retrofit.service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TransActionsViewModel : ViewModel() {

    private val _getTransactions = MutableLiveData<List<transactionsData>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val getTransactions: LiveData<List<transactionsData>>
        get() = _getTransactions


//
//    private val _getErrorTransactions = MutableLiveData<String>()
//
//    // The external LiveData interface to the property is immutable, so only this class can modify
//    val getErrorTransactions: LiveData<String>
//        get() = _getErrorTransactions

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

//    init {
//        getPaymentsFromApi(token = String)
//    }




    public fun getPaymentsFromApi(token:String) {
        coroutineScope.launch {

            Log.i("shared","token is ${token}")
            //  sharedPreference = getSharedPreferences("token", Context.MODE_PRIVATE)
            //var getThePayments = service.retrofitService.getTransactions("Token "+tokenGot)
            var getResults = service.retrofitService.getTransactions("Token "+token)
            try {
                // this will run on a thread managed by Retrofit //  val Result = getThePayments.await()
                val result = getResults.await()
               // Log.i("win", "success of ${Result.size}")
                if (Result != null) {
                    _getTransactions.value = result
                }else {
                    Log.i("win", "no results")
                }
            } catch (e: Exception) {
                Log.i("eror", "this is error in payments  ${e.message.toString()}")
            }

        }
    }
}
