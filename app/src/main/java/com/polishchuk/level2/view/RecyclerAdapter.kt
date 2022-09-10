package com.polishchuk.level2.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.polishchuk.level2.R
import com.polishchuk.level2.model.UserContact

class RecyclerAdapter(private val names: List<UserContact>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val imageContact : ImageView = itemView.findViewById(R.id.imageContact)
        var nameField : TextView = itemView.findViewById(R.id.nameContact)
        val phoneField : TextView = itemView.findViewById(R.id.textPhone)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameField.text =
            names[position].nameField
        holder.phoneField.text =
            names[position].phoneNumber
    }

    override fun getItemCount() = names.size
}