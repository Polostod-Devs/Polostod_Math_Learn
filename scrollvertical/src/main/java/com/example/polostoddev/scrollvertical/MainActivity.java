package com.example.polostoddev.scrollvertical;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

public class MainActivity extends AppCompatActivity {
    private VerticalMarqueeTextView VMTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate the VMTV.
        VMTV = (VerticalMarqueeTextView) findViewById(R.id.vmTextView);

        // Set the VMTV movement method so that it can scroll.
        VMTV.setMovementMethod(new ScrollingMovementMethod());

        // Alternately you may also pass in the (long) duration between scroll
        // moves and the (int) pixelYOffSet amount that you wish to scroll by.
        // Using the following method calls:
        // VMTV.setDuration(duration);
        // VMTV.setPixelYOffSet(pixelYOffSet);

        // Optionally pause the marquee to wait for the Activity to start.
        VMTV.pauseMarquee();

    }

    @Override
    protected void onResume() {

        // Start or restart the Marquee if paused.
        if (VMTV.isPaused()) {
            VMTV.resumeMarquee();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {

        // Pause the Marquee when the Activity pauses.
        VMTV.pauseMarquee();
        super.onPause();
    }

    @Override
    protected void onDestroy() {

        VMTV.stopMarquee();
        super.onDestroy();
    }
}
