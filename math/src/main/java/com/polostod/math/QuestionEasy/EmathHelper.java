package com.polostod.math.QuestionEasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.polostod.math.HighScore.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by polostod-dev on 15/04/16.
 */
public class EmathHelper extends SQLiteOpenHelper {

    private static final int Database_Version =1;
    private static final String Database_Name ="mathlearn";
    public static final String Table_Quest = "quest";
    public static final String Table_Score = "score";
    public static final String KEY_ID = "qid";
    public static final String KEY_QUES = "question";
    public static final String KEY_ANSWER = "answer"; // correct option
    public static final String KEY_OPTA = "opta"; // option a
    public static final String KEY_OPTB = "optb"; // option b
    public static final String KEY_OPTC = "optc"; // option c
    public static final String KEY_OPTD = "optd";
    public static final String KEY_LEVEL = "level";
    public static final String id = "id";
    public static final String name = "name";
    public static final String level = "level";
    public static final String highscore = "score";
    private SQLiteDatabase dbase;
    public EmathHelper(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + Table_Quest + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT, " + KEY_OPTD + " TEXT, " + KEY_LEVEL + " TEXT)";
        String sql1 ="CREATE TABLE IF NOT EXISTS " + Table_Score + " ( "
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + name
                + " TEXT, " + level + " TEXT, " + highscore + " REAL)";
        db.execSQL(sql);
        db.execSQL(sql1);
        addQuestion();
        // db.close();
    }

    private void addQuestion() {
        //easy question
        Question q1 = new Question("5 + 2 = ?", "7", "8", "6", "9","7","easy");
        this.addQuestion(q1);
        Question q2 = new Question("2 + 18 = ?", "18", "19", "20", "27", "20","easy");
        this.addQuestion(q2);
        Question q3 = new Question("10 - 3 = ?", "6", "7", "8", "5", "7","easy");
        this.addQuestion(q3);
        Question q4 = new Question("5 + 7 = ?", "12", "13", "14", "15", "12","easy");
        this.addQuestion(q4);
        Question q5 = new Question("3 - 1 = ?", "1", "3", "2", "0", "2","easy");
        this.addQuestion(q5);
        Question q6 = new Question("0 + 1 = ?", "1", "0", "10", "3", "1","easy");
        this.addQuestion(q6);
        Question q7 = new Question("9 - 9 = ?", "0", "9", "1", "3", "0","easy");
        this.addQuestion(q7);
        Question q8 = new Question("3 + 6 = ?", "8", "7", "9", "12", "9","easy");
        this.addQuestion(q8);
        Question q9 = new Question("1 + 5 = ?", "6", "7", "5", "9", "6","easy");
        this.addQuestion(q9);
        Question q10 = new Question("7 - 6 = ?", "3", "2", "1", "5", "1","easy");
        this.addQuestion(q10);
        Question q11 = new Question("7 - 2 = ?", "7", "6", "5", "1", "5","easy");
        this.addQuestion(q11);
        Question q12 = new Question("3 + 5 = ?", "8", "7", "5","9", "8","easy");
        this.addQuestion(q12);
        Question q13 = new Question("0 + 6 = ?", "7", "6", "5", "0", "6","easy");
        this.addQuestion(q13);
        Question q14 = new Question("12 - 10 = ?", "1", "2", "3", "12", "2","easy");
        this.addQuestion(q14);
        Question q15 = new Question("12 + 2 = ?", "14", "15", "16", "11", "14","easy");
        this.addQuestion(q15);
        Question q16 = new Question("2 - 1 = ?", "2", "1", "0", "3", "1","easy");
        this.addQuestion(q16);
        Question q17 = new Question("6 - 6 = ?", "6", "12", "0", "1", "0","easy");
        this.addQuestion(q17);
        Question q18 = new Question("5 - 1 = ?", "4", "3", "2", "5", "4","easy");
        this.addQuestion(q18);
        Question q19 = new Question("4 + 2 = ?", "6", "7", "5", "2", "6","easy");
        this.addQuestion(q19);
        Question q20 = new Question("5 + 1 = ?", "6", "7", "5", "4", "6","easy");
        this.addQuestion(q20);
        Question q21 = new Question("5 - 4 = ?", "5", "4", "1", "9", "1","easy");
        this.addQuestion(q21);
        Question q22 = new Question("8 - 4 = ?", "5", "4", "1", "2", "4","easy");
        this.addQuestion(q22);
        Question q23 = new Question("3 + 4 = ?", "5", "4", "1", "7", "7","easy");
        this.addQuestion(q23);
        Question q24 = new Question("6 - 4 = ?", "1", "4", "2", "3", "2","easy");
        this.addQuestion(q24);
        Question q25 = new Question("10 - 4 = ?", "5", "4", "6", "9", "6","easy");
        this.addQuestion(q25);
        Question q26 = new Question("5 + 5 = ?", "11", "0", "10", "9","10","easy");
        this.addQuestion(q26);
        Question q27 = new Question("8 - 7 = ?", "0", "1", "2", "3", "1","easy");
        this.addQuestion(q27);
        Question q28 = new Question("7 - 3 = ?", "4", "3", "5", "2", "4","easy");
        this.addQuestion(q28);
        Question q29 = new Question("5 + 1 = ?", "5", "7", "6", "4", "6","easy");
        this.addQuestion(q29);
        Question q30 = new Question("3 - 3 = ?", "1", "3", "2", "0", "0","easy");
        this.addQuestion(q30);
        Question q31 = new Question("0 + 4 = ?", "4", "0", "2", "3", "4","easy");
        this.addQuestion(q31);
        Question q32 = new Question("9 - 4 = ?", "5", "6", "4", "3", "5","easy");
        this.addQuestion(q32);
        Question q33 = new Question("3 + 6 = ?", "8", "7", "9", "12", "9","easy");
        this.addQuestion(q33);
        Question q34 = new Question("3 + 5 = ?", "6", "7", "8", "9", "8","easy");
        this.addQuestion(q34);
        Question q35 = new Question("9 - 6 = ?", "3", "2", "1", "5", "3","easy");
        this.addQuestion(q35);
        Question q36 = new Question("10 - 2 = ?", "7", "6", "5", "8", "8","easy");
        this.addQuestion(q36);
        Question q37 = new Question("3 + 11 = ?", "12", "13", "14","15", "14","easy");
        this.addQuestion(q37);
        Question q38 = new Question("7 + 6 = ?", "11", "12", "10", "13", "13","easy");
        this.addQuestion(q38);
        Question q39 = new Question("5 - 2 = ?", "1", "2", "3", "4", "3","easy");
        this.addQuestion(q39);
        Question q40 = new Question("1 + 2 = ?", "4", "3", "1", "2", "3","easy");
        this.addQuestion(q40);
        Question q41 = new Question("4 - 1 = ?", "2", "1", "0", "3", "3","easy");
        this.addQuestion(q41);
        Question q42 = new Question("6 - 3 = ?", "4", "3", "0", "2", "3","easy");
        this.addQuestion(q42);
        Question q43 = new Question("3 - 2 = ?", "4", "3", "2", "1", "1","easy");
        this.addQuestion(q43);
        Question q44 = new Question("4 + 4 = ?", "6", "7", "5", "8", "8","easy");
        this.addQuestion(q44);
        Question q45 = new Question("6 + 2 = ?", "6", "4", "8", "5", "8","easy");
        this.addQuestion(q45);
        Question q46 = new Question("7 - 4 = ?", "5", "4", "1", "3", "3","easy");
        this.addQuestion(q46);
        Question q47 = new Question("9 - 5 = ?", "5", "4", "1", "2", "4","easy");
        this.addQuestion(q47);
        Question q48 = new Question("3 + 7 = ?", "10", "8", "9", "11", "10","easy");
        this.addQuestion(q48);
        Question q49 = new Question("6 - 5 = ?", "1", "4", "2", "3", "1","easy");
        this.addQuestion(q49);
        Question q50 = new Question("12 - 4 = ?", "5", "8", "6", "9", "8","easy");
        this.addQuestion(q50);
        // END OF EASY
        //medium question
        Question q51 = new Question("1 x 2 = ?", "3", "2", "1", "4","2","medium");
        this.addQuestion(q51);
        Question q52 = new Question("1 x 3 = ?", "4", "2", "3", "5", "3","medium");
        this.addQuestion(q52);
        Question q53 = new Question("5 x 1 = ?", "6", "7", "8", "5", "5","medium");
        this.addQuestion(q53);
        Question q54 = new Question("1 x 4 = ?", "4", "5", "3", "6", "4","medium");
        this.addQuestion(q54);
        Question q55 = new Question("8 x 1 = ?", "9", "7", "6", "8", "8","medium");
        this.addQuestion(q55);
        Question q56 = new Question("1 x 9 = ?", "10", "9", "8", "11", "9","medium");
        this.addQuestion(q56);
        Question q57 = new Question("1 x 10 = ?", "9", "11", "10", "1", "10","medium");
        this.addQuestion(q57);
        Question q58 = new Question("2 x 2 = ?", "0", "1", "2", "4", "4","medium");
        this.addQuestion(q58);
        Question q59 = new Question("2 x 6 = ?", "6", "4", "12", "8", "12","medium");
        this.addQuestion(q59);
        Question q60 = new Question("2 x 4 = ?", "6", "8", "2", "4", "8","medium");
        this.addQuestion(q60);
        Question q61 = new Question("2 x 7 = ?", "7", "6", "5", "14", "14","medium");
        this.addQuestion(q61);
        Question q62 = new Question("9 x 2 = ?", "8", "7", "18","9", "18","medium");
        this.addQuestion(q62);
        Question q63 = new Question("2 x 10 = ?", "12", "8", "5", "20", "6","medium");
        this.addQuestion(q63);
        Question q64 = new Question("11 x 2 = ?", "22", "13", "9", "12", "22","medium");
        this.addQuestion(q64);
        Question q65 = new Question("3 x 2 = ?", "6", "1", "5", "4", "6","medium");
        this.addQuestion(q65);
        Question q66 = new Question("1 / 1 = ?", "2", "1", "0", "3", "1","medium");
        this.addQuestion(q66);
        Question q67 = new Question("2 / 2 = ?", "6", "4", "0", "1", "1","medium");
        this.addQuestion(q17);
        Question q68 = new Question("2 / 1 = ?", "4", "3", "2", "1", "2","medium");
        this.addQuestion(q68);
        Question q69 = new Question("3 / 1 = ?", "6", "4", "3", "2", "3","medium");
        this.addQuestion(q69);
        Question q70 = new Question("3 / 3 = ?", "6", "7", "5", "1", "1","medium");
        this.addQuestion(q70);
        Question q71 = new Question("4 / 2 = ?", "5", "4", "1", "2", "2","medium");
        this.addQuestion(q71);
        Question q72 = new Question("8 / 4 = ?", "5", "4", "1", "2", "2","medium");
        this.addQuestion(q72);
        Question q73 = new Question("9 / 3 = ?", "3", "4", "1", "7", "3","medium");
        this.addQuestion(q73);
        Question q74 = new Question("10 / 2 = ?", "5", "4", "12", "8", "5","medium");
        this.addQuestion(q74);
        Question q75 = new Question("6 / 2 = ?", "8", "4", "6", "3", "3","medium");
        this.addQuestion(q75);
        Question q76 = new Question("7 / 7 = ?", "11", "0", "14", "1","1","medium");
        this.addQuestion(q76);
        Question q77 = new Question("8 / 2 = ?", "10", "6", "2", "4", "4","medium");
        this.addQuestion(q77);
        Question q78 = new Question("9 / 9 = ?", "4", "3", "1", "2", "1","medium");
        this.addQuestion(q78);
        Question q79 = new Question("2 x 5 = ?", "5", "7", "10", "4", "10","medium");
        this.addQuestion(q79);
        Question q80 = new Question("5 x 4 = ?", "1", "3", "2", "20", "20","medium");
        this.addQuestion(q80);
        Question q81 = new Question("4 x 6 = ?", "24", "0", "2", "3", "24","medium");
        this.addQuestion(q81);
        Question q82 = new Question("5 x 6 = ?", "5", "6", "4", "30", "30","medium");
        this.addQuestion(q82);
        Question q83 = new Question("6 x 6 = ?", "8", "36", "9", "12", "36","medium");
        this.addQuestion(q83);
        Question q84 = new Question("7 x 5 = ?", "6", "7", "35", "9", "35","medium");
        this.addQuestion(q84);
        Question q85 = new Question("7 x 7 = ?", "3", "49", "1", "5", "49","medium");
        this.addQuestion(q85);
        Question q86 = new Question("8 x 2 = ?", "7", "16", "5", "8", "16","medium");
        this.addQuestion(q86);
        Question q87 = new Question("8 x 5 = ?", "40", "13", "14","15", "40","medium");
        this.addQuestion(q87);
        Question q88 = new Question("9 x 5 = ?", "11", "12", "45", "13", "45","medium");
        this.addQuestion(q88);
        Question q89 = new Question("10 x 10 = ?", "100", "20", "23", "24", "100","medium");
        this.addQuestion(q89);
        Question q90 = new Question("9 x 2 = ?", "14", "13", "18", "12", "18","medium");
        this.addQuestion(q90);
        Question q91 = new Question("10 x 1 = ?", "10", "9", "20", "11", "10","medium");
        this.addQuestion(q91);
        Question q92 = new Question("8 x 8 = ?", "64", "16", "0", "2", "64","medium");
        this.addQuestion(q92);
        Question q93 = new Question("6 x 9 = ?", "54", "23", "21", "11", "54","medium");
        this.addQuestion(q93);
        Question q94 = new Question("8 / 8 = ?", "6", "7", "1", "8", "1","medium");
        this.addQuestion(q94);
        Question q95 = new Question("7 x 8 = ?", "62", "41", "18", "56", "56","medium");
        this.addQuestion(q95);
        Question q96 = new Question("2 x 7 = ?", "5", "14", "9", "3", "14","medium");
        this.addQuestion(q96);
        Question q97 = new Question("10 x 3 = ?", "30", "14", "13", "7", "30","medium");
        this.addQuestion(q97);
        Question q98 = new Question("7 x 4 = ?", "10", "28", "9", "11", "28","medium");
        this.addQuestion(q98);
        Question q99 = new Question("9 x 9 = ?", "81", "41", "23", "18", "81","medium");
        this.addQuestion(q99);
        Question q100 = new Question("9 x 3 = ?", "5", "8", "27", "11", "27","medium");
        this.addQuestion(q100);
        //end of medium question
        //hard question
        Question q101 = new Question("11 x 2 = ?", "13", "8", "22", "9","22","hard");
        this.addQuestion(q101);
        Question q102 = new Question("12 x 4 = ?", "48", "24", "20", "16", "48","hard");
        this.addQuestion(q102);
        Question q103 = new Question("16 x 2 = ?", "18", "14", "28", "32", "32","hard");
        this.addQuestion(q103);
        Question q104 = new Question("9 x 10 = ?", "19", "1", "90", "80", "90","hard");
        this.addQuestion(q104);
        Question q105 = new Question("14 - 11 = ?", "1", "3", "2", "0", "3","hard");
        this.addQuestion(q105);
        Question q106 = new Question("90 + 20 = ?", "91", "10", "110", "73", "110","hard");
        this.addQuestion(q106);
        Question q107 = new Question("20 / 2 = ?", "10", "9", "1", "22", "10","hard");
        this.addQuestion(q107);
        Question q108 = new Question("22 / 2 = ?", "8", "11", "9", "12", "11","hard");
        this.addQuestion(q108);
        Question q109 = new Question("25 / 5 = ?", "6", "7", "5", "30", "5","hard");
        this.addQuestion(q109);
        Question q110 = new Question("55 / 5 = ?", "60", "22", "11", "5", "11","hard");
        this.addQuestion(q110);
        Question q111 = new Question("20 - 19 = ?", "7", "6", "5", "1", "1","hard");
        this.addQuestion(q111);
        Question q112 = new Question("32 - 12 = ?", "8", "32", "20","19", "20","hard");
        this.addQuestion(q112);
        Question q113 = new Question("40 - 30 = ?", "70", "16", "15", "10", "10","hard");
        this.addQuestion(q113);
        Question q114 = new Question("50 - 19 = ?", "14", "12", "31", "12", "31","hard");
        this.addQuestion(q114);
        Question q115 = new Question("60 - 40 = ?", "14", "15", "20", "11", "20","hard");
        this.addQuestion(q115);
        Question q116 = new Question("100 / 4 = ?", "96", "104", "25", "20", "25","hard");
        this.addQuestion(q116);
        Question q117 = new Question("80 / 2 = ?", "16", "12", "40", "21", "40","hard");
        this.addQuestion(q117);
        Question q118 = new Question("92 - 10 = ?", "44", "73", "82", "35", "82","hard");
        this.addQuestion(q118);
        Question q119 = new Question("10 x 12 = ?", "10", "2", "22", "120", "120","hard");
        this.addQuestion(q119);
        Question q120 = new Question("20 x 11 = ?", "200", "11", "31", "220", "220","hard");
        this.addQuestion(q120);
        Question q121 = new Question("32 - 12 = ?", "14", "23", "44", "9", "20","hard");
        this.addQuestion(q121);
        Question q122 = new Question("12 x 12 = ?", "24", "144", "41", "22", "144","hard");
        this.addQuestion(q122);
        Question q123 = new Question("15 - 12 = ?", "5", "4", "3", "27", "3","hard");
        this.addQuestion(q123);
        Question q124 = new Question("100 - 80 = ?", "180", "14", "20", "23", "20","hard");
        this.addQuestion(q124);
        Question q125 = new Question("80 + 20 = ?", "25", "42", "60", "100", "100","hard");
        this.addQuestion(q125);
        Question q126 = new Question("77 - 7 = ?", "11", "70", "10", "9","70","hard");
        this.addQuestion(q126);
        Question q127 = new Question("90 - 50 = ?", "40", "41", "32", "43", "40","hard");
        this.addQuestion(q127);
        Question q128 = new Question("83 - 80 = ?", "4", "3", "5", "2", "3","hard");
        this.addQuestion(q128);
        Question q129 = new Question("3 x 11 = ?", "9", "7", "33", "14", "33","hard");
        this.addQuestion(q129);
        Question q130 = new Question("4 x 7 = ?", "1", "3", "28", "11", "28","hard");
        this.addQuestion(q130);
        Question q131 = new Question("32 + 32 = ?", "64", "60", "52", "33", "64","hard");
        this.addQuestion(q131);
        Question q132 = new Question("40 - 11 = ?", "51", "29", "41", "23", "29","hard");
        this.addQuestion(q132);
        Question q133 = new Question("20 x 4 = ?", "80", "17", "29", "12", "80","hard");
        this.addQuestion(q133);
        Question q134 = new Question("25 x 2 = ?", "25", "27", "23", "50", "50","hard");
        this.addQuestion(q134);
        Question q135 = new Question("50 x 3 = ?", "53", "25", "100", "150", "150","hard");
        this.addQuestion(q135);
        Question q136 = new Question("70 + 20 = ?", "17", "26", "90", "50", "90","hard");
        this.addQuestion(q136);
        Question q137 = new Question("80 + 5 = ?", "12", "13", "14","85", "85","hard");
        this.addQuestion(q137);
        Question q138 = new Question("92 - 8 = ?", "100", "12", "84", "13", "84","hard");
        this.addQuestion(q138);
        Question q139 = new Question("11 - 4 = ?", "7", "2", "15", "14", "7","hard");
        this.addQuestion(q139);
        Question q140 = new Question("40 - 50 = ?", "34", "90", "-10", "20", "-10","hard");
        this.addQuestion(q140);
        Question q141 = new Question("20 x 2 = ?", "22", "31", "40", "13", "40","hard");
        this.addQuestion(q141);
        Question q142 = new Question("30 x 2 = ?", "24", "33", "60", "42", "60","hard");
        this.addQuestion(q142);
        Question q143 = new Question("32 x 1 = ?", "42", "32", "22", "21", "32","hard");
        this.addQuestion(q143);
        Question q144 = new Question("35 x 2 = ?", "26", "70", "35", "18", "70","hard");
        this.addQuestion(q144);
        Question q145 = new Question("50 x 2 = ?", "10", "50", "25", "100", "100","hard");
        this.addQuestion(q145);
        Question q146 = new Question("70 - 40 = ?", "35", "41", "21", "30", "30","hard");
        this.addQuestion(q146);
        Question q147 = new Question("100 - 50 = ?", "50", "40", "10", "20", "50","hard");
        this.addQuestion(q147);
        Question q148 = new Question("98 + 10 + 2 = ?", "110", "18", "99", "111", "110","hard");
        this.addQuestion(q148);
        Question q149 = new Question("20 - 5 + 3 = ?", "18", "14", "21", "13", "18","hard");
        this.addQuestion(q149);
        Question q150 = new Question("12 - 4 = ?", "5", "8", "6", "9", "8","hard");
        this.addQuestion(q150);
        //end of hard question

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Table_Quest);
        db.execSQL("DROP TABLE IF EXISTS " + Table_Score);
        // Create tables again

        onCreate(db);
    }

    public void delete(String names) {

        dbase= this.getWritableDatabase(); //getWritableDatabase artinya hanya bisa menulis
        // parameter ?, untuk concatenate string
        dbase.delete(Table_Score, name + "= ?",
                new String[] { names });
        dbase.close(); // menutup koneksi database

    }
    // Adding new question
    public void addQuestion(Question quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
        values.put(KEY_LEVEL, quest.getLEVEL());
        // Inserting Row
        dbase.insert(Table_Quest, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Table_Quest + " WHERE level = 'easy'";
        //+" WHERE qid IN (SELECT qid FROM quest ORDER BY RANDOM() lIMIT 1)"
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    public List<Question> getQuestionMedium()
    {
        List<Question> questlist = new ArrayList<Question>();
        String query = "SELECT * FROM " + Table_Quest + " WHERE level = 'medium'";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(query,null);
        if (cursor.moveToFirst())
        {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                questlist.add(quest);
            } while (cursor.moveToNext());
        }
        return questlist;
    }

    public List<Question> getQuestionHard()
    {
        List<Question> questlist = new ArrayList<Question>();
        String query = "SELECT * FROM " + Table_Quest + " WHERE level = 'hard'";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(query,null);
        if (cursor.moveToFirst())
        {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                questlist.add(quest);
            } while (cursor.moveToNext());
        }
        return questlist;
    }


    public Long insertscore(Score score)
    {
        SQLiteDatabase dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put(id, score.getId()); //remove id if wanna unique code
        values.put(name, score.getName());
        values.put(level, score.getLevel());
        values.put(highscore, score.getScore());
        // Inserting Row
        Long quest =  dbase.insert(Table_Score, null, values);
        dbase.close();
        return quest;
    }

    public ArrayList<HashMap<String, String>> getHighScore(){

        dbase = this.getReadableDatabase();
        String sql = "SELECT " + name + ","
                + highscore + " FROM " + Table_Score + " WHERE " + level +  "= 'Easy' ORDER BY " + highscore + " DESC ";
        ArrayList<HashMap<String, String>> highScoreList =
                new ArrayList<>();
        Cursor cursor = dbase.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> dataMahasiswa =
                        new HashMap<>();
                dataMahasiswa.put(EmathHelper.name,
                        cursor.getString(cursor.getColumnIndex(EmathHelper.name)));
                dataMahasiswa.put(EmathHelper.highscore,
                        cursor.getString(cursor.getColumnIndex(EmathHelper.highscore)));
                highScoreList.add(dataMahasiswa);
            } while (cursor.moveToNext());

        }
        cursor.close();
        dbase.close();
        return highScoreList;
    }

    public ArrayList<HashMap<String, String>> getHighScoreMedium(){

        dbase = this.getReadableDatabase();
        String sql = "SELECT " + name + ","
                + highscore + " FROM " + Table_Score + " WHERE " + level +  "= 'Medium' ORDER BY " + highscore + " DESC ";
        ArrayList<HashMap<String, String>> highScoreList =
                new ArrayList<>();
        Cursor cursor = dbase.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> dataMahasiswa =
                        new HashMap<>();
                dataMahasiswa.put(EmathHelper.name,
                        cursor.getString(cursor.getColumnIndex(EmathHelper.name)));
                dataMahasiswa.put(EmathHelper.highscore,
                        cursor.getString(cursor.getColumnIndex(EmathHelper.highscore)));
                highScoreList.add(dataMahasiswa);
            } while (cursor.moveToNext());

        }
        cursor.close();
        dbase.close();
        return highScoreList;
    }

    public ArrayList<HashMap<String, String>> getHighScoreHard(){

        dbase = this.getReadableDatabase();
        String sql = "SELECT " + name + ","
                + highscore + " FROM " + Table_Score + " WHERE " + level +  "= 'Hard' ORDER BY " + highscore + " DESC ";
        ArrayList<HashMap<String, String>> highScoreList =
                new ArrayList<>();
        Cursor cursor = dbase.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> dataMahasiswa =
                        new HashMap<>();
                dataMahasiswa.put(EmathHelper.name,
                        cursor.getString(cursor.getColumnIndex(EmathHelper.name)));
                dataMahasiswa.put(EmathHelper.highscore,
                        cursor.getString(cursor.getColumnIndex(EmathHelper.highscore)));
                highScoreList.add(dataMahasiswa);
            } while (cursor.moveToNext());

        }
        cursor.close();
        dbase.close();
        return highScoreList;
    }
}
