package com.example.finalproject.OnBoarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalproject.Login.SignInActivity.SignInActivity
import com.example.finalproject.R
import me.tylerbwong.allaboard.builder.onboarding
import me.tylerbwong.allaboard.builder.page

class OnBoardingActivity : AppCompatActivity() {
    lateinit var sharedPreference: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        sharedPreference = getSharedPreferences("Once", Context.MODE_PRIVATE)

        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString("Once","yes")
        editor.commit()

        onboarding {
            backgroundColor = R.color.white
            page {
                imageRes = R.drawable.sign
                titleText = "Register Now"
                titleColor = R.color.colorPrimary

            }
            page {
                imageRes = R.drawable.qrcode
                titleText = "Get Your QR code"
                titleColor = R.color.colorPrimary
            }
            page {
                imageRes = R.drawable.payments
                titleText = "Recieve Your Payment"
                titleColor = R.color.colorPrimary

            }
            page {
                imageRes = R.drawable.statistics
                titleText = "Show Statistics"
                titleColor = R.color.colorPrimary
            }
//            page {
//                imageRes = R.drawable.wallet
  //          layout : R.layout.myLayout
//                subTitleText = "This is a subtitle."
//            }
            onFinish {
                startActivity(Intent(this@OnBoardingActivity, SignInActivity::class.java))
                finish()

            }
        }
    }
}
