package com.example.finalproject.Fragments.Payments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.finalproject.Model.PaymentsData.PaymentsDummyData
import com.example.finalproject.R
import kotlinx.android.synthetic.main.payments_fragment.*
import java.util.ArrayList


class Payments : Fragment() {

    companion object {
        fun newInstance() = Payments()
    }

    private lateinit var viewModel: PaymentsViewModel
    private lateinit var paymentsArrayAdapter: PaymentsArrayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.payments)
        return inflater.inflate(R.layout.payments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PaymentsViewModel::class.java)
        viewModel.getPayments.observe(this, Observer {
            Log.i("recieved","this is recieved data ${it.size}")
            paymentsArrayAdapter = PaymentsArrayAdapter(context!!, it as ArrayList<PaymentsDummyData>)
            recycler.adapter =paymentsArrayAdapter
            recycler.setHasFixedSize(true)

        })

    }

}
