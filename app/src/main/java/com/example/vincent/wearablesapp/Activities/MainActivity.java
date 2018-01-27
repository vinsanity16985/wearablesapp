package com.example.vincent.wearablesapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wear.widget.drawer.WearableActionDrawerView;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;
import android.support.wearable.activity.WearableActivity;
import android.view.MenuItem;

import com.example.vincent.wearablesapp.SimpleClasses.Coin;
import com.example.vincent.wearablesapp.Adapters.CoinAdapter;
import com.example.vincent.wearablesapp.Interfaces.CoinInterface;
import com.example.vincent.wearablesapp.Adapters.NavAdapter;
import com.example.vincent.wearablesapp.R;

import java.util.ArrayList;
import java.util.List;

//Todo: Comment everything
//Todo: Research way to update data throughout the app - Loader or Architecture Components + Data
//Todo: Research way to navigate top level activities (Main, Alert, Settings)
public class MainActivity extends WearableActivity implements MenuItem.OnMenuItemClickListener, CoinInterface {

    private final static int REQUEST_CODE_ADD_COIN = 1;

    private List<Coin> mCoinList;
    private CoinAdapter mCoinAdapter;
    private NavAdapter mNavAdapter;

    private WearableNavigationDrawerView mWearableNavigationDrawer;
    private WearableActionDrawerView mWearableActionDrawer;
    private WearableRecyclerView mWearableRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //----------- Set up Wearable Navigation/Action Drawers -----------//
        mWearableNavigationDrawer = findViewById(R.id.ndMain);
        mWearableActionDrawer = findViewById(R.id.adMain);

        //Set Nav Adapter
        mNavAdapter = new NavAdapter(this);
        mWearableNavigationDrawer.setAdapter(mNavAdapter);
        mWearableNavigationDrawer.getController().peekDrawer();

        mWearableActionDrawer.setOnMenuItemClickListener(this);
        mWearableActionDrawer.getController().peekDrawer();

        //---------------------------------------------------------//

        //Todo: Have NavDrawer take you to selected activity

        //----------- Set up the list of coins in the portfolio -----------//
        mWearableRecyclerView = findViewById(R.id.rvWearable);
        listTesting();
        mCoinAdapter = new CoinAdapter(this, mCoinList);
        mWearableRecyclerView.setAdapter(mCoinAdapter);
        mWearableRecyclerView.setLayoutManager(new WearableLinearLayoutManager(this));
        //-----------------------------------------------------------------//

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Coin newCoin;
        switch (requestCode){
            case REQUEST_CODE_ADD_COIN:
                newCoin = new Coin();
                newCoin.setName(data.getStringExtra("name"));
                newCoin.setExchange(data.getStringExtra("exchange"));
                newCoin.setImageId(data.getIntExtra("imageId", R.drawable.btc_logo));
                newCoin.setMarket(data.getStringExtra("market"));
                break;
            default:
                newCoin = new Coin();
        }

        mCoinList.add(newCoin);
        mCoinAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id){
            case R.id.miMain1:
                //Open Activity to Add new Coin to Portfolio
                Intent intent = new Intent(this, AddCoinActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_COIN);
                break;
            default:
                return false;
        }

        return true;
    }

    private void listTesting(){
        mCoinList = new ArrayList<>();

        Coin c1 = new Coin();
        Coin c2 = new Coin();
        Coin c3 = new Coin();

        c1.setName("Bitcoin");
        c2.setName("Ethereum");
        c3.setName("Bcash");

        mCoinList.add(c1);
        mCoinList.add(c2);
        mCoinList.add(c3);
    }

    @Override
    public void passCoin(Coin coin) {
        Intent intent = new Intent(this, CoinInfoActivity.class);
        intent.putExtra("coin", coin);

        startActivity(intent);
    }
}
