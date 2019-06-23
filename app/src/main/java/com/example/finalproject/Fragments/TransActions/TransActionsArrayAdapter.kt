package com.example.finalproject.Fragments.TransActions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.Model.TransactionsData.transactionsData
import com.example.finalproject.R
import java.util.ArrayList

class TransActionsArrayAdapter(var context: Context,
                               internal var transActionsData: ArrayList<transactionsData>) : RecyclerView.Adapter<TransActionsArrayAdapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.payment_list_item, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transActionsData.size

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        var data = transActionsData[position]
        var date = data.timeStamp.substring(0,10)
        holder.pilgrimUserName.text = data.pilgrimUsername
        holder.Time.text = date
        holder.money.text = "$"+(data.moneyPaid / 100.0 ).toString()
        Glide.with(context).load(R.drawable.transaction).into(holder.image)
    }


    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pilgrimUserName: TextView = itemView.findViewById(R.id.companyTitle)
        var Time: TextView = itemView.findViewById(R.id.subtitle)
        var money: TextView = itemView.findViewById(R.id.money)
        var image: ImageView = itemView.findViewById(R.id.image)
    }
}