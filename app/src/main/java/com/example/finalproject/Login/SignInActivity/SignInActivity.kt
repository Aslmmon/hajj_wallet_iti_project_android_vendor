package com.example.finalproject.Login.SignInActivity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.finalproject.Home.HomeActivity
import com.example.finalproject.Login.SignUpActivity.SignUpActivity
import com.example.finalproject.Model.UserLogin.UserResponse
import com.example.finalproject.R
import com.example.finalproject.Retrofit.service
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_account.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    lateinit var sharedPreference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initializeWithSharedPrefrences()
        signIn.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Logging in")
            progressDialog.show()
            service.retrofitService.login(userNAme = userName.text.toString(), pass = pass.text.toString())
                    .enqueue(object : Callback<UserResponse> {
                        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                            Log.i("result", "failer due to ${t.message.toString()}")
                            Toast.makeText(this@SignInActivity, "eror is ${t.message.toString()}", Toast.LENGTH_SHORT).show()
                            progressDialog.dismiss()

                        }

                        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                            progressDialog.dismiss()

                            if (response != null) {
                                if (response.isSuccessful) {
                                    if (checkBox.isChecked)
                                        cacheUserData()
                                    else
                                        decacheUserData()

                                    var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                                    intent.putExtra("token", response.body()!!.token)
                                    intent.putExtra("code", response.body()!!.vendor_details.code)
                                    intent.putExtra("firstName", response.body()!!.user.first_name)
                                    intent.putExtra("email", response.body()!!.user.email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    //  signIn.revertAnimation()
                                    // signIn.background = ContextCompat.getDrawable(this, R.drawable.btn_background)
                                    Toast.makeText(this@SignInActivity, "Wrong data Enterd ${response.message()}", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(this@SignInActivity, "error in internet", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }
        goToRegister.setOnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
    }

    private fun decacheUserData() {
        val preferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        preferences.edit().clear().apply()
    }

    private fun cacheUserData() {
        val sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString("username", userName.text.toString())
        editor.putString("password", pass.text.toString())
        editor.apply()

    }

    private fun initializeWithSharedPrefrences() {
        sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        val text = sharedPreference.getString("username", " ")
        val passw = sharedPreference.getString("password", " ")
        Log.i("hello", "your username is  is $text")
        Log.i("hello", "your username is  is $pass")

        if (!text.isNullOrEmpty() || !passw.isNullOrEmpty())
            checkBox.isChecked = false
        userName.setText(text)
        pass.setText(passw)
    }
}
