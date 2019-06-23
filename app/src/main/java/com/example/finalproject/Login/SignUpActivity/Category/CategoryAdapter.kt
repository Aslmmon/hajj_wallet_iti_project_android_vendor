package com.example.finalproject.Login.SignUpActivity.Category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Login.SignUpActivity.Nationality.ViewHolder
import com.example.finalproject.Model.Categories.CategoriesResponse
import com.example.finalproject.Model.Nationalities.NationalitiesResponse
import com.example.finalproject.R
import kotlinx.android.synthetic.main.item_view_category.view.*
import kotlinx.android.synthetic.main.item_view_nationality.view.*

class CategoryAdapter constructor(
        private val categories: List<CategoriesResponse>,
        val context: Context?,
        val clickListener: (CategoriesResponse, Int) -> Unit
):
        RecyclerView.Adapter<myViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.item_view_category,
                        parent,
                        false
                )
        )
    }


    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder?.categoryName?.text = categories.get(position).name

        holder?.itemView?.setOnClickListener {
            clickListener(
                    categories.get(position),
                    position
            )
        }
    }
}

class myViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val categoryName = view.category
}

