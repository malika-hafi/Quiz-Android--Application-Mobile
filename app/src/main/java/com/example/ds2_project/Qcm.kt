package com.example.ds2_project

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Qcm : Fragment() {
    // TODO: Rename and change types of parameters

    private var param1: String? = null
    private var param2: String? = null
//les champs de qcm

    lateinit var textColorDefaultRb: ColorStateList
    //difficulté textview
    lateinit var textViewDifficulty: TextView
    lateinit var textViewChapitre: TextView

    var EXTRA_SCORE = "extraScore"
    val COUNTDOWN_IN_MILLIS:Long=30000
    var  score:Int = 0
    var  answered:Boolean = false
    var questionCounter:Int = 0
    var questionCountTotal:Int = 0
    var backPressedTime:Long= 0L
    lateinit var currentQuestion:Questions
    var questionsList:ArrayList<Questions>? = ArrayList<Questions>()
    var dbHelper=DbHelper(getActivity())

    //timer
    lateinit var countdowntimer: CountDownTimer
    lateinit var textColorDefaultCd: ColorStateList
    var timeleftInMillis :Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =inflater.inflate(R.layout.fragment_qcm, container, false)

        //les widget de l'activity
        var btn_confirm_next=rootView.findViewById<TextView>(R.id.textView)
        var question_text=rootView.findViewById<TextView>(R.id.text_view_question)
        var rb1=rootView.findViewById<RadioButton>(R.id.radio_button1)
        var rb2=rootView.findViewById<RadioButton>(R.id.radio_button2)
        var rb3=rootView.findViewById<RadioButton>(R.id.radio_button3)
        var countDownText=rootView.findViewById<TextView>(R.id.text_view_countdown)
        var questiocountText=rootView.findViewById<TextView>(R.id.text_view_question_count)
        var RbGroup=rootView.findViewById<RadioGroup>(R.id.radio_group)
        textViewChapitre=rootView.findViewById(R.id.text_view_chapitre)
        textViewDifficulty = rootView.findViewById(R.id.text_view_difficulty)
        //recuperer l'intent avec la difficulté choisie
        val intent = Intent (  )
        val chapitreID = intent.getIntExtra("extraChapitreID", 0)
        val chapitreNAME = intent.getStringExtra("extraChapitreName")
        val difficulty = intent.getStringExtra("extraDifficulty")
        textViewChapitre.setText(chapitreNAME)
        textViewDifficulty.setText("Difficulté: " + difficulty)

        questionsList= dbHelper?.getQuestions(chapitreID,difficulty!!)
        textColorDefaultRb = rb1.getTextColors()
        textColorDefaultCd=countDownText.getTextColors()
        questionCountTotal = questionsList?.size!!
        Collections.shuffle(questionsList);
        ShowNextQuestions()

        btn_confirm_next.setOnClickListener{
            if(!answered){
                if((rb1.isChecked)||(rb2.isChecked)||(rb3.isChecked)){
                    checKAnswer()
                }
                else{
                    Toast.makeText(getActivity(), "veuillez selectionner une reponse", Toast.LENGTH_LONG).show()
                }
            }
            else{
                ShowNextQuestions()
            }
        }
        return rootView
    }

    private fun checKAnswer() {

        var score_text= getView()!!.findViewById<TextView>(R.id.text_view_score)
        var RbGroup=getView()!!.findViewById<RadioGroup>(R.id.radio_group)
        answered = true
        countdowntimer.cancel()
        var rbSelected=getView()!!.findViewById<RadioButton>(RbGroup.checkedRadioButtonId)
        var answerNr=RbGroup.indexOfChild(rbSelected)+1
        if (answerNr == currentQuestion.answerNr) {
            score++;
            score_text.text="Score: " + score
        }
        showSolutions()
    }

    private fun showSolutions() {
        var btn_confirm_next=getView()!!.findViewById<TextView>(R.id.textView)
        var question_text=getView()!!.findViewById<TextView>(R.id.text_view_question)
        var rb1=getView()!!.findViewById<RadioButton>(R.id.radio_button1)
        var rb2=getView()!!.findViewById<RadioButton>(R.id.radio_button2)
        var rb3=getView()!!.findViewById<RadioButton>(R.id.radio_button3)
        rb1.setTextColor(Color.RED)
        rb2.setTextColor(Color.RED)
        rb3.setTextColor(Color.RED)

        when (currentQuestion.answerNr) {

            1 -> {
                rb1.setTextColor(Color.GREEN)
                question_text.setText("la reponse  1 est correcte")
            }
            2 -> {
                rb2.setTextColor(Color.GREEN)
                question_text.setText("la reponse  2 est correcte")
            }
            3 -> {
                rb3.setTextColor(Color.GREEN)
                question_text.setText("la reponse 3 est correcte")
            }
        }
        if (questionCounter < questionCountTotal) {
            btn_confirm_next.setText("Next");
        } else {
            btn_confirm_next.setText("termier");
        }
    }

    private fun ShowNextQuestions() {
        var btn_confirm_next=getView()!!.findViewById<TextView>(R.id.textView)
        var questiocountText=getView()!!.findViewById<TextView>(R.id.text_view_question_count)
        var question_text=getView()!!.findViewById<TextView>(R.id.text_view_question)
        var RbGroup=getView()!!.findViewById<RadioGroup>(R.id.radio_group)
        var rb1=getView()!!.findViewById<RadioButton>(R.id.radio_button1)
        var rb2=getView()!!.findViewById<RadioButton>(R.id.radio_button2)
        var rb3=getView()!!.findViewById<RadioButton>(R.id.radio_button3)
        rb1.setTextColor(textColorDefaultRb)
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);

        RbGroup.clearCheck()
        if(questionCounter < questionCountTotal){
            currentQuestion= questionsList?.get(questionCounter)!!

            question_text.text=currentQuestion.question
            rb1.text=currentQuestion.option1
            rb2.text=currentQuestion.option2
            rb3.text=currentQuestion.option3
            questionCounter++
            questiocountText.text="Question: "+questionCounter +"/"+questionCountTotal
            answered = false;
            btn_confirm_next.text="confirmer"

            timeleftInMillis=COUNTDOWN_IN_MILLIS
            startCountDown()
        }
        else{
            finishQuiz()
        }
    }
    private fun startCountDown() {
        countdowntimer = object : CountDownTimer(timeleftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeleftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                timeleftInMillis = 0
                updateCountDownText()
                checKAnswer()
            }

        }.start()
    }
    public fun updateCountDownText(){
        var countDownText=getView()!!.findViewById<TextView>(R.id.text_view_countdown)
        val minutes = (timeleftInMillis / 1000)  / 60
        val seconds = (timeleftInMillis / 1000)  % 60
        val timeFormatted = java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        countDownText.setText(timeFormatted)
        if (timeleftInMillis < 10000) {
            countDownText.setTextColor(Color.RED)
        } else {
            countDownText.setTextColor(textColorDefaultCd)
        }
    }
    private fun finishQuiz() {
        val ResultIntent= Intent()
        ResultIntent.putExtra(EXTRA_SCORE, score)
        getActivity()!!.setResult(AppCompatActivity.RESULT_OK, ResultIntent)
        getActivity()!!.finish()
    }
}