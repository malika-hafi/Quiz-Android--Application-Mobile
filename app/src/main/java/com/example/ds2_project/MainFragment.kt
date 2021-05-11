package com.example.ds2_project

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.widget.Toast.makeText as makeText1


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rootView =inflater.inflate(R.layout.fragment_main, container, false)

        // les champs de formulaire
        var username=rootView.findViewById<EditText>(R.id.editnom_util)
        var password=rootView.findViewById<EditText>(R.id.editpass)
        var btn_login_click=rootView.findViewById<CardView>(R.id.cardView_btn_login)

        // la class sqllitehelper
        var db=DbHelper(getActivity())

        var go_to_register_text=rootView.findViewById<TextView>(R.id.go_to_register);
        go_to_register_text.setOnClickListener{
            var intent= Intent(getActivity(),Inscription::class.java)
            startActivity(intent)
        }

        //on cliquant sur le boutton s'inscrire
        btn_login_click.setOnClickListener{

            val username_text=username.text.toString()
            val password_text=password.text.toString()

            //tester la validation des echamps

            if((username_text.equals("")||(password_text.equals("")))){

                makeText1(getActivity(),"veuillez remplir touts les champs", Toast.LENGTH_LONG).show()

            }
            else{
                var checkuserpass=db.checkUserNamePassword(username_text,password_text)
                if(checkuserpass==true){
                    makeText1(getActivity(),"connexion réussite", Toast.LENGTH_LONG).show()
                    val sharedPrefences= this.getActivity()!!.getSharedPreferences("shared_username", AppCompatActivity.MODE_PRIVATE)
                    val editor=sharedPrefences.edit()
                    editor.putString("username_key", username_text)
                    editor.apply()
                    var intent= Intent(getActivity(),Acceuil::class.java)
                    startActivity(intent)
                }
                else{
                    makeText1(getActivity(),"le nom d'utilisateur ou le mot de passe est incorrecte", Toast.LENGTH_LONG).show()

                }
            }
        }

        return rootView
    }

    fun changecolor(rootView: View) {
        var btn_login_click= rootView.findViewById<CardView>(R.id.cardView_btn_login)
        btn_login_click.setBackgroundColor(Color.BLACK)
    }

    fun go_to_twitter(rootView: View) {
        val twitter_page = Uri.parse("https://twitter.com/androiddev")
        val webIntent = Intent(Intent.ACTION_VIEW, twitter_page)
        startActivity(webIntent)
    }

    fun go_to_facebook(rootView: View) {
        val face_image = Uri.parse("https://www.facebook.com/ENSA-Safi-Service-des-Relations-Ext%C3%A9rieures-526411714065605/")
        val webIntent = Intent(Intent.ACTION_VIEW, face_image)
        startActivity(webIntent)
    }
    fun go_to_insta(rootView: View) {
        val insta_image = Uri.parse("https://www.instagram.com/android")
        val webIntent = Intent(Intent.ACTION_VIEW, insta_image)
        startActivity(webIntent)

    }

}