package com.example.finalproject.Login.SignUpActivity


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject.Fragments.CashOut.StepperFragments.CreateWalletActivity

import com.example.finalproject.R
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_first.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AccountFragment : Fragment() ,BlockingStep{
    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {

    }

    override fun onSelected() {
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {

    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {
        val parentActivity = (activity as SignUpActivity)
        parentActivity.userSignUp.email= email.text.toString()
        parentActivity.userSignUp.username= username.text.toString()
        parentActivity.userSignUp.first_name= first_name.text.toString()
        parentActivity.userSignUp.last_name= last_name.text.toString()
        parentActivity.userSignUp.phone_number = phone_number.text.toString()
        parentActivity.userSignUp.gender = gender.text.toString()


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
        return inflater.inflate(R.layout.fragment_account, container, false)
    }


}
