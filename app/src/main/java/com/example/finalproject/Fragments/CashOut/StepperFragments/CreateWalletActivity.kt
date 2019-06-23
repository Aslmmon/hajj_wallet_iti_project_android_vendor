package com.example.finalproject.Fragments.CashOut.StepperFragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.finalproject.Fragments.CashOut.CashOutFragment
import com.example.finalproject.Model.CreateWallet.CreateWalletBody
import com.example.finalproject.R
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import kotlinx.android.synthetic.main.activity_create_wallet.*





class CreateWalletActivity : AppCompatActivity(), StepperLayout.StepperListener  {

    private lateinit var viewModel: CreateWalletViewModel
    lateinit var createWalletbody: CreateWalletBody


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wallet)


        createWalletbody = CreateWalletBody(
                null, null, null, null, null,
                null, null, null, null, null
        )
        viewModel = ViewModelProviders.of(this).get(CreateWalletViewModel::class.java)
        stepperLayout!!.adapter = MyStepperAdapter(supportFragmentManager, this)
        stepperLayout!!.setListener(this)
    }

    override fun onRestart() {
        super.onRestart()


    }
    override fun onStepSelected(newStepPosition: Int) {
    }

    override fun onError(verificationError: VerificationError?) {
    }

    override fun onReturn() {
    }



    override fun onCompleted(completeButton: View?) {
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Creating Wallet")
        progressDialog.show()

        val intent = intent
        val token = intent.getStringExtra("token")
        viewModel.CreateWallet(createWalletbody, this,token)
        viewModel.response.observe(this, Observer {
            if(it != null ){
                Toast.makeText(this@CreateWalletActivity,"Done Created Your Wallet ",Toast.LENGTH_SHORT).show()
                Log.i("eshta","Created in response.observe ")
                finish()
            }else {
                Toast.makeText(this,"Error in Creating",Toast.LENGTH_SHORT).show()
            }
        })
        progressDialog.dismiss()

    }


}
