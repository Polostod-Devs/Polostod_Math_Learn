package com.polostod.math.Facebook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.polostod.math.R;
import com.facebook.appevents.AppEventsLogger;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

/**
 * Created by pratma.net on 8/13/2016.
 */
public class profil extends FragmentActivity {

    private int currentV;
    TextView id,name,email;
    ImageView imgProfile;
    JSONObject response,profile_pic_data,profile_pic_url;
    Button close;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_profile);
        currentV = Build.VERSION.SDK_INT;
        close = (Button) findViewById(R.id.close_button_profil);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        id = (TextView) findViewById(R.id.id);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        imgProfile = (ImageView) findViewById(R.id.img_profile);
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
        String data = shared.getString("object","");

        if (!data.equals(""))
        {
            setUserProfile(data);
        }else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(R.string.facebookvalidate)
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog dialog = alert.create();
            dialog.show();
        }

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
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }



    public  void  setUserProfile(String jsondata){

        try {
            response = new JSONObject(jsondata);
            id.setText(getString(R.string.getIdJson) +response.get("id").toString());
            name.setText(getString(R.string.getNameJson)+response.get("name").toString());
            email.setText(getString(R.string.getEmailJson)+response.get("email").toString());
            id.setTextColor(Color.BLACK);
            name.setTextColor(Color.BLACK);
            email.setTextColor(Color.BLACK);
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));

            Picasso.with(this).load(profile_pic_url.getString("url"))
                    .into(imgProfile);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
