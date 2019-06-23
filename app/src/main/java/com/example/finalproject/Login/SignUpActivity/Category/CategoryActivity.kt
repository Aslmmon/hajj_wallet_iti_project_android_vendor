package com.example.finalproject.Login.SignUpActivity.Category

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.Login.SignUpActivity.Nationality.NationalityAdapter
import com.example.finalproject.R
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_nationality.*

class CategoryActivity : AppCompatActivity() {
    private lateinit var viewModel: CategoryActivityViewModel
    private lateinit var mAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        viewModel = ViewModelProviders.of(this).get(CategoryActivityViewModel::class.java)
        viewModel.getCategories()
        recyclerCategory.layoutManager = LinearLayoutManager(this)

        viewModel.response.observe(this, Observer {
            mAdapter = CategoryAdapter(it, this) { category, position: Int ->
                val returnIntent = intent
                returnIntent.putExtra("category",category.name)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
            recyclerCategory.adapter = mAdapter

        })

    }
}
