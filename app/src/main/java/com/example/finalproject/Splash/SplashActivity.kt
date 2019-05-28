package com.example.finalproject.Splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.finalproject.Login.SignInActivity.SignInActivity
import com.example.finalproject.OnBoarding.OnBoardingActivity
import com.example.finalproject.R
import kotlinx.android.synthetic.main.activity_splash.*
import kotlin.concurrent.thread

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 3000 // 3 sec
    lateinit var sharedPreference: SharedPreferences
    val t = Thread()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreference = getSharedPreferences("Once", Context.MODE_PRIVATE)
        val text = sharedPreference.getString("Once","once")
        Log.i("hello","text is $text")



            Handler().postDelayed({

                // This method will be executed once the timer is over
                if(text == "yes"){
                    startActivity(Intent(this, SignInActivity::class.java))
                    finish()
                }else {
                    startActivity(Intent(this, OnBoardingActivity::class.java))
                    finish()
                }
                // close this activity
            }, SPLASH_TIME_OUT)




    }


}

