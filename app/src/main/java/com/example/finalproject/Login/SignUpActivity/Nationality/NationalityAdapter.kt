package com.example.finalproject.Login.SignUpActivity.Nationality

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Model.Nationalities.NationalitiesResponse
import com.example.finalproject.R
import kotlinx.android.synthetic.main.item_view_nationality.view.*

class NationalityAdapter constructor(
        private val nationalities: List<NationalitiesResponse>,
        val context: Context?,
        val clickListener: (NationalitiesResponse, Int) -> Unit
) :
        RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.item_view_nationality,
                        parent,
                        false
                )
        )
    }


    override fun getItemCount(): Int {
        return nationalities.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.name?.text = nationalities.get(position).name

        holder?.itemView?.setOnClickListener {
            clickListener(
                    nationalities.get(position),
                    position
            )
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name = view.tv_nationality
}