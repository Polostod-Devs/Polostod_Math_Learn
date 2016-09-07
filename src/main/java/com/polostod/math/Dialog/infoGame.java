package com.polostod.math.Dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.polostod.math.R;

/**
 * Created by pratama.net on 20/01/2016.
 */
public class infoGame extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
//    public VerticalMarqueeTextView mTextView;
    private TranslateAnimation textViewAnimation;
    private int currentV;
    public infoGame (Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.info_math);
//        mTextView =(VerticalMarqueeTextView) findViewById(R.id.txt_authorize);

        TextView scrol = (TextView) findViewById(R.id.txt_authorize);
        scrol.setMovementMethod(new ScrollingMovementMethod());
        if(scrol != null){
            final Layout layout = scrol.getLayout();
            if(layout != null){
                int scrollDelta = layout.getLineBottom(scrol.getLineCount() - 1)
                        - scrol.getScrollY() - scrol.getHeight();
                if(scrollDelta > 0)
                    scrol.scrollBy(0, scrollDelta);
            }
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

    }
/*

    private void animateTextView() {
        int textWidth = getTextViewWidth(mTextView);
        int displayWidth = getDisplayWidth(getContext());

        if((displayWidth)<=textWidth) {
            textViewAnimation = new TranslateAnimation(
                    0f, -0f,
                    20f , -100f);
            textViewAnimation.setDuration(10000); // Set custom duration.
            textViewAnimation.setStartOffset(0);// Set custom offset.
            textViewAnimation.setRepeatMode(Animation.RESTART); // This will animate text back ater it reaches end.
            textViewAnimation.setRepeatCount(Animation.INFINITE); // Infinite animation.

            mTextView.startAnimation(textViewAnimation);
            mTextView.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    private int getDisplayWidth(Context context) {
        int displayWidth;

        WindowManager windowManager = (WindowManager)context.getSystemService(
                Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point screenSize = new Point();

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(screenSize);
            displayWidth = screenSize.x;
        } else {
            displayWidth = display.getWidth();
        }

        return displayWidth;
    }

    private int getTextViewWidth(TextView textView) {
        textView.measure(0, 100);    // Need to set measure to (0, 0).
        return textView.getMeasuredWidth();
    }
*/

    @Override
    public void onClick(View v) {

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

}

