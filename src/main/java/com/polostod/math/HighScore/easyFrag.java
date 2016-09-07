package com.polostod.math.HighScore;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.polostod.math.Adapter.AppAdapter;
import com.polostod.math.Dialog.deleteDialog;
import com.polostod.math.QuestionEasy.EmathHelper;
import com.polostod.math.R;

import java.util.ArrayList;
import java.util.HashMap;

public class easyFrag extends android.support.v4.app.Fragment {
    ArrayList<HashMap<String,String>> data;
    View rootView;
    TextView val;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         rootView = inflater.inflate(R.layout.highscore_easy, container, false);
            readData();
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        readData();
    }

    public void readData(){
        EmathHelper emathHelper = new EmathHelper(getActivity());
        val = (TextView) rootView.findViewById(R.id.validate);
        ArrayList<HashMap<String, String>> studentList = emathHelper.getHighScore();
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
        ListView list = (ListView) rootView.findViewById(R.id.listView);

        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {
                String names = data.get(i).get(EmathHelper.name);
                Intent intent = new Intent(getActivity(),deleteDialog.class);
                Bundle bundle = new Bundle();
                bundle.putString("names",names);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
