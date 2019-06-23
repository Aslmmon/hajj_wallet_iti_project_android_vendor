package com.example.finalproject.Fragments.CashOut

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        // sharedPreference = activity!!.getSharedPreferences("token", Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.cash_out_fragment, container, false)
        Log.i("lifecycle", "on CreateView")

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("lifecycle", "on Activity Created")
    }

    override fun onResume() {
        super.onResume()

        viewModel = ViewModelProviders.of(this).get(CashOutViewModel::class.java)
        val textToken = activity!!.intent.getStringExtra("token")
        Log.i("lifecycle", "on Resume")
        viewModel.getResult(textToken)
        viewModel.getCheckResult.observe(this, Observer {
            if (it == null) {
                balance.text = "No Wallet"
                balanceNumber.text = ""
                createWallet.visibility = View.VISIBLE
            } else {
//                (data.moneyPaid / 100.0 ).toString()
                balanceNumber.text = (it.totalBalance / 100.0).toString()
                balance.text = "your balance is "
                createWallet.visibility = View.GONE

            }
        })

        createWallet.setOnClickListener {
            if (!createWallet.isClickable) {
                Toast.makeText(activity, "You Have Wallet Already Created !", Toast.LENGTH_SHORT).show()
            } else {

                val intent = Intent(activity, CreateWalletActivity::class.java)
                intent.putExtra("token", textToken)
                activity!!.startActivity(intent)
            }
        }
        Log.i("lifecycle", "on Start")
    }

}
