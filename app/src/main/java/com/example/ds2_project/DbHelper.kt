package com.example.ds2_project

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ds2_project.constants.DB_NAME
import com.example.ds2_project.constants.DB_VERSION

class DbHelper(context: Context?): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    lateinit var db:SQLiteDatabase
    private var instance: DbHelper? = null

    override fun onCreate(MyDb: SQLiteDatabase?) {
        this.db= MyDb!!
        MyDb?.execSQL(constants.CREATE_TABLE_USER)
        MyDb?.execSQL(constants.CREATE_TABLE_QUIZ)
        MyDb?.execSQL(constants.CREATE_TABLE_CHAPITRE)
        FillChapitreTable()
        FillQuestionsTable()
    }

    override fun onUpgrade(MyDb: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        MyDb?.execSQL(constants.DROP_TABLE_USER)
        MyDb?.execSQL(constants.DROP_TABLE_QUIZ)
        MyDb?.execSQL(constants.DROP_TABLE_CHAPITRE)
        onCreate(MyDb)
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        db?.setForeignKeyConstraintsEnabled(true)
    }

    private fun FillChapitreTable() {
        var c1 =  Chapitre("Chapitre I");
        addChapitre(c1);
        var c2 =  Chapitre("Chapitre II");
        addChapitre(c2);
        var c3 =  Chapitre("Chapitre III");
        addChapitre(c3);
    }

    private fun addChapitre(chapitre: Chapitre) {
        val values = ContentValues()
        values.put(constants.COLUMN_NAME, chapitre.name)
        val result: Long = db.insert(constants.TABLE_CHAPITRE_NAME, null, values)

    }

    private fun FillQuestionsTable() {
        var question=Questions()
        var chapitre=Chapitre()
        //chapitre1
        //facile
        var q1=Questions("Quelle entreprise a développé Android?", "Apple", "Google", "Android Inc", 3, question.DIFFICULTY_EASY, chapitre.CHAPITRE1)
        addQuestions(q1)
        var q2=Questions("Quelle entreprise a acheté Android?", "Apple", "Nokia", "Google", 3, question.DIFFICULTY_EASY, chapitre.CHAPITRE1)
        addQuestions(q2)
        var q3=Questions("Android est basé sur quelle langue?", "C++", "VC++", "Java", 3, question.DIFFICULTY_EASY, chapitre.CHAPITRE1)
        addQuestions(q3)
        var q4=Questions("Quelle est la dernière version mobile d'android", "3.0 (Honeycomb)", "2.3 (Gingerbread)", "2.6", 2, question.DIFFICULTY_EASY, chapitre.CHAPITRE1)
        addQuestions(q4)
        var q5=Questions("Pour créer une interface utilisateur sous Android, vous devez utiliser?", "Eclipse", "java and XML", "java and SQL",     2, question.DIFFICULTY_EASY, chapitre.CHAPITRE1)
        addQuestions(q5)
        var q8=Questions("Si vous souhaitez augmenter l'espace blanc entre les widgets, vous devrez utiliser la propriété_____________.", "Android:padding", "Android:digits", "Android:capitalize", 1, question.DIFFICULTY_EASY, chapitre.CHAPITRE1)
        addQuestions(q8)
        var q10=Questions("Android est basé sur quelle langue?", "C++", "VC++", "Java", 3, question.DIFFICULTY_EASY, chapitre.CHAPITRE1)
        addQuestions(q10)

        //moyen
        var q11=Questions("Pour créer une interface utilisateur sous Android, vous devez utiliser?", "Eclipse", "java and XML", "java and SQL",     2, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE1)
        addQuestions(q11)
        var q12=Questions("Android est basé sur quelle langue?", "C++", "VC++", "Java", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE1)
        addQuestions(q12)
        var q13=Questions("Lequel des éléments suivants est / sont un composant du fichier APK?", "Resources", "Delvik Executable", "a et b", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE1)
        addQuestions(q13)
        var q15=Questions("Android est basé sur quelle langue?", "C++", "VC++", "Java", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE1)
        addQuestions(q15)
        var q17=Questions("Vous pouvez créer un émulateur pour simuler la configuration d'un type particulier d'appareil Android à l'aide d'un outil tel que ___.", "theme Editor", "Android sdk Manager", "AVD Manager", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE1)
        addQuestions(q17)
        var q19=Questions("Lequel des éléments suivants contient le code source Java de l'application?", "res/", "assets/", "src/", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE1)
        addQuestions(q19)
        var q20=Questions("Pour créer une interface utilisateur sous Android, vous devez utiliser?", "Eclipse", "java and XML", "java and SQL",     2, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE1)
        addQuestions(q20)

        //difficile
        var q21=Questions("Un SDK Android est requis pour développer l'application pour Android?", "Oui", "Non", "pas nécessairement", 1, question.DIFFICULTY_HARD, chapitre.CHAPITRE1)
        addQuestions(q21)
        var q22=Questions("Quelle est la dernière version d'Android?", "Android 9", "Android 10", "Android 11", 3, question.DIFFICULTY_HARD, chapitre.CHAPITRE1)
        addQuestions(q22)
        var q23=Questions("Lequel des éléments suivants ne comporte aucun composant d'interface utilisateur et s'exécute en tant que processus d'arrière-plan?", "services", "simulator", "Emulator", 1, question.DIFFICULTY_HARD, chapitre.CHAPITRE1)
        addQuestions(q23)
        var q24=Questions("Un SDK Android est requis pour développer l'application pour Android?", "Oui", "Non", "pas nécessairement", 1, question.DIFFICULTY_HARD, chapitre.CHAPITRE1)
        addQuestions(q24)
        var q26=Questions("Pour créer une interface utilisateur sous Android, vous devez utiliser?", "Eclipse", "java and XML", "java and SQL",     2, question.DIFFICULTY_HARD, chapitre.CHAPITRE1)
        addQuestions(q26)
        var q29=Questions("Quelle est la dernière version d'Android?", "Android 9", "Android 10", "Android 11", 3, question.DIFFICULTY_HARD, chapitre.CHAPITRE1)
        addQuestions(q29)
        var q30=Questions("Un SDK Android est requis pour développer l'application pour Android?", "Oui", "Non", "pas nécessairement", 1, question.DIFFICULTY_HARD, chapitre.CHAPITRE1)
        addQuestions(q30)

        //chapitre2
        //facile
        var q31=Questions("Dans ___________, l'expéditeur spécifie le type de destinataire?", "Implicit intent", "Explicit intent", "a et b", 1, question.DIFFICULTY_EASY, chapitre.CHAPITRE2)
        addQuestions(q31)
        var q34=Questions("Choisissez la bonne option concernant l'activité dans Android.", "Une activité est une fenêtre qui contient l'interface utilisateur de votre application.", "Une application ne peut avoir qu'une seule activité.", "aucune des réponses", 1, question.DIFFICULTY_EASY, chapitre.CHAPITRE2)
        addQuestions(q34)
        var q36=Questions("Choisissez la bonne option concernant l'activité dans Android.", "Une activité est une fenêtre qui contient l'interface utilisateur de votre application.", "Une application ne peut avoir qu'une seule activité.", "aucune des réponses", 1, question.DIFFICULTY_EASY, chapitre.CHAPITRE2)
        addQuestions(q36)
        var q37=Questions("La classe Log prend en charge quels types de journaux?", "Error", "Warning", "Debug", 2, question.DIFFICULTY_EASY, chapitre.CHAPITRE2)
        addQuestions(q37)
        var q38=Questions("Quelle est la dernière version d'Android?", "Android 9", "Android 10", "Android 11", 3, question.DIFFICULTY_EASY, chapitre.CHAPITRE2)
        addQuestions(q38)
        var q39=Questions("Lequel des éléments suivants contient le code source Java de l'application?", "res/", "assets/", "src/", 3, question.DIFFICULTY_EASY, chapitre.CHAPITRE2)
        addQuestions(q39)
        var q40=Questions("Un SDK Android est requis pour développer l'application pour Android?", "Oui", "Non", "pas nécessairement", 1, question.DIFFICULTY_EASY, chapitre.CHAPITRE2)
        addQuestions(q40)
        //MOYEN
        var q41=Questions("Si vous avez besoin de renvoyer des données d'une activité, quelle méthode devez-vous utiliser?", "startActivity()", "startActivityForResult()", "ActivityForResult()", 2, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE2)
        addQuestions(q41)
        var q42=Questions("Si vous souhaitez naviguer d'une activité à une autre, Android vous propose quelle classe?", "Object", "startActivity", "Intent", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE2)
        addQuestions(q42)
        var q43=Questions("Un des types d'intent dans Android est", "Explicit intents", "Aucune des réponses ci-dessus.", "Start intents", 1, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE2)
        addQuestions(q43)
        var q44=Questions("Lequel des éléments suivants contient le code source Java de l'application?", "res/", "assets/", "src/", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE2)
        addQuestions(q44)
        var q48=Questions("Quelle est la dernière version d'Android?", "Android 9", "Android 10", "Android 11", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE2)
        addQuestions(q48)
        var q49=Questions("Choisissez la bonne option concernant l'activité dans Android.", "Une activité est une fenêtre qui contient l'interface utilisateur de votre application.", "Une application ne peut avoir qu'une seule activité.", "aucune des réponses", 1, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE2)
        addQuestions(q49)
        var q50=Questions("La classe Log prend en charge quels types de journaux?", "Error", "Warning", "Debug", 2, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE2)
        addQuestions(q50)

        //HARD
        var q51=Questions("Quelle est / sont l'option de stockage disponible dans Android?", "SharedPreferences", "SQLiteDatabase", "ContentProvider", 1, question.DIFFICULTY_HARD, chapitre.CHAPITRE2)
        addQuestions(q51)
        var q52=Questions("SharedPreferences stocke les données dans quel format?", "TXT", "XML", "DOC", 2, question.DIFFICULTY_HARD, chapitre.CHAPITRE2)
        addQuestions(q52)
        var q53=Questions("Quels objets stockent uniquement le type de données primitif?", "SharedPreferences", "SQLiteDatabase", "ContentProvider", 1, question.DIFFICULTY_HARD, chapitre.CHAPITRE2)
        addQuestions(q53)
        var q54=Questions("Pour écrire des fichiers sur le stockage externe, quelle autorisation vous allez écrire dans le fichier AndroidManifest.xml", "WRITE_STORAGE", "WRITE_EXTERNAL_DATA", "WRITE_EXTERNAL_STORAGE", 3, question.DIFFICULTY_HARD, chapitre.CHAPITRE2)
        addQuestions(q54)
        var q56=Questions("Quels objets stockent uniquement le type de données primitif?", "SharedPreferences", "SQLiteDatabase", "ContentProvider", 1, question.DIFFICULTY_HARD, chapitre.CHAPITRE2)
        addQuestions(q56)
        var q59=Questions("La classe Log prend en charge quels types de journaux?", "Error", "Warning", "Debug", 2, question.DIFFICULTY_HARD, chapitre.CHAPITRE2)
        addQuestions(q59)
        var q60=Questions("Quelle est la dernière version d'Android?", "Android 9", "Android 10", "Android 11", 3, question.DIFFICULTY_HARD, chapitre.CHAPITRE2)
        addQuestions(q60)

        //chapitre3
        //FACILE
        var q61=Questions("Si vous souhaitez partager les données d'une application vers d'autres applications, quel objet utiliserez-vous?", "SQLiteDatabases", "InternalStorage", "ContentProvider", 3, question.DIFFICULTY_EASY, chapitre.CHAPITRE3)
        addQuestions(q61)
        var q62=Questions("Quelle est la forme complète d'AVD sous Android?", "Android Virtual Device", "Android Virtual Display", "Actual Virtual Display", 1, question.DIFFICULTY_EASY, chapitre.CHAPITRE3)
        addQuestions(q62)
        var q63=Questions("Qu'est-ce qui fonctionne en arrière-plan et n'a pas d'interface utilisateur?", "PendingIntent", "Service", "Intent", 2, question.DIFFICULTY_EASY, chapitre.CHAPITRE3)
        addQuestions(q63)
        var q64=Questions("Lequel n'est pas un surnom du système d'exploitation Android?", "Honeycomb", "CupCake", "Muffin", 3, question.DIFFICULTY_EASY, chapitre.CHAPITRE3)
        addQuestions(q64)
        var q68=Questions("Qu'est-ce qui fonctionne en arrière-plan et n'a pas d'interface utilisateur?", "PendingIntent", "Service", "Intent", 2, question.DIFFICULTY_EASY, chapitre.CHAPITRE3)
        addQuestions(q68)
        var q69=Questions("Lequel n'est pas un surnom du système d'exploitation Android?", "Honeycomb", "CupCake", "Muffin", 3, question.DIFFICULTY_EASY, chapitre.CHAPITRE3)
        addQuestions(q69)
        var q70=Questions("SharedPreferences stocke les données dans quel format?", "TXT", "XML", "DOC", 2, question.DIFFICULTY_EASY, chapitre.CHAPITRE3)
        addQuestions(q70)

        //moyen
        var q71=Questions("Comment arrêter les services sous Android?", "finish()", "system.exit()", "stopSelf() and stopService()", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE3)
        addQuestions(q71)
        var q72=Questions("Comment stocker des données structurées lourdes dans Android?", "Shared Preferences", "Cursor", "SQlite database", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE3)
        addQuestions(q72)
        var q73=Questions("Qu'est-ce que la classe singleton dans Android?", "Une classe qui ne peut créer qu'un seul objet", "Anonymous class", "Java class", 1, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE3)
        addQuestions(q73)
        var q75=Questions("Fragment dans Android peut être trouvé via", "getContext.findFragmentByID()", "findFragmentByID()", "FragmentManager.findFragmentByID()", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE3)
        addQuestions(q75)
        var q78=Questions("Comment arrêter les services sous Android?", "finish()", "system.exit()", "stopSelf() and stopService()", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE3)
        addQuestions(q78)
        var q79=Questions("SharedPreferences stocke les données dans quel format?", "TXT", "XML", "DOC", 2, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE3)
        addQuestions(q79)
        var q80=Questions("Fragment dans Android peut être trouvé via", "getContext.findFragmentByID()", "findFragmentByID()", "FragmentManager.findFragmentByID()", 3, question.DIFFICULTY_MEDIUM, chapitre.CHAPITRE3)
        addQuestions(q80)
        //hard
        var q81=Questions("Lesquels parmi les éléments suivants est un gestionnaire de disposition Android RecyclerView?", "RecyclingManager", "LinearLayoutManager", "CircularLayoutManager", 2, question.DIFFICULTY_HARD, chapitre.CHAPITRE3)
        addQuestions(q81)
        var q82=Questions("Lesquelles des suivantes sont des méthodes dans les cycles de vie des activités et des fragments?.", "Theme Editor", "Android SDK Manager", "AVD Manager", 3, question.DIFFICULTY_HARD, chapitre.CHAPITRE3)
        addQuestions(q82)
        var q83=Questions("Vous pouvez créer un émulateur pour simuler la configuration d'un type particulier d'appareil Android à l'aide d'un outil tel que ___.", "onClick()", "onStart()", "onPause()", 2, question.DIFFICULTY_HARD, chapitre.CHAPITRE3)
        addQuestions(q83)
        var q84=Questions("SharedPreferences stocke les données dans quel format?", "TXT", "XML", "DOC", 2, question.DIFFICULTY_HARD, chapitre.CHAPITRE3)
        addQuestions(q84)
        var q86=Questions("Qu'est-ce que la classe singleton dans Android?", "Une classe qui ne peut créer qu'un seul objet", "Anonymous class", "Java class", 1, question.DIFFICULTY_HARD, chapitre.CHAPITRE3)
        addQuestions(q86)
        var q88=Questions("Fragment dans Android peut être trouvé via", "getContext.findFragmentByID()", "findFragmentByID()", "FragmentManager.findFragmentByID()", 3, question.DIFFICULTY_HARD, chapitre.CHAPITRE3)
        addQuestions(q88)
        var q89=Questions("Vous pouvez créer un émulateur pour simuler la configuration d'un type particulier d'appareil Android à l'aide d'un outil tel que ___.", "onClick()", "onStart()", "onPause()", 2, question.DIFFICULTY_HARD, chapitre.CHAPITRE3)
        addQuestions(q89)

    }

    //ajouter les questions à la base de donnée
    private fun addQuestions(question: Questions){

        val values = ContentValues()
        values.put(constants.COLUMN_QUESTION, question.question)
        values.put(constants.COLUMN_OPTION1, question.option1)
        values.put(constants.COLUMN_OPTION2, question.option2)
        values.put(constants.COLUMN_OPTION3, question.option3)
        values.put(constants.COLUMN_ANSWER_NR, question.answerNr)
        values.put(constants.COLUMN_DIFICCULTY, question.difficulty)
        values.put(constants.COLUMN_CHAPITRE_ID, question.chapitre_id)
        val result: Long = db.insert(constants.TABLE_QUIZ_Name, null, values)

    }
    fun getAllChapitre(): List<Chapitre>? {
        val chapitreList: ArrayList<Chapitre> = ArrayList<Chapitre>()
        db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + constants.TABLE_CHAPITRE_NAME, null)

        if(cursor.moveToFirst()){
            do {
                var chapitre:Chapitre= Chapitre()
                chapitre.id=(cursor.getInt(cursor.getColumnIndex(constants.C_ID_CHAPITRE)))
                chapitre.name=(cursor.getString(cursor.getColumnIndex(constants.COLUMN_NAME)))
                chapitreList.add(chapitre)
            }while (cursor.moveToNext())


        }
        cursor.close()
        return chapitreList
    }
    //retourné toutes les questions
    fun getAllQuestions():ArrayList<Questions>{
        var listQuestions= ArrayList<Questions>()
        val myDb=this.readableDatabase
        var cursor:Cursor=myDb.rawQuery("SELECT * FROM ${constants.TABLE_QUIZ_Name}", null)
        if(cursor.moveToFirst()){
            do{
                var question=Questions()
                question.id=(cursor.getInt(cursor.getColumnIndex(constants.C_ID_QUESTIONS)));
                question.question=(cursor.getString(cursor.getColumnIndex(constants.COLUMN_QUESTION)))
                question.option1=(cursor.getString(cursor.getColumnIndex(constants.COLUMN_OPTION1)))
                question.option2=(cursor.getString(cursor.getColumnIndex(constants.COLUMN_OPTION2)))
                question.option3=(cursor.getString(cursor.getColumnIndex(constants.COLUMN_OPTION3)))
                question.answerNr=(cursor.getInt(cursor.getColumnIndex(constants.COLUMN_ANSWER_NR)))
                question.chapitre_id=(cursor.getInt(cursor.getColumnIndex(constants.COLUMN_CHAPITRE_ID)))
                listQuestions.add(question)
            }while (cursor.moveToNext())
        }

        return listQuestions
    }
    //retourné toutes les questions selon le nieveau de difficulté et le chapitre
    fun getQuestions(chapitre_id: Int, difficulty: String):ArrayList<Questions>{
        var listQuestions= ArrayList<Questions>()
        val db=this.readableDatabase
        val selection: String = constants.COLUMN_CHAPITRE_ID.toString() + " = ? " +
                " AND " + constants.COLUMN_DIFICCULTY + " = ? "
        val selectionArgs: Array<String> = arrayOf(java.lang.String.valueOf(chapitre_id), difficulty)
        val cursor = db.query(
            constants.TABLE_QUIZ_Name,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        if(cursor.moveToFirst()){
            do{
                var question=Questions()
                question.id=(cursor.getInt(cursor.getColumnIndex(constants.C_ID_QUESTIONS)));
                question.question=(cursor.getString(cursor.getColumnIndex(constants.COLUMN_QUESTION)))
                question.option1=(cursor.getString(cursor.getColumnIndex(constants.COLUMN_OPTION1)))
                question.option2=(cursor.getString(cursor.getColumnIndex(constants.COLUMN_OPTION2)))
                question.option3=(cursor.getString(cursor.getColumnIndex(constants.COLUMN_OPTION3)))
                question.answerNr=(cursor.getInt(cursor.getColumnIndex(constants.COLUMN_ANSWER_NR)))
                question.chapitre_id=(cursor.getInt(cursor.getColumnIndex(constants.COLUMN_CHAPITRE_ID)))
                listQuestions.add(question)
            }while (cursor.moveToNext())
        }

        return listQuestions
    }

    fun add_user(username: String, password: String):Long {
        val myDB = this.writableDatabase
        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)
        val result: Long = myDB.insert(constants.TABLE_UTILISATEUR_NAME, null, values)

        return result
    }
    fun checkUsername(username: String):Boolean{
        val myDB=this.writableDatabase
        var cusror: Cursor = myDB.rawQuery("SELECT * FROM ${constants.TABLE_UTILISATEUR_NAME} WHERE ${constants.COL_USERNAME} ='${username}'", null)
        return cusror.count>0
    }
    fun checkUserNamePassword(username: String, password: String):Boolean{
        val myDB=this.writableDatabase
        var cusror: Cursor = myDB.rawQuery("SELECT * FROM ${constants.TABLE_UTILISATEUR_NAME} WHERE ${constants.COL_USERNAME} ='$username' AND ${constants.COL_PASSWORD}='$password'", null)
        return cusror.count>0
    }


}
