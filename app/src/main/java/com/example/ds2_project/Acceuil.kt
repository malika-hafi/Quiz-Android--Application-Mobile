package com.example.ds2_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Acceuil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acceuil)
    }

    fun se_deconnecter(view: View) {
        var intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}