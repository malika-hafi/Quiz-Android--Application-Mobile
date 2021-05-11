package com.example.ds2_project

object constants {
    //nom de la base de donnée
    const val DB_NAME="myQCM"
    const val DB_VERSION =2
    //nom des tables
    const val TABLE_UTILISATEUR_NAME="Utilisateur"
    const val TABLE_QUIZ_Name="quiz_questions"
    const val TABLE_CHAPITRE_NAME = "quiz_chapitre"


    //nom du colonne
    //la table utilisateur
    const val C_ID="_ID"
    const val COL_USERNAME="Username"
    const val COL_PASSWORD="password"

    // la table quiz_questions
    const val C_ID_QUESTIONS="_IDQuestions"
    const val COLUMN_QUESTION ="question"
    const val COLUMN_OPTION1 = "option1"
    const val COLUMN_OPTION2 = "option2";
    const val  COLUMN_OPTION3 = "option3";
    const val  COLUMN_ANSWER_NR = "answer_nr";
    const val  COLUMN_DIFICCULTY = "difficulty";
    const val COLUMN_CHAPITRE_ID = "chapitre_id"

    // la table chapitre
    const val C_ID_CHAPITRE="_IDChapitre"
    const val COLUMN_NAME = "name"



    // Creation des tables
    //la table uilisateur
    const val CREATE_TABLE_USER=("CREATE TABLE "+ TABLE_UTILISATEUR_NAME+"("
            + C_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_USERNAME+" TEXT,"
            + COL_PASSWORD+" TEXT)"
            )
    //la table quiz_questions
    const val CREATE_TABLE_QUIZ=("CREATE TABLE "+ TABLE_QUIZ_Name+"("
            + C_ID_QUESTIONS+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_QUESTION+" TEXT,"
            + COLUMN_OPTION1+" TEXT,"
            + COLUMN_OPTION2+" TEXT,"
            + COLUMN_OPTION3+" TEXT,"
            + COLUMN_ANSWER_NR+" INTEGER,"
            +COLUMN_DIFICCULTY+" TEXT,"
            +COLUMN_CHAPITRE_ID+" INTEGER, "
            +"FOREIGN KEY(" + COLUMN_CHAPITRE_ID + ") REFERENCES " +
            TABLE_CHAPITRE_NAME + "(" + C_ID_CHAPITRE + ")" + "ON DELETE CASCADE" +
            ")"
            )
    //la table chapitre
    const val CREATE_TABLE_CHAPITRE=("CREATE TABLE "+ TABLE_CHAPITRE_NAME +"("
            + C_ID_CHAPITRE+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME+" TEXT"
            +")"
            )

    const val DROP_TABLE_USER=("DROP TABLE IF EXISTS "+ TABLE_UTILISATEUR_NAME)
    const val DROP_TABLE_QUIZ=("DROP TABLE IF EXISTS "+ TABLE_QUIZ_Name)
    const val DROP_TABLE_CHAPITRE=("DROP TABLE IF EXISTS "+ TABLE_CHAPITRE_NAME)

}