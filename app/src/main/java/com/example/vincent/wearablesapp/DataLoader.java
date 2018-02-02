package com.example.vincent.wearablesapp;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by Vincent on 2/1/2018.
 */

public class DataLoader extends AsyncTaskLoader {


    public DataLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }
}
