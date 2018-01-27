package com.example.vincent.wearablesapp.SimpleClasses;

/**
 * Created by Vincent on 1/22/2018.
 */

public class NavDrawerItem {

    private String title;
    private int drawableId;

    public NavDrawerItem(String title, int drawable){
        this.title = title;
        drawableId = drawable;
    }

    public String getTitle(){
        return title;
    }

    public int getDrawableId(){
        return drawableId;
    }
}
