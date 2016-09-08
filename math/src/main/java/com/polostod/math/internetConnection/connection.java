package com.polostod.math.internetConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by polostod-dev on 26/04/16.
 */
public class connection {

    private Context Acontext;

    public connection(Context context)
    {
        this.Acontext = context;
    }

    public boolean connectToInternet()
    {
        ConnectivityManager connectivity = (ConnectivityManager) Acontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }
}
