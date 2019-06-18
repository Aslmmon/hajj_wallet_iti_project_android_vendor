package com.example.finalproject.Fragments.CashOut.StepperFragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.finalproject.R
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import kotlinx.android.synthetic.main.fragment_second.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SecondFragment : Fragment(), BlockingStep {
    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {

    }

    override fun onSelected() {
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {
        val parentActivity = (activity as CreateWalletActivity)
        parentActivity.createWalletbody.dob_day = day.text.toString()
        parentActivity.createWalletbody.dob_month = Month.text.toString()
        parentActivity.createWalletbody.dob_year= year.text.toString()
        parentActivity.createWalletbody.ssn_last_numbers = ssnLastNumber.text.toString()
        parentActivity.createWalletbody.pin_code= pinCode.text.toString()

      //  Toast.makeText(activity,"Done Added",Toast.LENGTH_SHORT).show()
        callback!!.complete()
        //parentActivity.finish()

    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {
    }

    override fun verifyStep(): VerificationError? {
        return null
    }

    override fun onError(error: VerificationError) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }


}
