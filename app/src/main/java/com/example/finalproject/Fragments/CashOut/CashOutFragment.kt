package com.example.finalproject.Fragments.CashOut

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.finalproject.ChargeActivity
import com.example.finalproject.Fragments.CashOut.StepperFragments.CreateWalletActivity

import com.example.finalproject.R
import kotlinx.android.synthetic.main.cash_out_fragment.*

class CashOutFragment : Fragment() {

    companion object {
        fun newInstance() = CashOutFragment()
    }

    private lateinit var viewModel: CashOutViewModel
    lateinit var sharedPreference: SharedPreferences


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.cash_out)
       // sharedPreference = activity!!.getSharedPreferences("token", Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.cash_out_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val textToken = activity!!.intent.getStringExtra("token")
        Log.i("hello","token in Cash Out  is $textToken")

        viewModel = ViewModelProviders.of(this).get(CashOutViewModel::class.java)
        viewModel.getResult(textToken)
        viewModel.getCheckResult.observe(this, Observer {
            if(it == null){
                balanceNumber.text= "No Wallet"
                balance.text = ""
            }else {
                balanceNumber.text = it.totalBalance.toString()
            }
        })
        val text = activity!!.intent.getStringExtra("token")
        createWallet.setOnClickListener {
            val intent = Intent (activity, CreateWalletActivity::class.java)
            intent.putExtra("token",text)
            activity!!.startActivity(intent)
        }
    }


}
