package com.example.finalproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Model.walletCharged.CardFields
import com.example.finalproject.Model.walletCharged.SuccessWalletCharged
import com.example.finalproject.Retrofit.service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChargeActivityViewModel :ViewModel() {
    private val _response= MutableLiveData<SuccessWalletCharged>()
    val response: LiveData<SuccessWalletCharged>
        get() = _response

       // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    public fun getResult(cardFields: CardFields) {
        coroutineScope.launch {
                        val result  =  service.retrofitService.chargeWallet("Token 077f89fe6b85ed46cf9c18e17d592ce1886435ae"
                                , CardFields(card_number = cardFields.card_number ,
                                exp_month = cardFields.exp_month,
                                exp_year = cardFields.exp_year,
                                cvc = cardFields.cvc,
                                amount= cardFields.amount,
                                currency = "USD",
                                pin_code = 1234))

            try {
                val result = result.await()
                Log.i("result ", "success in Charge  $result")

            } catch (e: Exception) {
                Log.i("eror", " error  in charge Activity ${e.message.toString()}")
            }
        }
    }
}