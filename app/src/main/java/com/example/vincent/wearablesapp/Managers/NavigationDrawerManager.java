package com.example.vincent.wearablesapp.Managers;

import android.support.wear.widget.drawer.WearableNavigationDrawerView;

/**
 * Created by Vincent on 1/28/2018.
 */

public class NavigationDrawerManager {

    private NavigationDrawerManager mInstance;

    private NavigationDrawerManager(){}

    public NavigationDrawerManager getInstance(){
        if(mInstance != null){
            return mInstance;
        }

        return new NavigationDrawerManager();
    }

    public void manage(WearableNavigationDrawerView navDrawer){

    }

}
