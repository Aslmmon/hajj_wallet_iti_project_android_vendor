package com.example.finalproject.Login.SignInActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.finalproject.Home.HomeActivity
import com.example.finalproject.Model.UserLogin.UserResponse
import com.example.finalproject.R
import com.example.finalproject.Retrofit.service
import kotlinx.android.synthetic.main.activity_sign_in.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    // private lateinit var viewModel: SignInActivityViewModel
    lateinit var sharedPreference: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initializeWithSharedPrefrences()
        signIn.setOnClickListener {
            service.retrofitService.login(userNAme = userName.text.toString(), pass = CRNNumber.text.toString())
                    .enqueue(object : Callback<UserResponse> {
                        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                            Log.i("result", "failer due to ${t.message.toString()}")

                        }

                        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                                Log.i("result", "result is ${response.body()}")
                                sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
                            val editor: SharedPreferences.Editor = sharedPreference.edit()
                            editor.putString("username", userName.text.toString())
                            editor.putString("password",CRNNumber.text.toString())
                            Log.i("loginUSer","Saved Done ")
                            editor.commit()
                            var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                            startActivity( intent)
                            finish()
                        }
//                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                        Log.i("result", "failer due to ${t.message.toString()}")
//                    }
//                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                        Log.i("result", "result is ${response.isSuccessful}")
//                        if (response.isSuccessful){
//                            sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
//                            val editor: SharedPreferences.Editor = sharedPreference.edit()
//                            editor.putString("username", userName.text.toString())
//                            editor.putString("password",CRNNumber.text.toString())
//                            Log.i("loginUSer","Saved Done ")
//                            editor.commit()
//                            var intent = Intent(this@SignInActivity, HomeActivity::class.java)
//                            startActivity( intent)
//                        }else {
//                            sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
//                            val editor: SharedPreferences.Editor = sharedPreference.edit()
//                            editor.putString("username", userName.text.toString())
//                            editor.putString("password",CRNNumber.text.toString())
//                            Log.i("loginUSer","Saved Done ")
//                            editor.commit()
//
//                            Log.i("result", "result is ${response.errorBody()}")
//                            Log.i("result", "result is ${response.message()}")
//                            Log.i("result", "result is ${response.body()}")
//                            Log.i("result", "result is ${response.headers()}")
//                            Log.i("result", "result is ${response.code()}")
//                            Log.i("result", "result is ${response.raw()}")
//                            var intent = Intent(this@SignInActivity, HomeActivity::class.java)
//                            startActivity( intent)
//                            finish()
//
//
//                        }
//                    }
//                })
                    })
        }
//        viewModel = ViewModelProviders.of(this).get(SignInActivityViewModel::class.java)
//        signIn.setOnClickListener {
//            var name = email.text.toString()
//            var passwordtaken = CRNNumber.text.toString()
//         //   viewModel.ValidateEmailAndPAssword(email, CRNNumber, name, passwordtaken)
//            viewModel.signIn(name, passwordtaken,this,progress)
//
//        }
//        goToRegister.setOnClickListener {
//            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
//        }


    }

    private fun initializeWithSharedPrefrences() {
        sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        val text = sharedPreference.getString("username", "username")
        val pass = sharedPreference.getString("password", "password")

        Log.i("hello", "your username is  is $text")
        Log.i("hello", "your username is  is $pass")
        userName.setText(text)
        CRNNumber.setText(pass)
    }
}
