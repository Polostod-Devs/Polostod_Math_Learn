package com.polostod.math.QuestionMedium;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.polostod.math.QuestionEasy.EmathHelper;
import com.polostod.math.QuestionEasy.Question;
import com.polostod.math.QuestionEasy.insertName;
import com.polostod.math.R;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by pratma.net on 8/17/2016.
 */
public class Medium extends Activity implements View.OnClickListener {

    List<Question> questList;
    int score = 0;
    int resut = 0;
    int availableQuestion;
    int qid = 0;
    int heart = 5;
    CounterClass timer;
    private int currentV;
    Question currentQ;
    public Button a1,a2,a3,a4;
    public TextView scored, times, question;
    public static final String level = "Medium";
    public EmathHelper db;
    Random random = new Random();
    MediaPlayer mPlayer, mPlayer1;
    TextView hearts;

    public int getResut() {
        return resut;
    }

    public void setResut(int resut) {
        this.resut = resut * 10;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);
        SavedPreferences("level", level);
        db = new EmathHelper(this);  // my question bank class
        questList = db.getQuestionMedium();  // this will fetch all quetonall questions
        currentQ = pickQuestion(); // the current question
        a1 = (Button) findViewById(R.id.btn5);
        a2 = (Button) findViewById(R.id.btn3);
        a3 = (Button) findViewById(R.id.btn4);
        a4 = (Button) findViewById(R.id.btn6);
        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
        a4.setOnClickListener(this);
        scored = (TextView) findViewById(R.id.score);
        question = (TextView) findViewById(R.id.quest);
        hearts = (TextView) findViewById(R.id.heart);
        hearts.setText(""+heart);
        times = (TextView) findViewById(R.id.waktu);
        // method which will set the things up for our game
        setQuestionView();
        times.setText(R.string.time);
        //when times is up app finish the game

        // A timer of 60 seconds to play for, with an interval of 1 second (1000 milliseconds)
        timer = new CounterClass(120000, 1000);
        timer.start();
        currentV = Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentV >= Build.VERSION_CODES.KITKAT)
        {
            getWindow().getDecorView().setSystemUiVisibility(flags);
        }

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onFinish() {
            times.setText(R.string.timeUp);
            /*if (times.getText().equals("Time is up"))
            {*/
            Intent intent = new Intent(Medium.this,
                    insertName.class);
            // passing the int value
            Bundle b = new Bundle();
            setResut(score);
            b.putInt("score", getResut());
            b.putString("time",times.getText().toString());// Your score
            intent.putExtras(b); // Put your score to your next
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            db.close();
            mPlayer1.stop();
//            finish();

        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            long millis = millisUntilFinished;
            @SuppressLint("DefaultLocale") String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            times.setText(hms);
            SavedPreferences("currentTime", hms);
        }
    }

    public void getAnswer(String AnswerString) {
        if (currentQ.getANSWER().equals(AnswerString)) {
            score++;
            setResut(score);
            scored.setText("Score : " + getResut());
            mPlayer = MediaPlayer.create(this,R.raw.correct);
            mPlayer.setVolume(100,100);
            mPlayer.start();
        } else {
            // if unlucky start activity and fin ish the game
            heart--;
            hearts.setText(""+heart);
            mPlayer = MediaPlayer.create(this, R.raw.wrong);
            mPlayer.setVolume(100,100);
            mPlayer.start();
            if (heart == 0) {
                Intent intent = new Intent(Medium.this,
                        insertName.class);
                // passing the int value
                Bundle b = new Bundle();
                setResut(score);
                b.putInt("score", getResut());
                b.putString("time", getString(R.string.heartValidate));// Your score
                intent.putExtras(b); // Put your score to your next
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//            finish();
                timer.cancel();
            }
        }

        if (qid != 0) {
            currentQ = pickQuestion();
            setQuestionView();
        }
    }

    public void SavedPreferences(String key, String name) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(key, name);
        edit.commit();
    }

    @SuppressLint("NewApi")
    private void setQuestionView() {
        // the method which will put all things together

        question.setText(currentQ.getQUESTION());
        a1.setText(currentQ.getOPTA());
        a2.setText(currentQ.getOPTB());
        a3.setText(currentQ.getOPTC());
        a4.setText(currentQ.getOPTD());
        ++qid;

    }

    private Question pickQuestion() {
        if (availableQuestion == 0) { // Takes care of initialisation too.
            availableQuestion = questList.size();
        }
        int qid = random.nextInt(availableQuestion);
        Question q = questList.get(qid);

        // Switch question with the last one, and have one less available:
        --availableQuestion;
        questList.add(qid, questList.get(availableQuestion));
        questList.add(availableQuestion, q);
        return q;
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String preference = pref.getString("currentTime","");
        times.setText(preference);
        timer.start();
        mPlayer1.setVolume(70,70);
        mPlayer1.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPlayer1 = MediaPlayer.create(this,R.raw.onplay);
        mPlayer1.setVolume(70,70);
        mPlayer1.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        if (mPlayer1 != null) {
            mPlayer1.stop();
            mPlayer1.release();
            mPlayer1 = null;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn5:
                getAnswer(a1.getText().toString());
                break;
            case R.id.btn3:
                getAnswer(a2.getText().toString());
                break;
            case R.id.btn4:
                getAnswer(a3.getText().toString());
                break;
            case R.id.btn6:
                getAnswer(a4.getText().toString());
        }
    }
}
