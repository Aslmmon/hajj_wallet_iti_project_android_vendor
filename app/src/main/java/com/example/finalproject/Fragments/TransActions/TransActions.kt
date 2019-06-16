package com.example.finalproject.Fragments.TransActions

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.finalproject.Model.TransactionsData.transactionsData
import com.example.finalproject.R
import kotlinx.android.synthetic.main.payments_fragment.*
import java.util.ArrayList


class TransActions : Fragment() {

    companion object {
        fun newInstance() = TransActions()
    }

    private lateinit var viewModel: TransActionsViewModel
    private lateinit var transactionsArrayAdapter: TransActionsArrayAdapter
    lateinit var sharedPreference: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.payments)
        sharedPreference = activity!!.getSharedPreferences("token", Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.payments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val text = sharedPreference.getString("token","token is here")
        Log.i("hello","text is $text")
     //  viewModel = ViewModelProviders.of(this).get(TransActionsViewModel::class.java)
       // viewModel.getPaymentsFromApi(text)
//        viewModel.getPayments.observe(this, Observer {
//            Log.i("recieved","this is recieved data $it")
//            transactionsArrayAdapter = TransActionsArrayAdapter(context!!, it as ArrayList<transactionsData>)
//            recycler.adapter =transactionsArrayAdapter
//            recycler.setHasFixedSize(true)
//
//        })

    }

}
