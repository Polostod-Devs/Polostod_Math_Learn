package com.polostod.math.HighScore;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;

import com.polostod.math.R;

/**
 * Created by polostod-dev on 28/04/16.
 */
public class highscoreDialog extends android.support.v4.app.DialogFragment {

    private SectionPagerAdapter mTabhost;
    private ViewPager viewPager;
    private int currentV;
    private FragmentTabHost tabHost;
    Button close;
    Dialog dialog;

    @SuppressLint("NewApi")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        currentV = Build.VERSION.SDK_INT;

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentV >= Build.VERSION_CODES.KITKAT)
        {
            dialog.getWindow().getDecorView().setSystemUiVisibility(flags);
        }

        final View decorView = dialog.getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });
        return dialog;
    }



    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.highscore_dialog,container);
        tabHost = (FragmentTabHost) view.findViewById(R.id.tabs);
        close = (Button) view.findViewById(R.id.close_button_score);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tabHost.setup(getActivity(), getChildFragmentManager());
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Easy"), Fragment.class,null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Medium"), Fragment.class,null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Hard"), Fragment.class,null);
        mTabhost = new SectionPagerAdapter(getChildFragmentManager());
        viewPager =(ViewPager)view.findViewById(R.id.pager);
        viewPager.setAdapter(mTabhost);
        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = (int) (50 * getResources().getDisplayMetrics().density);
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height = (int) (50 * getResources().getDisplayMetrics().density);
        tabHost.getTabWidget().getChildAt(2).getLayoutParams().height = (int) (50 * getResources().getDisplayMetrics().density);
        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.color.white);
        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.color.white);
        tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.color.white);
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++)
        {
            tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.white);
        }


        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.color.black_overlay);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++)
                {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.white);
//                    tabHost.getTabWidget().getChildAt(i).getLayoutParams().height = (int) (80 * getResources().getDisplayMetrics().density);
                }
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.color.black_overlay);
                int i = tabHost.getCurrentTab();
                viewPager.setCurrentItem(i);
            }
        });

    return view;
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter
    {

        public SectionPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }



        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if (position == 0)
            {
                easyFrag easyFrag = new easyFrag();
                return easyFrag;
            }
            if (position == 1)
            {
                mediumFrag mediumFrag = new mediumFrag();
                return mediumFrag;
            }
            if (position == 2)
            {
                hardFrag hardFrag = new hardFrag();
                return hardFrag;
            }
            return null;
        }

        @Override
        public int getCount() {

            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return getString(R.string.leveleasy);
                case 1:
                    return getString(R.string.levelmedium);
                case 2:
                    return getString(R.string.levelhard);
            }
            return null;
        }

    }



}
