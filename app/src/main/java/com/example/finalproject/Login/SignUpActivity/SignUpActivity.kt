package com.example.finalproject.Login.SignUpActivity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.finalproject.Fragments.CashOut.StepperFragments.myStepperSignUpAdapter
import com.example.finalproject.Home.HomeActivity
import com.example.finalproject.Login.SignInActivity.SignInActivity
import com.example.finalproject.Model.CreateWallet.CreateWalletBody
import com.example.finalproject.Model.UserLogin.UserSignUp
import com.example.finalproject.R
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_second_account.*

class SignUpActivity : AppCompatActivity(), StepperLayout.StepperListener {
    lateinit var userSignUp: UserSignUp
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        userSignUp = UserSignUp(
                null, null, null, null, null,
                null, null, null, null, null, null,null,
                null, null
        )

        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)

        stepperLayout!!.adapter = myStepperSignUpAdapter(supportFragmentManager, this)
        stepperLayout!!.setListener(this)
    }


    override fun onStepSelected(newStepPosition: Int) {

    }

    override fun onError(verificationError: VerificationError?) {
    }

    override fun onReturn() {
    }

    override fun onCompleted(completeButton: View?) {



        viewModel.signUp(userSignUp, this)
        viewModel.response.observe(this, Observer { response ->
//            progressDialog.dismiss()
            Toast.makeText(this@SignUpActivity,"Done Created , Waiting For Approval",Toast.LENGTH_LONG).show()
            if (response != null) {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
