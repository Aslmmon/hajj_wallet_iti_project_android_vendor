package com.example.finalproject.Fragments.CashOut

import android.content.Intent
import android.os.Bundle
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.cash_out)
        return inflater.inflate(R.layout.cash_out_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CashOutViewModel::class.java)
        viewModel.getCheckResult.observe(this, Observer {
            balanceNumber.text = it.totalBalance.toString()
        })
        createWallet.setOnClickListener {
            val intent = Intent (activity, CreateWalletActivity::class.java)
            activity!!.startActivity(intent)
        }
    }


}
