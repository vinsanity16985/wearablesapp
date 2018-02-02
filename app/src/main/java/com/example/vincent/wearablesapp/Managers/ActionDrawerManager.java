package com.example.vincent.wearablesapp.Managers;

import android.support.wear.widget.drawer.WearableActionDrawerView;

/**
 * Created by Vincent on 1/28/2018.
 */

public class ActionDrawerManager {

    private ActionDrawerManager mInstance;

    private ActionDrawerManager(){}

    public ActionDrawerManager getInstance(){
        if(mInstance != null){
            return mInstance;
        }

        return new ActionDrawerManager();
    }

    public void manage(WearableActionDrawerView navDrawer){

    }

}
