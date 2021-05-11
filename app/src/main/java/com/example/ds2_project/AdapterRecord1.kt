package com.example.ds2_project

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRecord1(): RecyclerView.Adapter<AdapterRecord1.HolderRecord>() {



    private var context: Context?=null

    private var recordList:ArrayList<Questions>?=null
    lateinit var dbHelper:DbHelper
    constructor(context: Context?, recordList:ArrayList<Questions>?):this(){
        this.context=context
        this.recordList=recordList
        dbHelper=DbHelper(context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRecord{
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent,false)
        return HolderRecord(v)
    }
    //retourner le size de notre list
    override fun getItemCount(): Int {
        return  recordList!!.size
    }

    override fun onBindViewHolder(holder: HolderRecord, position: Int) {
        //récuperer les données
        val model = recordList?.get(position)

        //mettre les données dans notre view
        holder?.question?.text = model!!.question

        holder?.option1?.text = model!!.option1
        holder?.option2?.text = model!!.option2
        holder?.option3?.text = model!!.option3

        if(model!!.answerNr == 1){

            holder?.option1.setTextColor(Color.GREEN)

        }
        if(model!!.answerNr == 2){


            holder?.option2.setTextColor(Color.GREEN)

        }
        if(model!!.answerNr == 3){

            holder?.option3.setTextColor(Color.GREEN)
        }


    }


    class HolderRecord(itemView: View):
            RecyclerView.ViewHolder(itemView){

        var question: TextView = itemView.findViewById<TextView>(R.id.question)
        var option1: TextView = itemView.findViewById<TextView>(R.id.op1)
        var option2: TextView = itemView.findViewById<TextView>(R.id.op2)
        var option3: TextView = itemView.findViewById<TextView>(R.id.op3)

    }




}






