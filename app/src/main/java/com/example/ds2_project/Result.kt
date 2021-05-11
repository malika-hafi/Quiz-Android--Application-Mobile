package com.example.ds2_project

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.io.Serializable

class Result : AppCompatActivity() {
    lateinit var list : Serializable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var image =findViewById<ImageView>(R.id.image)

        val message = intent.getStringExtra("EXTRA_MESSAGE")
         list = intent.getSerializableExtra("list")!!
        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.test).apply {
            text = " Votre Score: "+message
            val shared= getSharedPreferences("shared_score", AppCompatActivity.MODE_PRIVATE)
            val editor=shared.edit()
            editor.putString("score_key", message)
            editor.apply()
        }
        if (message!!.toInt() >= 5){
            val textView = findViewById<TextView>(R.id.mention).apply {
                text = "Réussi"
                image.setImageResource(R.drawable.good)
            }
        }else{
            val textView = findViewById<TextView>(R.id.mention).apply {
                text = "Echoué"
                setTextColor(Color.RED)
                image.setImageResource(R.drawable.bad)
            }
        }
    }

    fun ShowResult(view: View) {

        val intent = Intent(this, ValidActivity::class.java)
        intent.putExtra("list", list)
        startActivity(intent)
    }
}
