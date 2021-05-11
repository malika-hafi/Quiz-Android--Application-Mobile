package com.example.ds2_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlin.collections.ArrayList

class ValidActivity : AppCompatActivity() {
    var dbHelper=DbHelper(this)
    var questionsList:  ArrayList<Questions>? = ArrayList<Questions>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valid)

        val recyclerView=findViewById<RecyclerView>(R.id.recycle)

        val intent = intent
        val list = intent.getSerializableExtra("list")as ArrayList<Questions>


        val chapitreID =list.get(0).chapitre_id
        val difficulty =list.get(0).difficulty

      questionsList= dbHelper?.getAllQuestions( )
        var adapter=AdapterRecord1(this, list)

        recyclerView.adapter=adapter


    }

    fun Score(view: View) {
        val intent = Intent(this, Result::class.java)
        startActivity(intent)
    }

    fun go_to_acceuil(view: View) {
        val shared= getSharedPreferences("shared_score", AppCompatActivity.MODE_PRIVATE)
        val score= shared.getString("score_key", "").toString()
        var intent=Intent(this,Acceuil::class.java)
        intent.putExtra("score",score)
        startActivity(intent)


    }

}


