package com.example.vincent.wearablesapp.Fragments;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vincent.wearablesapp.Activities.MainActivity;
import com.example.vincent.wearablesapp.Interfaces.FragmentInterface;
import com.example.vincent.wearablesapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertAddFragment extends Fragment implements MenuItem.OnMenuItemClickListener{

    private FragmentInterface mInterface;
    private Context mContext;

    public AlertAddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alert_add, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //Tell MainActivity how to manage the Nav and Action Drawers
        mInterface = (MainActivity)mContext;
        mInterface.manageActionDrawer(R.menu.amenu_alertadd, this);
        mInterface.manageNavigationDrawer(false);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        FragmentManager fm = getFragmentManager();

        switch(itemId){
            case R.id.mAlertAddConfirm:
                fm.beginTransaction().replace(R.id.fragment_container, new AlertListFragment(), getString(R.string.tag_fragment_alertlist)).commit();
                return true;
            case R.id.mAlertAddCancel:
                fm.beginTransaction().replace(R.id.fragment_container, new AlertListFragment(), getString(R.string.tag_fragment_alertlist)).commit();
                return true;
            default:
                return false;
        }
    }

}
