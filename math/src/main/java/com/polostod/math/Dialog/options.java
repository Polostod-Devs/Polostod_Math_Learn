package com.polostod.math.Dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.polostod.math.Facebook.profil;
import com.polostod.math.HighScore.highscoreDialog;
import com.polostod.math.R;
import com.polostod.math.internetConnection.connection;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONObject;

/**
 * Created by polostod-dev on 19/04/16.
 */
public class options extends FragmentActivity implements
        SeekBar.OnSeekBarChangeListener {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private int currentV;
    ShareDialog shareDialog;
    String objects;
    JSONObject response;
    SharedPreferences pref;
    Context context;
    Button profile,close;
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;
    ImageView sound;
    int proggressChange;

    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        super.setFinishOnTouchOutside(false);
    }

    Button shareButton,score;
    connection cd;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.options);
//        initControls();
        this.setFinishOnTouchOutside(true);
        sound = (ImageView) findViewById(R.id.soundImg);
        volumeSeekbar = (SeekBar)findViewById(R.id.volume);
        profile = (Button) findViewById(R.id.Profile);
        close = (Button) findViewById(R.id.close_button_opt);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(options.this,profil.class);
                startActivity(i);
                isFacebookLogged();
            }
        });

        //proggress change image
        /*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        proggressChange = prefs.getInt("seekBarValue", 0);*/
        volumeSeekbar.setProgress(proggressChange);
        if (proggressChange >= 0 && proggressChange <= 5)
        {
            sound.setImageResource(R.drawable.sound_min);

        }else if (proggressChange >=5 && proggressChange <= 10 )
        {
            sound.setImageResource(R.drawable.sound_half);
        }else if(proggressChange <= 15)
        {
            sound.setImageResource(R.drawable.sound_max);
        }

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volumeSeekbar.setMax(audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeSeekbar.setProgress(audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC));


        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onStopTrackingTouch(SeekBar arg0)
            {
                proggressChange = arg0.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0)
            {
                proggressChange = arg0.getProgress();
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
            {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        progress, 0);
                proggressChange = progress;
                if (progress >= 0 && progress <= 5)
                {
                    sound.setImageResource(R.drawable.sound_min);
                }else if (progress >=5 && progress <= 10 )
                {
                    sound.setImageResource(R.drawable.sound_half);
                }else if(progress <= 15)
                {
                    sound.setImageResource(R.drawable.sound_max);
                }
            }
        });
        context = getApplicationContext();
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        objects = pref.getString("object","");
//        setUserProfile(objects);
        Log.d("ini object", objects);
        if (!isFacebookLogged())
        {
            pref = PreferenceManager.getDefaultSharedPreferences(this);
            pref.edit().remove("object").commit();
        }

        /*if (isLogout())
        {
            finish();
        }*/
        cd = new connection(getApplicationContext());

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

        loginButton = (LoginButton) findViewById(R.id.login);
        loginButton.setReadPermissions("email,publish_actions");
        getLoginDetails(loginButton);
        score =(Button)findViewById(R.id.highscore);
        shareButton = (Button) findViewById(R.id.share);
        shareDialog = new ShareDialog(this);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)){
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Math Learn")
                            .setImageUrl(Uri.parse("https://www.polostod-developers.com/wp-content/uploads/2016/08/wallpeper-sicha.png"))
                            .setContentDescription("Aplikasi Math Learn ini dikembangkan dengan bantuan tim Polostod Developers ini ditujukan kepada anak - anak \\n untuk sarana belajar dasar menghitung sambil bermain. \\n\n" +
                                    "    sangat diharapkan untuk megirimkan kritik dan saran")
                            .setContentUrl(Uri.parse("https://www.polostod-developers.com/produk/mathlearn/"))
                            .build();
                    shareDialog.show(linkContent);


                }

            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                highscoreDialog dialog = new highscoreDialog();
                dialog.show(fm,"HighScoreDialog");
            }
        });

    }

    protected void facebookSDKInitialize(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }


    protected void getLoginDetails(LoginButton loginButton)
    {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                getUserInfo(loginResult);
                finish();
                Toast.makeText(options.this,"connected to facebook", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(options.this,"Facebook is canceled", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(options.this,"please Check your Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    protected void getUserInfo(LoginResult loginResult)
    {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        /*Intent intent = new Intent(options.this,profil.class);
                        intent.putExtra("jsondata",object.toString());

                        startActivity(intent);*/
//                        if (isFacebookLogged()) {
                            pref = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor edit = pref.edit();
                            edit.putString("object", object.toString());
                            edit.commit();

                    }
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        Log.e("data",data.toString());
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
        AppEventsLogger.activateApp(this);
        Profile profile = Profile.getCurrentProfile();
        if (profile == null)
        {
            pref = PreferenceManager.getDefaultSharedPreferences(this);
            pref.edit().remove("object").commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    public  void  setUserProfile(String jsondata){

        try {
            response = new JSONObject(jsondata);
//            music.setText(response.get("name").toString());
//            user_name.setText(response.get("name").toString());
//            profile_pic_data = new JSONObject(response.get("picture").toString());
//            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));

           /* Picasso.with(this).load(profile_pic_url.getString("url"))
                    .into(user_picture);*/

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isFacebookLogged()
    {
        return AccessToken.getCurrentAccessToken() != null && Profile.getCurrentProfile() != null;
    }

    public boolean isLogout()
    {
        LoginManager.getInstance().logOut();
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                progress, 0);
        if (progress >= 0 && progress <= 5)
        {
            sound.setImageResource(R.drawable.sound_min);
        }else if (progress >=5 && progress <= 10 )
        {
            sound.setImageResource(R.drawable.sound_half);
        }else if(progress <= 15)
        {
            sound.setImageResource(R.drawable.sound_max);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        proggressChange = seekBar.getProgress();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        proggressChange = seekBar.getProgress();
    }

}
