package com.example.vincent.wearablesapp.Data;

import android.content.ContentResolver;
import android.provider.BaseColumns;

/**
 * Created by Vincent on 1/24/2018.
 */

public final class CoinContract {

    //Strings for building URI's
    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "com.example.wearablesapp.provider";
    public static final String PATH_COINTABLE = "/" + CoinTable.TABLE_NAME;

    //URIs
    public static final String URI_COINTABLE = SCHEME + AUTHORITY + PATH_COINTABLE;

    //DB Name and Version
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Coin_DB";

    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    private CoinContract(){}

    public static abstract class CoinTable implements BaseColumns{

        private CoinTable(){}

        //Table Name
        public static final String TABLE_NAME = "cointable";

        //Column Names
        public static final String _ID = "id";
        public static final String NAME = "name";
        public static final String TICKER = "ticker";
        public static final String MARKET = "market";
        public static final String EXCHANGE = "exchange";
        public static final String PRICE = "price";
        public static final String LOGO = "logo";

        public static final String[] COLUMNS = {_ID, NAME, TICKER, MARKET, EXCHANGE, PRICE, LOGO};

        public static final String CREATE_COIN_TABLE = CREATE_TABLE + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL, " +
                TICKER + " TEXT NOT NULL, "+
                MARKET + " TEXT NOT NULL, " +
                EXCHANGE + " TEXT NOT NULL, " +
                PRICE + " REAL DEFAULT 0, " +
                LOGO + " INTEGER);";

        public static final String DELETE_COIN_TABLE = DROP_TABLE + TABLE_NAME;

    }

}
