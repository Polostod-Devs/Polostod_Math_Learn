/*
package com.example.polostoddev.math;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

*/
/**
 * Created by polostod-dev on 21/04/16.
 *//*

public class music extends Service implements MediaPlayer.OnPreparedListener,
MediaPlayer.OnCompletionListener,MediaPlayer.OnErrorListener{


    private static final String LogCat = null; //logging status
    MediaPlayer mPlayer;
    boolean mActivityResumed;
    boolean mPrepraed;
    int mPosition;


    private final IBinder musicBind = new MusicBnder();
    private int songPstn;
    public Random random = new Random();


    @Override
    public void onCreate() {
        super.onCreate();
        songPstn=0;
        mPlayer = new MediaPlayer();
        initMusic();
    }

    public void initMusic()
    {
        mPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mPlayer.stop();
        mPlayer.release();
        return false;
    }

    public void playSong()
    {
        mPlayer.reset();
        mPlayer = MediaPlayer.create(this, rawFiles[random.nextInt(rawFiles.length)]);
        mPlayer.prepareAsync();
    }

    public class MusicBnder extends Binder
    {
        music getService()
        {
            return music.this;
        }
    }



    @Override
    public void onCompletion(MediaPlayer mp) {

        if (mPlayer.getCurrentPosition() > 0)
        {
            mPlayer.reset();
            playNext();
        }
    }

    public void playNext()
    {
        if(mPlayer.isLooping())
        {
            int i = rawFiles.length;
            while (i > mPlayer.getCurrentPosition()) {
                mPlayer = MediaPlayer.create(this, rawFiles[random.nextInt(i)]);
            }
            i++;
        }

        playSong();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.v("MUSIC", "EROOR");
        mPlayer.reset();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        mPlayer.start();

    }

    @Override
    public void onDestroy() {
        stopForeground(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }
}
*/
