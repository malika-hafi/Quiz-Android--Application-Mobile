package com.example.ds2_project

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class QuizActivity : AppCompatActivity() {
    //les champs de qcm
    var MyAnwsers:ArrayList<Questions> = ArrayList()
    var CorrectAnswers:ArrayList<Questions> = ArrayList()
    var AnswerList:ArrayList<Int> = ArrayList()
    var i =0
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
    var dbHelper=DbHelper(this)

    //timer
    lateinit var countdowntimer: CountDownTimer
    lateinit var textColorDefaultCd: ColorStateList
    var timeleftInMillis :Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        //les widget de l'activity
        var btn_confirm_next=findViewById<TextView>(R.id.textView)
        var question_text=findViewById<TextView>(R.id.text_view_question)
        var rb1=findViewById<RadioButton>(R.id.radio_button1)
        var rb2=findViewById<RadioButton>(R.id.radio_button2)
        var rb3=findViewById<RadioButton>(R.id.radio_button3)
        var countDownText=findViewById<TextView>(R.id.text_view_countdown)
        var questiocountText=findViewById<TextView>(R.id.text_view_question_count)
        var RbGroup=findViewById<RadioGroup>(R.id.radio_group)
        textViewChapitre=findViewById(R.id.text_view_chapitre)
        textViewDifficulty = findViewById(R.id.text_view_difficulty)
        //recuperer l'intent avec la difficulté choisie
        val intent = intent
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
                    Toast.makeText(this, "veuillez selectionner une réponse", Toast.LENGTH_LONG).show()
                }
            }
            else{
                MyAnwsers.add(Questions( currentQuestion.question,currentQuestion.option1,
                        currentQuestion.option2,currentQuestion.option3, AnswerList.elementAt(i),
                        currentQuestion.difficulty,currentQuestion.chapitre_id))

                CorrectAnswers.add(Questions( currentQuestion.question,currentQuestion.option1,
                        currentQuestion.option2,currentQuestion.option3,currentQuestion.answerNr,
                        currentQuestion.difficulty,currentQuestion.chapitre_id))
                i= i+1
                ShowNextQuestions()
            }
        }
    }

    private fun checKAnswer() {
        var score_text=findViewById<TextView>(R.id.text_view_score)
        var RbGroup=findViewById<RadioGroup>(R.id.radio_group)
        answered = true
        countdowntimer.cancel()
        var rbSelected=findViewById<RadioButton>(RbGroup.checkedRadioButtonId)
        var answerNr=RbGroup.indexOfChild(rbSelected)+1
        AnswerList.add(answerNr)
        if (answerNr == currentQuestion.answerNr) {
            score++;
            score_text.text="Score: " + score
        }
        showSolutions()
    }

    private fun showSolutions() {
        var btn_confirm_next=findViewById<TextView>(R.id.textView)
        var question_text=findViewById<TextView>(R.id.text_view_question)
        var rb1=findViewById<RadioButton>(R.id.radio_button1)
        var rb2=findViewById<RadioButton>(R.id.radio_button2)
        var rb3=findViewById<RadioButton>(R.id.radio_button3)
        rb1.setTextColor(Color.RED)
        rb2.setTextColor(Color.RED)
        rb3.setTextColor(Color.RED)

        when (currentQuestion.answerNr) {

            1 -> {
                rb1.setTextColor(Color.GREEN)
                question_text.setText("la réponse  A est correcte")
            }
            2 -> {
                rb2.setTextColor(Color.GREEN)
                question_text.setText("la réponse  B est correcte")
            }
            3 -> {
                rb3.setTextColor(Color.GREEN)
                question_text.setText("la réponse C est correcte")
            }
        }
        if (questionCounter < questionCountTotal) {
            btn_confirm_next.setText("Suivant");
        } else {
            btn_confirm_next.setText("terminer");
        }
    }

    private fun ShowNextQuestions() {
        var btn_confirm_next=findViewById<TextView>(R.id.textView)
        var questiocountText=findViewById<TextView>(R.id.text_view_question_count)
        var question_text=findViewById<TextView>(R.id.text_view_question)
        var RbGroup= findViewById<RadioGroup>(R.id.radio_group)
        var rb1= findViewById<RadioButton>(R.id.radio_button1)
        var rb2= findViewById<RadioButton>(R.id.radio_button2)
        var rb3= findViewById<RadioButton>(R.id.radio_button3)
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

            val intent1 = Intent(this, Reviser::class.java)

            intent1.putExtra("list", MyAnwsers)
            intent1.putExtra("list1", CorrectAnswers)
            intent1.putExtra("EXTRA_MESSAGE", score.toString())

            startActivity(intent1)
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
        var countDownText=findViewById<TextView>(R.id.text_view_countdown)
        val minutes = (timeleftInMillis / 1000)  / 60
        val seconds = (timeleftInMillis / 1000)  % 60
        val timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        countDownText.setText(timeFormatted)
        if (timeleftInMillis < 10000) {
            countDownText.setTextColor(Color.RED)
        } else {
            countDownText.setTextColor(textColorDefaultCd)
        }
    }
    private fun finishQuiz() {
        val intent = Intent(this, Result::class.java).apply {
            putExtra("EXTRA_MESSAGE", score.toString())
        }
        startActivity(intent)

    }
    override fun onBackPressed() {

        super.onBackPressed()
        if (backPressedTime +2000  > System.currentTimeMillis()) {
            finishQuiz()
        } else {
            Toast.makeText(this, "Appuyez à nouveau pour terminer", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis()
    }
    override fun onDestroy() {
        super.onDestroy()
        if (countdowntimer != null) {
            countdowntimer.cancel()
        }
    }

}