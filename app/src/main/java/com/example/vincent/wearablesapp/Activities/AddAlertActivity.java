package com.example.vincent.wearablesapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vincent.wearablesapp.SimpleClasses.Alert;
import com.example.vincent.wearablesapp.SimpleClasses.Coin;
import com.example.vincent.wearablesapp.R;

public class AddAlertActivity extends WearableActivity implements View.OnClickListener{

    private int ACTIVITY_ALERT = 1;
    private int ACTIVITY_COININFO = 2;

    private Alert mAlert;
    private Coin mCoin;
    private int returnActivity;

    private TextView mTextView;
    private Button bAlertAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);

        Intent intent = getIntent();

        if(intent != null){
            returnActivity = ACTIVITY_COININFO;
            mCoin = intent.getExtras().getParcelable("coin");
            mAlert = new Alert(mCoin);
        }else{
            returnActivity = ACTIVITY_ALERT;
            mAlert = new Alert();
        }



        mTextView = findViewById(R.id.text);
        bAlertAdd = findViewById(R.id.bAlertAdd);

        bAlertAdd.setOnClickListener(this);

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public void onClick(View view) {
        int viedId = view.getId();

        switch (viedId){
            case R.id.bAlertAdd:
                //Todo: Pass created Alert to where it needs to go
                Intent intent;
                if(returnActivity == ACTIVITY_COININFO){
                    intent = new Intent(this, CoinInfoActivity.class);
                    intent.putExtra("coin", mCoin);
                }else{
                    intent = new Intent(this, AlertActivity.class);
                }
                startActivity(intent);
                break;
        }
    }
}
