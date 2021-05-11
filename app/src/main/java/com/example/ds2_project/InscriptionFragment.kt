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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class InscriptionFragment : Fragment() {

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
        val rootView =inflater.inflate(R.layout.fragment_inscription, container, false)
//les champs de formulaire

        var text_username=rootView.findViewById<EditText>(R.id.text_userame)
        var text_password=rootView.findViewById<EditText>(R.id.text_pass)
        var text_con_password=rootView.findViewById<EditText>(R.id.text_con_pass)
        var btn_register=rootView.findViewById<CardView>(R.id.card_view_btn_egister)
        var image_twitter=rootView.findViewById<ImageView>(R.id.twitterimage)
        var insta_image=rootView.findViewById<ImageView>(R.id.instaimage)
        var face_image=rootView.findViewById<ImageView>(R.id.facebookimage)

        // la class sqllitehelper

        var db=DbHelper(getActivity())

        var _go_to_login_text=rootView.findViewById<TextView>(R.id.go_to_login)
        _go_to_login_text.setOnClickListener{
            var intent= Intent(getActivity(),MainActivity::class.java)
            startActivity(intent);
        }
        //on cliquant qur le boutton login

        btn_register.setOnClickListener{
            val username_text=text_username.text.toString()
            val password_text=text_password.text.toString()
            val con_password_text=text_con_password.text.toString()
            //test de validation des champs
            if((username_text.equals(""))||(password_text.equals(""))||(con_password_text.equals(""))){
                Toast.makeText(getActivity(),"veuillez remplir touts les champs", Toast.LENGTH_LONG).show()
            }
            else{
                if(password_text.equals(con_password_text)) {

                    val checkuser:Boolean = db.checkUsername(username_text)
                    if(checkuser==false) {
                        var isInsert:Long = db.add_user(username_text, password_text)
                        if(isInsert!=null){
                            Toast.makeText(getActivity(),"félicitaion votre compte a bien été créé veuillez se connecter ${isInsert}",
                                Toast.LENGTH_LONG).show()
                            var intent= Intent(getActivity(),MainActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(getActivity(),"inscription échoué ${isInsert}", Toast.LENGTH_LONG).show()
                        }
                    }
                    else{
                        Toast.makeText(getActivity(),"l'utilisateur existe déja", Toast.LENGTH_LONG).show()

                    }
                }
                else{
                    Toast.makeText(getActivity(),"le mot de passe de confirmation est incorrecte", Toast.LENGTH_LONG).show()


                }

            }
        }
        image_twitter.setOnClickListener{
            val twitter_page = Uri.parse("https://twitter.com/androiddev")
            val webIntent = Intent(Intent.ACTION_VIEW, twitter_page)
            startActivity(webIntent)
        }
        insta_image.setOnClickListener{
            val insta_image = Uri.parse("https://www.instagram.com/android")
            val webIntent = Intent(Intent.ACTION_VIEW, insta_image)
            startActivity(webIntent)
        }
        face_image.setOnClickListener{
            val face_image = Uri.parse("https://www.facebook.com/ENSA-Safi-Service-des-Relations-Ext%C3%A9rieures-526411714065605/")
            val webIntent = Intent(Intent.ACTION_VIEW, face_image)
            startActivity(webIntent)
        }


        return rootView
    }

    fun changecolor(rootView: View) {
        var btn_register=rootView.findViewById<CardView>(R.id.card_view_btn_egister)
        btn_register.setBackgroundColor(Color.BLACK)
    }
}