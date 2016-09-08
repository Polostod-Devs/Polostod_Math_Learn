package com.polostod.math.QuestionEasy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;
import com.polostod.math.R;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Easy extends Activity {

    List<Question> quesList;
    int score = 0;
    int resut = 0;
    int heart = 5;
    int availableQuestions; // Questions 0 till (askable - 1) can be asked
    Random random = new Random();
    MediaPlayer mplayer,mplayer1;
    TextView hearts;

    public int getResut() {
        return resut;
    }

    public void setResut(int resut) {
        this.resut = resut * 10;
    }

    int qid = 0;
    private int currentV;
    Question currentQ;
    public ImageView v1, v2, v3, papan;
    public Button a1, a2, a3, a4;
    CounterClass timer;
    public TextView scored, times, question;
    public static final String level = "Easy";
//    public static final String name = "Aditya";
    public EmathHelper db;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String preference = pref.getString("currentTime","");
        times.setText(preference);
        timer.start();
        mplayer1.setVolume(80,80);
        mplayer1.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        if (mplayer1 != null) {
            mplayer1.stop();
            mplayer1.release();
            mplayer1 = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        SavedPreferences("level", level);
        db = new EmathHelper(this);  // my question bank class
        quesList = db.getAllQuestions();  // this will fetch all quetonall questions
        hearts = (TextView) findViewById(R.id.heart);
        hearts.setText("" + heart);
        currentQ = pickQuestion(); // the current question
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
        a1 = (Button) findViewById(R.id.btn5);
        a2 = (Button) findViewById(R.id.btn3);
        a3 = (Button) findViewById(R.id.btn4);
        a4 = (Button) findViewById(R.id.btn6);
        scored = (TextView) findViewById(R.id.score);
        question = (TextView) findViewById(R.id.quest);
        // the timer
        times = (TextView) findViewById(R.id.waktu);
        // method which will set the things up for our game
        setQuestionView();
        times.setText(R.string.time);
        //when times is up app finish the game

        // A timer of 60 seconds to play for, with an interval of 1 second (1000 milliseconds)
        timer = new CounterClass(120000, 1000);
        timer.start();


        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(a1.getText().toString());
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(a2.getText().toString());
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(a3.getText().toString());
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(a4.getText().toString());
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mplayer1 = MediaPlayer.create(this,R.raw.onplay);
        mplayer1.setVolume(80,80);
            mplayer1.start();

    }

    @Override
    public void onStop() {
        super.onStop();

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
            Intent intent = new Intent(Easy.this,
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
            mplayer1.stop();
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
            // if conditions matches increase the int (score) by 1
            // and set the text of the score view
            score++;
            setResut(score);
            scored.setText("Score : " + getResut());
            mplayer = MediaPlayer.create(this,R.raw.correct);
            mplayer.setVolume(100,100);
            mplayer.start();
        } else {
            // if unlucky start activity and fin ish the game
            heart--;
            hearts.setText(""+heart);
            mplayer = MediaPlayer.create(this, R.raw.wrong);
            mplayer.setVolume(100, 100);
            mplayer.start();
            if (heart == 0) {
                Intent intent = new Intent(Easy.this,
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
            // if questions are not over then do this
            currentQ = pickQuestion();
            setQuestionView();
        } /*else {
            // if over do this
            Intent intent = new Intent(Easy.this,
                    insertName.class);
            Bundle b = new Bundle();
            setResut(score);
            b.putInt("score", getResut()); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();

        }*/
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
        if (availableQuestions == 0) { // Takes care of initialisation too.
            availableQuestions = quesList.size();
        }
        int qid = random.nextInt(availableQuestions);
        Question q = quesList.get(qid);

        // Switch question with the last one, and have one less available:
        --availableQuestions;
        quesList.add(qid, quesList.get(availableQuestions));
        quesList.add(availableQuestions, q);
        return q;
    }




    @Override
    public void onBackPressed() {
    }

//Try to fix button

}

