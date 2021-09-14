package com.example.clever_2
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class NameAdapter(private val names: List<String>,private val descriptions: MutableList<String>) :
    RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.name_item, parent, false)
        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val element = names[position]
        val secondElement = descriptions [position]
        holder.txtName.text = element
        holder.txtDescription.text = secondElement

    }

    override fun getItemCount(): Int {
        return names.size

    }

    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtName = itemView.findViewById(R.id.txtName) as TextView
        val txtDescription = itemView.findViewById(R.id.txtDescription) as TextView


    }
}