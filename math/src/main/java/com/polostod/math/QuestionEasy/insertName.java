package com.polostod.math.QuestionEasy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.polostod.math.R;
import com.polostod.math.score_result;

import org.json.JSONObject;

public class insertName extends FragmentActivity implements View.OnClickListener {

    private int currentV,scored;
    EditText input;
    Button send;
    JSONObject response;

    @Override
    public void onBackPressed() {
    }

    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        super.setFinishOnTouchOutside(false);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_name);
        this.setFinishOnTouchOutside(true);
        input = (EditText) findViewById(R.id.editTextDialogUserInput);
        send = (Button) findViewById(R.id.sendName);
        send.setOnClickListener(this);
        TextView ket = (TextView) findViewById(R.id.txt_ket);
        SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(this);
        String user = share.getString("object","");
        if (!user.equals(""))
        {
            setUserProfile(user);
        }
        Bundle b = getIntent().getExtras();
        scored = b.getInt("score");
        String time = b.getString("time");
        ket.setText(time);
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

    @Override
    public void onClick(View v) {
        if (v == send) {
                String inputName = input.getText().toString();
                if (!TextUtils.isEmpty(inputName)) {
                    Intent intent = new Intent(insertName.this, score_result.class);
                    Bundle b = new Bundle();
                    b.putString("name", inputName);
                    b.putInt("score", scored);
                    intent.putExtras(b);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Nama harap diisi!!!", Toast.LENGTH_SHORT).show();
                    return;
            }
        }
    }

    public  void  setUserProfile(String jsondata){

        try {
            response = new JSONObject(jsondata);
            input.setText(response.get("name").toString());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

