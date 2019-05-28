package com.example.finalproject.Login.SignInActivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Home.HomeActivity
import com.example.finalproject.Model.response
import com.example.finalproject.Retrofit.service
import kotlinx.coroutines.*

class SignInActivityViewModel : ViewModel() {

    lateinit var sharedPreference: SharedPreferences

    private val _response= MutableLiveData<response>()
    val response: LiveData<response>
        get() = _response

       // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun ValidateEmailAndPAssword(username: EditText, CRN: EditText, name: String, pass: String) {

        if(name.isEmpty()){
            username.error = "Enter Your Email please"
            username.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
            Log.i("check","Email Address")
            username.error = "Enter Valid email please"
            username.requestFocus()
            return
        }
        if(pass.isEmpty()){
            CRN.error = "Enter your CRN"
            CRN.requestFocus()
            return
        }
        if(pass.length <= 6){
            CRN.error = "Enter CRN > 6 numbers "
            CRN.requestFocus()
            return
        }

    }
    fun signIn(
        username: String,
        CRN: String,
        context: Context,
        progress: ProgressBar
    ) {
        coroutineScope.launch {
            val accountLogin = service.retrofitService.login(username, CRN)
            progress.visibility = View.VISIBLE
            try {
                val text = accountLogin.await()
                Log.i("result ", "success of $text")
                progress.visibility = View.INVISIBLE
                saveUserNameAndPassword(username,CRN,context)
                goToHomeActivity(context)

            } catch (e: Exception) {
                Log.i("eror", "this is error ${e.message.toString()}")
                progress.visibility = View.INVISIBLE

            }
        }
    }

    private fun saveUserNameAndPassword(username: String, crn: String, context: Context) {
        sharedPreference = context.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString("username", username)
        editor.putString("password",crn)
        Log.i("loginUSer","Saved Done ")
        editor.commit()
    }

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun goToHomeActivity(context:Context){
        var intent  = Intent(context,HomeActivity::class.java)

        startActivity(context,intent,null)

    }


}