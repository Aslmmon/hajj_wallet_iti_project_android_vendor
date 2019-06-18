package com.example.finalproject.Login.SignUpActivity


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finalproject.Fragments.CashOut.StepperFragments.CreateWalletActivity

import com.example.finalproject.R
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second_account.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AccountFragment : Fragment(), BlockingStep {
    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {

    }

    override fun onSelected() {
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {

    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {
        if (isUserDataValid()) {
            val parentActivity = (activity as SignUpActivity)
            parentActivity.userSignUp.email = email.text.toString()
            parentActivity.userSignUp.username = username.text.toString()
            parentActivity.userSignUp.first_name = first_name.text.toString()
            parentActivity.userSignUp.last_name = last_name.text.toString()
            parentActivity.userSignUp.phone_number = phone_number.text.toString()
            parentActivity.userSignUp.gender = gender.text.toString()


            callback!!.goToNextStep()
        }else {
            Toast.makeText(activity,"Error in Fields ",Toast.LENGTH_SHORT).show()
        }
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

    fun isFieldValid(context: Context, editText: EditText): Boolean {
        return if (editText.text.toString().isEmpty()) {
            editText.requestFocus()
            false
        } else
            true
    }

    private fun isUserDataValid(): Boolean {
        return isFieldValid(context!!, username) && isFieldValid(context!!, email) &&
                isEmailValid(context!!, email) && isFieldValid(context!!,first_name)
                && isFieldValid(context!!,last_name) && isFieldValid(context!!,phone_number)
                && isFieldValid(context!!,gender)

    }

    fun isEmailValid(context: Context, editText: EditText): Boolean {
        return if (android.util.Patterns.EMAIL_ADDRESS.matcher(editText.text.toString()).matches())
            true
        else {
            editText.error = context.getString(R.string.not_valid_email)
            false
        }
    }

    fun isPasswordValid(context: Context, editText: EditText): Boolean {
        return !isNumericPassword(context, editText) && isPasswordLengthValid(context, editText)
    }

    private fun isNumericPassword(context: Context, editText: EditText): Boolean {
        val isNumericOnly = editText.text.toString().toIntOrNull()
        return if (isNumericOnly != null) {
            editText.error = context.getString(R.string.password_is_numbers)
            editText.requestFocus()
            true
        } else
            false
    }

    fun passwordsMatch(context: Context, password1: EditText, password2: EditText): Boolean {
        if (password1.text.toString() == password2.text.toString())
            return true
        else {
            password2.error = context.getString(R.string.password_doesnt_match)
            return false
        }
    }

    private fun isPasswordLengthValid(context: Context, editText: EditText): Boolean {
        return if (editText.text.toString().length > 7)
            true
        else {
            editText.error = context.getString(R.string.password_is_short)
            editText.requestFocus()
            false
        }
    }


}
