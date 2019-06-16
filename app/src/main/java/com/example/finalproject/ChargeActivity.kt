package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.finalproject.Fragments.CashOut.CashOutViewModel
import com.example.finalproject.Model.walletCharged.CardFields
import com.example.finalproject.Retrofit.service
import kotlinx.android.synthetic.main.activity_charge.*

class ChargeActivity : AppCompatActivity() {

    private lateinit var viewModel: ChargeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charge)
        viewModel = ViewModelProviders.of(this).get(ChargeActivityViewModel::class.java)
//                card_form.setPayBtnClickListner {
//                    it.currency = "USD"
//                    viewModel.getResult(CardFields(it.number,
//                            it.expMonth!!,
//                            it.expYear,
//                            123,
//                            "100",
//                            it.currency,123))
//                    Log.i("fields","card_number ${it.number}," +
//                            "exp_month ${it.expMonth}," +
//                            "exp_year ${it.expYear}," +
//                            "cvc ${it.cvc}," +
//                            "amount ${it.funding}," +
//                            "currency ${it.currency},")
//                }
    }
}
