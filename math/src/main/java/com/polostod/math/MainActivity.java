package com.polostod.math;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.polostod.math.Dialog.closedialog;
import com.polostod.math.Dialog.infoGame;
import com.polostod.math.Dialog.options;
import com.polostod.math.QuestionEasy.Easy;
import com.polostod.math.QuestionHard.Hard;
import com.polostod.math.QuestionMedium.Medium;

import org.json.JSONObject;

import java.util.Random;


public class MainActivity extends Activity implements View.OnClickListener {
    Button Play,easy,medium,hard,exit,info;
    LinearLayout liner;
    private int currentV;
    MediaPlayer mp = null;
    SharedPreferences sp;
    private int currentTrack = 0;
    Random rand = new Random();
    JSONObject response;
    TextView userT;
    String user;

    /*Music has been fixed*/
    @Override
    protected void onStart() {
        super.onStart();
        playMusic();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(this);
        user = share.getString("object","");
        userT = (TextView) findViewById(R.id.txt_user);
        if (!user.equals("")) {
            setUserProfile(user);
            userT.setVisibility(View.VISIBLE);
        }else{
            userT.setVisibility(View.INVISIBLE);
        }
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
        ActionBar a = getActionBar();
        if(a != null) a.hide();
        Play = (Button) findViewById(R.id.btn_play);
        liner =(LinearLayout) findViewById(R.id.v_liner);
        easy = (Button) findViewById(R.id.btn_easy);
        medium = (Button) findViewById(R.id.btn_medium);
        hard = (Button) findViewById(R.id.btn_hard);
        exit = (Button)findViewById(R.id.button_exit);
        info = (Button) findViewById(R.id.button_author);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        info.setOnClickListener(this);
        exit.setOnClickListener(this);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Easy.class);
                startActivity(intent);
                stopMusic();
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Medium.class);
                startActivity(intent);
                stopMusic();
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Hard.class);
                startActivity(intent);
                stopMusic();
            }
        });
        Play.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
            closedialog cdd = new closedialog(MainActivity.this);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(currentV >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
//        mp.start();
        if (!user.equals("")) {
            setUserProfile(user);
            userT.setVisibility(View.VISIBLE);
        }else{
            userT.setVisibility(View.INVISIBLE);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMusic();
    }

    public void stopMusic()
    {
        if (mp != null){
                mp.stop();
                mp.release();
                mp = null;

        }
    }

        // Added auto rotation Fixed
     public static void setAutoOrientationEnabled(Context context, boolean enabled)
     {
         Settings.System.putInt( context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, enabled ? 1 : 0);
     }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_play:
                if(liner.getVisibility() == View.VISIBLE){
                    liner.setVisibility(View.INVISIBLE);
                }else {
                    liner.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.button_exit:
                closedialog cdd = new closedialog(MainActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
                break;
            case R.id.button_author:
                infoGame info = new infoGame(MainActivity.this);
                info.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                info.show();
                break;
            case R.id.button:
                Intent i = new Intent(MainActivity.this,options.class);
                startActivity(i);
                break;

        }
    }

    public void playMusic()
    {
        mp = new MediaPlayer();
//        final int nextMusic = rand.nextInt(rawFiles.length);
//        currentTrack = (currentTrack + 1) % rawFiles.length;
        mp = MediaPlayer.create(this,R.raw.happy);
       /*  mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

           @Override
            public void onCompletion(MediaPlayer arg0) {

                arg0.release();
                    arg0 = MediaPlayer.create(getApplicationContext(),R.raw.happy);
                    arg0.setOnCompletionListener(this);
                    arg0.start();


            }});*/
        mp.start();

    }

    public  void  setUserProfile(String jsondata){

        try {
            response = new JSONObject(jsondata);
            if (!response.equals(""))
            {
                userT.setText(response.get("name").toString());
                userT.setVisibility(View.VISIBLE);
            }else{
                userT.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
