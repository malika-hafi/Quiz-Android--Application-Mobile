package com.example.ds2_project

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AcceuilFragment : Fragment()  {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var textViewHighScore: TextView
    val SHARED_PREFS = "sharedPrefs";
    val KEY_HIGHSCORE = "keyHighscore";
    var highScore:Int=0
    var username:String=""
    val  REQUEST_CODE_QUIZ = 1
    val EXTRA_CHAPITRE_ID = "extraChapitreID"
    val EXTRA_CHAPITRE_NAME = "extraChapitreName"
    val EXTRA_DIFFICULTY = "extraDifficulty"
    lateinit var spinnerDifficulty: Spinner
    lateinit var spinnerChapitre: Spinner
    lateinit var questions_class:Questions
    lateinit var dbHelper:DbHelper
    lateinit var nom_utilisateur: TextView
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
        val rootView =inflater.inflate(R.layout.fragment_acceuil, container, false)

//spinner de difficulté


        questions_class= Questions()
        dbHelper=DbHelper(getActivity())
        spinnerDifficulty = rootView.findViewById<Spinner>(R.id.spinner_difficulty)
        spinnerChapitre = rootView.findViewById<Spinner>(R.id.spinner_chapitre)
        textViewHighScore=rootView.findViewById(R.id.text_view_highscore)
        nom_utilisateur=rootView.findViewById(R.id.text_userame)
        loadChapitre()
        loadDifficultyLevels()
        loadHighScore()

        val btn_start=rootView.findViewById<CardView>(R.id.button_start_quiz)
        btn_start.setOnClickListener{
            startQuiz()
        }
        //recupere le score
        val scoreIntent= getActivity()!!.getIntent()
         val scoremessage=scoreIntent.getStringExtra("score")
        val score= scoremessage?.toInt()
        if (score != null) {
            if (score > highScore ) {
                updateHighscore(score)
            }
        }
        //recuperer le nom d'utilisteur
        var Intent= getActivity()!!.getIntent()
        val sharedPrefences= this.getActivity()!!.getSharedPreferences("shared_username", AppCompatActivity.MODE_PRIVATE)
        username= sharedPrefences.getString("username_key", "").toString()
        nom_utilisateur.text="Bonjour $username"

        return rootView
    }

    private fun loadChapitre() {

        val chapitres: List<Chapitre> = dbHelper.getAllChapitre()!!
        val adapterChapitres: ArrayAdapter<Chapitre> = ArrayAdapter<Chapitre>(getActivity()!!.getApplicationContext(), android.R.layout.simple_spinner_item, chapitres)
        adapterChapitres.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerChapitre.adapter=adapterChapitres
    }

    private fun loadDifficultyLevels() {
        val difficultyLevels: Array<String> = questions_class.getAllDifficultyLevels()!!
        val adapterDifficulty = ArrayAdapter(getActivity()!!.getApplicationContext(), android.R.layout.simple_spinner_item, difficultyLevels)
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDifficulty.setAdapter(adapterDifficulty);

    }

    fun startQuiz(){

        val selectedCategory: Chapitre = spinnerChapitre.getSelectedItem() as Chapitre
        val chapitreId: Int = selectedCategory.id
        val chapitrename: String = selectedCategory.name
        val difficulty = spinnerDifficulty.selectedItem.toString()

        val intent = Intent(getActivity(), QuizActivity::class.java)
        intent.putExtra(EXTRA_CHAPITRE_ID, chapitreId)
        intent.putExtra(EXTRA_CHAPITRE_NAME, chapitrename)
        intent.putExtra(EXTRA_DIFFICULTY, difficulty)
        startActivityForResult(intent, REQUEST_CODE_QUIZ)
    }



    private fun loadHighScore(){
        val sharedPrefences=
            this.getActivity()!!.getSharedPreferences(SHARED_PREFS, AppCompatActivity.MODE_PRIVATE)
        highScore=sharedPrefences.getInt(KEY_HIGHSCORE, 0)
        textViewHighScore.text="Highscore "+highScore

    }

    private fun updateHighscore(highscoreNew: Int) {
        highScore = highscoreNew
        textViewHighScore.text="Highscore "+highScore
        val sharedPrefences= this.getActivity()!!.getSharedPreferences(SHARED_PREFS, AppCompatActivity.MODE_PRIVATE)
        val editor=sharedPrefences.edit()
        editor.putInt(KEY_HIGHSCORE, highScore)
        editor.apply()

    }
}
