package com.example.ds2_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MyAdapte:RecyclerView.Adapter<MyAdapte.MyViewHolder> {

    lateinit var ct:Context
    lateinit var titre:ArrayList<String>
    lateinit var description:ArrayList<String>

    constructor(ct:Context, titre: ArrayList<String>, description:ArrayList<String>){
        this.ct=ct;

        this.description=description
        this.titre=titre
    }

    class MyViewHolder:RecyclerView.ViewHolder {
        lateinit var titre:TextView
        lateinit var description:TextView

        constructor(itemView: View) : super(itemView){

            this.description=itemView.findViewById(R.id.question)
            this.titre=itemView.findViewById(R.id.question)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater:LayoutInflater= LayoutInflater.from(ct)
        var view:View=inflater.inflate(R.layout.item_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titre.setText(titre[position])
        holder.description.setText(description[position])

    }
    override fun getItemCount(): Int {
        return description.size
    }
}