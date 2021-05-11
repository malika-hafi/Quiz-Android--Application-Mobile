package com.example.ds2_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView

import kotlin.collections.ArrayList

class Reviser : AppCompatActivity()  {
       var score =""

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviser)

        //récupérer   depuis intent
        val intent = intent
        val list = intent.getSerializableExtra("list")

         score = intent.getStringExtra("EXTRA_MESSAGE").toString()
        val recyclerView=findViewById<RecyclerView>(R.id.recycle)

        var adapter=AdapterRecord(this, list as ArrayList<Questions>?)

        recyclerView.adapter=adapter

    }

    fun Score(view: View) {
        val list = intent.getSerializableExtra("list1")

        val intent1 = Intent(this, Result::class.java)
        intent1.putExtra("EXTRA_MESSAGE", score)
        intent1.putExtra("list", list)
        startActivity(intent1)

    }


}