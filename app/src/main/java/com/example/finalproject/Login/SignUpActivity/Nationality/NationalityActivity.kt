package com.example.finalproject.Login.SignUpActivity.Nationality

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import kotlinx.android.synthetic.main.activity_nationality.*

class NationalityActivity : AppCompatActivity() {
    private lateinit var viewModel: NationalityActivityViewModel
    private lateinit var mAdapter: NationalityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nationality)

        viewModel = ViewModelProviders.of(this).get(NationalityActivityViewModel::class.java)
        viewModel.getNationalities()
        recycler.layoutManager = LinearLayoutManager(this)

        viewModel.response.observe(this, Observer {
            mAdapter = NationalityAdapter(it, this) { nation, position: Int ->
                val returnIntent = intent
                returnIntent.putExtra("nationality",nation.name)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
            recycler.adapter = mAdapter
        })

    }
}
