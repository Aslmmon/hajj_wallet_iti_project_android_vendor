package com.example.finalproject.Fragments.CashOut.StepperFragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.finalproject.R
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import kotlinx.android.synthetic.main.fragment_first.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FirstFragment : Fragment(), BlockingStep {
    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {
    }

    override fun onSelected() {
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {
    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {
        val parentActivity = (activity as CreateWalletActivity)
        parentActivity.createWalletbody.country= "US"
        parentActivity.createWalletbody.bank_name= "STRIPE TEST BANK"
        parentActivity.createWalletbody.account_number= "000123456789"
        parentActivity.createWalletbody.routing_number= "110000000"
        parentActivity.createWalletbody.currency = "usd"

        callback!!.goToNextStep()
    }

    override fun verifyStep(): VerificationError? {
        return null
    }

    override fun onError(error: VerificationError) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


}
