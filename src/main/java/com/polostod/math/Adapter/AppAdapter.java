package com.polostod.math.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.polostod.math.QuestionEasy.EmathHelper;
import com.polostod.math.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pratma.net on 8/9/2016.
 */
public class AppAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater layInf = null;
    private int layoutItem;

    public AppAdapter(Activity act, ArrayList<HashMap<String, String>> data,
                      int layout) {
        activity = act;
        this.data = data;
        layInf = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutItem = layout;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        View vi = view;
        if (view == null) {
            vi = layInf.inflate(layoutItem, null);
        }

        HashMap<String, String> list = new HashMap<>();
        list = data.get(i);
        if (layoutItem == R.layout.list_item) {

            TextView nama = (TextView)
                    vi.findViewById(R.id.txtListName);
            TextView nim = (TextView)
                    vi.findViewById(R.id.txtListScore);

            nama.setText(list.get(EmathHelper.name));
            nim.setText(list.get(EmathHelper.highscore));
        }
        return vi;
    }
}
