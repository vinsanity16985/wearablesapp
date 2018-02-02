package com.example.vincent.wearablesapp.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.wear.widget.drawer.WearableActionDrawerView;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vincent.wearablesapp.Data.CoinContract;
import com.example.vincent.wearablesapp.Data.DatabaseHelper;
import com.example.vincent.wearablesapp.Fragments.AlertListFragment;
import com.example.vincent.wearablesapp.Fragments.CoinListFragment;
import com.example.vincent.wearablesapp.Interfaces.FragmentInterface;
import com.example.vincent.wearablesapp.Adapters.NavAdapter;
import com.example.vincent.wearablesapp.R;

//Todo: Test the database by adding values and test the RecyclerViewAdapter
//Todo: Comment everything
//Todo: Research way to update data throughout the app - Loader or Architecture Components + Data
public class MainActivity extends WearableActivity implements FragmentInterface, WearableNavigationDrawerView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";

    private FragmentInterface mFragmentInterface;
    private SharedPreferences mSharedPrefs;

    private NavAdapter mNavAdapter;

    private WearableNavigationDrawerView mWearableNavigationDrawer;
    private WearableActionDrawerView mWearableActionDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Testing
        DBTesting();
        //----------- Set up Wearable Navigation/Action Drawers -----------//
        mWearableNavigationDrawer = findViewById(R.id.ndMain);
        mWearableActionDrawer = findViewById(R.id.adMain);

        //Set Nav Adapter
        mNavAdapter = new NavAdapter(this);
        mWearableNavigationDrawer.setAdapter(mNavAdapter);
        mWearableNavigationDrawer.addOnItemSelectedListener(this);

        //Set up Coin List Action Drawer
        //mWearableActionDrawer.setOnMenuItemClickListener(this);

        //---------------------------------------------------------//

        FragmentManager fManager = getFragmentManager();
        fManager.beginTransaction().replace(R.id.fragment_container, new CoinListFragment(), getString(R.string.tag_fragment_coinlist)).commit();
        //Todo: Have NavDrawer take you to selected activity

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public void onItemSelected(int pos) {
        FragmentManager fm = getFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.fragment_container);
        Log.d(getString(R.string.tag_activity_main), "Current Fragment Tag: " + currentFragment.getTag());

        switch(pos){
            case 0:
                if(currentFragment.getTag() != getString(R.string.tag_fragment_coinlist)){
                    fm.beginTransaction().replace(R.id.fragment_container, new CoinListFragment(), getString(R.string.tag_fragment_coinlist)).commit();
                }
                break;
            case 1:
                if(currentFragment.getTag() != getString(R.string.tag_fragment_alertlist)){
                    fm.beginTransaction().replace(R.id.fragment_container, new AlertListFragment(), getString(R.string.tag_fragment_alertlist)).commit();
                }
                break;
            case 2:
                break;
        }
    }

    @Override
    public void manageActionDrawer(int menuId, MenuItem.OnMenuItemClickListener listener) {
        mWearableActionDrawer.setOnMenuItemClickListener(listener);
        Menu menu = mWearableActionDrawer.getMenu();
        menu.clear();
        getMenuInflater().inflate(menuId, menu);
        mWearableActionDrawer.getController().peekDrawer();
    }

    @Override
    public void manageNavigationDrawer(boolean isActive) {
        if(isActive){
            mWearableNavigationDrawer.setIsLocked(false);
            mWearableNavigationDrawer.getController().peekDrawer();
        }else{
            mWearableNavigationDrawer.getController().closeDrawer();
            mWearableNavigationDrawer.setIsLocked(true);
        }
    }

    private void DBTesting(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CoinContract.CoinTable.NAME, "Bitcoin");
        values.put(CoinContract.CoinTable.TICKER, "BTC");
        values.put(CoinContract.CoinTable.MARKET, "BTC/USD");
        values.put(CoinContract.CoinTable.EXCHANGE, "Bitfinex");
        values.put(CoinContract.CoinTable.PRICE, "10000");
        values.put(CoinContract.CoinTable.LOGO, R.drawable.btc_logo);

        long rowId = db.insert(CoinContract.CoinTable.TABLE_NAME, null, values);

        Log.d(getString(R.string.tag_activity_main), "Inserted Row - " + rowId);
    }
}
