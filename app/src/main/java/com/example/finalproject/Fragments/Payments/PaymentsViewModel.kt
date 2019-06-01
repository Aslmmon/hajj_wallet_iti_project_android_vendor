package com.example.finalproject.Fragments.Payments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.finalproject.Model.PaymentsData.PaymentsDummyData
import com.example.finalproject.Model.QrCodeData.Data
import com.example.finalproject.Retrofit.service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PaymentsViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _getPayments = MutableLiveData<List<PaymentsDummyData>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val getPayments: LiveData<List<PaymentsDummyData>>
        get() = _getPayments


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getPaymentsFromApi()
    }

    private fun getPaymentsFromApi() {
        coroutineScope.launch {
            var getThePayments = service.retrofitService2.getPayments()
            try {
                // this will run on a thread managed by Retrofit
                val Result = getThePayments.await()
                Log.i("win", "success of ${Result.size}")
                if (Result != null) {
                    _getPayments.value = Result
                }

            } catch (e: Exception) {
                Log.i("eror", "this is error in payments  ${e.message.toString()}")
            }

        }
    }
}
