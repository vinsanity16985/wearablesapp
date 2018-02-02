package com.example.vincent.wearablesapp.Data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.vincent.wearablesapp.SimpleClasses.Coin;

/**
 * Created by Vincent on 1/24/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private ContentResolver mContentResolver;

    public DatabaseHelper(Context context){
        super(context, CoinContract.DATABASE_NAME, null, 1);
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Necessary Tables
        db.execSQL(CoinContract.CoinTable.CREATE_COIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Todo: Implement onUpgrade - Follow this guide https://thebhwgroup.com/blog/how-android-sqlite-onupgrade
        db.execSQL(CoinContract.CoinTable.DELETE_COIN_TABLE);
        onCreate(db);
    }

    public void addCoin(Coin coin){
        ContentValues values = new ContentValues();
        values.put(CoinContract.CoinTable.NAME, "");

        mContentResolver.insert(Uri.parse(CoinContract.URI_COINTABLE), values);
    }

}
