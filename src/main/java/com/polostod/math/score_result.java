package com.polostod.math;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.polostod.math.HighScore.Score;
import com.polostod.math.QuestionEasy.Easy;
import com.polostod.math.QuestionEasy.EmathHelper;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class score_result extends Activity implements View.OnClickListener{

    EmathHelper em;
    ImageView home,refresh,resImg,test;
    int currentV;
    ShareDialog shareDialog;
    Bitmap mbitmap;
    ImageButton share;
    CallbackManager callbackManager;

    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        super.setFinishOnTouchOutside(false);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(score_result.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.score_result);
        this.setFinishOnTouchOutside(true);
        home = (ImageView) findViewById(R.id.kembali);
        refresh = (ImageView) findViewById(R.id.refresh);
        resImg = (ImageView) findViewById(R.id.imgResult);
        share = (ImageButton) findViewById(R.id.share);
        shareDialog = new ShareDialog(score_result.this);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                screenShot(v);
            }
        });
        home.setOnClickListener(this);
        refresh.setOnClickListener(this);
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
        TextView score =(TextView) findViewById(R.id.result);
        TextView level = (TextView) findViewById(R.id.level);
        TextView smiley = (TextView) findViewById(R.id.smiley);
        em = new EmathHelper(this);
        Score score1 = new Score();

        Bundle b = getIntent().getExtras();
        int scored = b.getInt("score");
        String name = b.getString("name");
        score.setText(" " + scored + " ");

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String preference = pref.getString("level","");
        level.setText(preference);

        score1.setName(name);
        score1.setLevel(preference);
        score1.setScore(scored);
        em.insertscore(score1);
        em.close();

        if (scored >= 0 && scored <= 50)
        {
            smiley.setText(R.string.smiley1);
        }else if (scored >= 50 && scored <= 100)
        {
            smiley.setText(R.string.smiley2);
        }else if (scored >= 100 && scored <= 150)
        {
            smiley.setText(R.string.smiley3);
        }else if (scored >= 150 && scored <= 200)
        {
            smiley.setText(R.string.smiley4);
        }else if (scored >= 200)
        {
            smiley.setText(R.string.smiley5);
        }
        if (scored >= 100)
        {
            resImg.setImageResource(R.drawable.great);
        }else{
            resImg.setImageResource(R.drawable.bad);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    protected void facebookSDKInitialize() {

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
    }
    @Override
    public void onClick(View v) {
        if (v == home){
            Intent intent = new Intent(score_result.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }else if (v == refresh) {
            Intent intent = new Intent(score_result.this, Easy.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    public void screenShot(View view) {
        mbitmap = getBitmapOFRootView(share);
        ShareDialog(mbitmap);
        Toast.makeText(score_result.this, R.string.sharedScore,Toast.LENGTH_LONG).show();
    }

    public Bitmap getBitmapOFRootView(View v) {
        View rootview = v.getRootView();
        rootview.setDrawingCacheEnabled(true);
        Bitmap bitmap1 = rootview.getDrawingCache();
        createImage(bitmap1);
        return bitmap1;
    }

    public void createImage(Bitmap bmp) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File file = new File(Environment.getExternalStorageDirectory() +
                "/capturedscreenandroid.jpg");
        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes.toByteArray());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ShareDialog(Bitmap imagePath){

        if (ShareDialog.canShow(SharePhotoContent.class)) {
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(imagePath)
//                    .setCaption(getString(R.string.captionShareScore))
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
//                    .setContentUrl(Uri.parse("https://polostod-developers.com"))
                    .build();

            shareDialog.show(content);
        }
    }
}