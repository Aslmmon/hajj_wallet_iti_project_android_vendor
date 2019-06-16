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
    // TODO: Implement the ViewModel
//
//    private val _getPayments = MutableLiveData<List<transactionsData>>()
//
//
//    // The external LiveData interface to the property is immutable, so only this class can modify
//    val getPayments: LiveData<List<transactionsData>>
//        get() = _getPayments


    private val _getErrorTransactions = MutableLiveData<String>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val getErrorTransactions: LiveData<String>
        get() = _getErrorTransactions

    init {
        getPaymentsFromApi()
    }


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private fun getPaymentsFromApi() {
        coroutineScope.launch {
            var tokenGot = "077f89fe6b85ed46cf9c18e17d592ce1886435ae"
            //var getThePayments = service.retrofitService.getTransactions("Token "+tokenGot)
            var getErrorTrans = service.retrofitService.getErrorTransactions("Token "+tokenGot)
            try {
                // this will run on a thread managed by Retrofit
              //  val Result = getThePayments.await()
                val errorResult = getErrorTrans.await()
               // Log.i("win", "success of ${Result.size}")
                if (Result != null) {
                   // _getPayments.value = Result
                    _getErrorTransactions.value = errorResult.message

                }else {
                    Log.i("win", "no results")

                }

            } catch (e: Exception) {
                Log.i("eror", "this is error in payments  ${e.message.toString()}")
            }

        }
    }
}
