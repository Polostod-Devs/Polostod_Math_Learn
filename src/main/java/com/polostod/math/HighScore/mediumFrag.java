package com.polostod.math.HighScore;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.polostod.math.Adapter.AppAdapter;
import com.polostod.math.QuestionEasy.EmathHelper;
import com.polostod.math.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by polostod-dev on 28/04/16.
 */
public class mediumFrag extends android.support.v4.app.Fragment {

    ArrayList<HashMap<String,String>> data;
    View rootView;
    TextView val;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.highscore_medium, container, false);
        readData();
        return rootView;
    }

    public void readData(){
        EmathHelper emathHelper = new EmathHelper(getActivity());
        val = (TextView) rootView.findViewById(R.id.validateMedium);
        ArrayList<HashMap<String, String>> studentList = emathHelper.getHighScoreMedium();
        if(studentList.size()!=0) {
            data = studentList;
            val.setVisibility(View.INVISIBLE);
        }else{
            data = new ArrayList<HashMap<String, String>>();
            val.setVisibility(View.VISIBLE);
            val.setText(R.string.scoreNotFound);
            val.setTextAppearance(getContext(), android.R.style.TextAppearance_DeviceDefault_Medium);
            val.setTextColor(Color.BLACK);
        }

        AppAdapter listAdapter = new AppAdapter(getActivity(),data,R.layout.list_item);
        ListView list = (ListView) rootView.findViewById(R.id.listViewMedium);

        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {

            }
        });
    }
}
