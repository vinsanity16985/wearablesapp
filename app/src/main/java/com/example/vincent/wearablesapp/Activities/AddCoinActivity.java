package com.example.vincent.wearablesapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vincent.wearablesapp.R;

//Todo: Add option to cancel new coin creation
//Todo: Make the UI for this class pretty
public class AddCoinActivity extends WearableActivity implements View.OnClickListener{

    private ImageView ivCoinLogo;
    private Spinner spCoinName;
    private Spinner spCoinExchange;
    private Spinner spCoinMarket;
    private Button bCoinAdd;

    private int coinImageId;
    private String coinName;
    private String coinExchange;
    private String coinMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coin);

        ivCoinLogo = findViewById(R.id.ivAddCoin);
        spCoinName = findViewById(R.id.spinAddCoinName);
        spCoinExchange = findViewById(R.id.spinAddCoinExchange);
        spCoinMarket = findViewById(R.id.spinAddCoinMarket);
        bCoinAdd = findViewById(R.id.bCoinAdd);

        bCoinAdd.setOnClickListener(this);

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public void onClick(View view) {
        //Todo: Change Hardcoded Values
        Intent resultIntent = new Intent();
        resultIntent.putExtra("imageId", R.drawable.eth_logo);
        resultIntent.putExtra("name", "Bitcoin");
        resultIntent.putExtra("exchange", "Binance");
        resultIntent.putExtra("market", "BTC/USDT");

        setResult(0, resultIntent);
        finish();
    }
}
