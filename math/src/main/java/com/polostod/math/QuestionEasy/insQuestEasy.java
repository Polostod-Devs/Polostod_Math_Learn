package com.polostod.math.QuestionEasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pratma.net on 7/23/2016.
 */
public class insQuestEasy {
    private EmathHelper emathHelper;

    public insQuestEasy(Context context){
        emathHelper = new EmathHelper(context);
    }

    public Long insertQuestion(Question question)
    {
        SQLiteDatabase dbase = emathHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmathHelper.KEY_QUES, question.getQUESTION());
        values.put(EmathHelper.KEY_ANSWER, question.getANSWER());
        values.put(EmathHelper.KEY_OPTA, question.getOPTA());
        values.put(EmathHelper.KEY_OPTB, question.getOPTB());
        values.put(EmathHelper.KEY_OPTC, question.getOPTC());
        values.put(EmathHelper.KEY_OPTD, question.getOPTD());
        // Inserting Row
        Long quest =  dbase.insert(EmathHelper.Table_Quest, null, values);
        dbase.close();
        return quest;
    }
}
