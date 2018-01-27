package com.example.vincent.wearablesapp.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;

import com.example.vincent.wearablesapp.SimpleClasses.NavDrawerItem;
import com.example.vincent.wearablesapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 1/22/2018.
 */

public class NavAdapter extends WearableNavigationDrawerView.WearableNavigationDrawerAdapter {

    private List<NavDrawerItem> mNavDrawerItems;
    private Context mContext;

    public NavAdapter(Context context){
        mContext = context;
        mNavDrawerItems = new ArrayList<>();

        NavDrawerItem item1 = new NavDrawerItem("Portfolio", R.drawable.btc_logo);
        NavDrawerItem item2 = new NavDrawerItem("Alerts", R.drawable.btc_logo);
        NavDrawerItem item3 = new NavDrawerItem("Settings", R.drawable.btc_logo);

        mNavDrawerItems.add(item1);
        mNavDrawerItems.add(item2);
        mNavDrawerItems.add(item3);
    }

    @Override
    public CharSequence getItemText(int pos) {
        return mNavDrawerItems.get(pos).getTitle();
    }

    @Override
    public Drawable getItemDrawable(int pos) {
        int drawableId = mNavDrawerItems.get(pos).getDrawableId();
        Drawable drawable = mContext.getResources().getDrawable(drawableId);
        return drawable;
    }

    @Override
    public int getCount() {
        return mNavDrawerItems.size();
    }
}
