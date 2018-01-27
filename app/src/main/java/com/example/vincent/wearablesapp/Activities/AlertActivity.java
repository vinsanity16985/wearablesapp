package com.example.vincent.wearablesapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.wear.widget.drawer.WearableActionDrawerView;
import android.support.wearable.activity.WearableActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.vincent.wearablesapp.Activities.AddAlertActivity;
import com.example.vincent.wearablesapp.R;

public class AlertActivity extends WearableActivity implements MenuItem.OnMenuItemClickListener{

    private TextView mTextView;

    private WearableActionDrawerView adAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        mTextView = findViewById(R.id.text);
        adAlert = findViewById(R.id.adAlert);

        adAlert.setOnMenuItemClickListener(this);
        adAlert.getController().peekDrawer();

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id){
            case R.id.miAlert1:
                Intent intent = new Intent(this, AddAlertActivity.class);
                startActivity(intent);
                break;
            default:
                return false;
        }

        return true;
    }
}
