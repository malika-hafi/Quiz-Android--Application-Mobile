package com.example.ds2_project

import android.os.Parcel
import android.os.Parcelable
import kotlin.properties.Delegates

class Questions : Parcelable {
    public  val DIFFICULTY_EASY = "Facile"
    public  val DIFFICULTY_MEDIUM = "Moyen"
    public  val DIFFICULTY_HARD = "Difficile"

    var id:Int = 0

    public lateinit var question:String

    public lateinit var option1:String

    public lateinit var option2:String

    public lateinit var option3:String

    public var answerNr by Delegates.notNull<Int>()

    public lateinit var difficulty:String

    public var chapitre_id by Delegates.notNull<Int>()

    constructor( ){
        id=0
        question=""
        option1=""
        option2=""
        option3=""
        answerNr=0
        difficulty=""
        chapitre_id=0
    }

      constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        question = parcel.readString().toString()
        option1 = parcel.readString().toString()
        option2 = parcel.readString().toString()
        option3 = parcel.readString().toString()
        answerNr = parcel.readInt()
        difficulty = parcel.readString().toString()
        chapitre_id = parcel.readInt()
    }


    constructor(question: String, option1: String, option2: String, option3: String,
                answerNr: Int, difficulty: String,chapitre_id:Int){

        this.option1=option1
        this.option2=option2
        this.option3=option3
        this.question=question
        this.answerNr=answerNr
        this.difficulty=difficulty
        this.chapitre_id=chapitre_id
    }



    fun getAllDifficultyLevels(): Array<String>? {
        return arrayOf(
            DIFFICULTY_EASY,
            DIFFICULTY_MEDIUM,
            DIFFICULTY_HARD
        )
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(question)
        parcel.writeString(option1)
        parcel.writeString(option2)
        parcel.writeString(option3)
        parcel.writeInt(answerNr)
        parcel.writeString(difficulty)
        parcel.writeInt(chapitre_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Questions> {
        override fun createFromParcel(parcel: Parcel): Questions {
            return Questions(parcel)
        }

        override fun newArray(size: Int): Array<Questions?> {
            return arrayOfNulls(size)
        }
    }

}