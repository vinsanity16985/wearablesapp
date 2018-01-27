package com.example.vincent.wearablesapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.wear.widget.drawer.WearableActionDrawerView;
import android.support.wearable.activity.WearableActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.vincent.wearablesapp.SimpleClasses.Coin;
import com.example.vincent.wearablesapp.R;

//Todo: Make this class's UI pretty
//Todo: Add action drawer button to add alerts
//Todo: Add navigation to go to already set alerts
public class CoinInfoActivity extends WearableActivity implements MenuItem.OnMenuItemClickListener{

    private Coin mCoin;

    private WearableActionDrawerView adCoinInfo;
    private TextView tvCoinInfoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_info);
        Intent intent = getIntent();
        mCoin = intent.getExtras().getParcelable("coin");

        adCoinInfo = findViewById(R.id.adCoinInfo);
        adCoinInfo.setOnMenuItemClickListener(this);
        adCoinInfo.getController().peekDrawer();

        tvCoinInfoName = findViewById(R.id.tvCoinInfoName);
        tvCoinInfoName.setText(mCoin.getName());

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id){
            case R.id.miCoinInfo1:
                //Navigate back to MainActivity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.miCoinInfo2:
                Intent intent1 = new Intent(this, AddAlertActivity.class);
                intent1.putExtra("coin", mCoin);
                startActivity(intent1);
                break;
            default:
                return false;
        }

        return true;
    }
}
