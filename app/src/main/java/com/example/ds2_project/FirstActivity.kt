package com.example.ds2_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }
    fun start(view: View) {
        var intent1= Intent(this,MainActivity::class.java)
        startActivity(intent1)

    }
    fun about(view: View) {
        var intent2= Intent(this,AboutActivity::class.java)
        startActivity(intent2)
    }
    fun exit(view: View) {
         finish()
    }
}