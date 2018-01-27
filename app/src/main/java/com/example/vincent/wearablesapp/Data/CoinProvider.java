package com.example.vincent.wearablesapp.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Vincent on 1/24/2018.
 */

//Todo: Add test values
//Todo: Research Loaders and SyncAdapters
public class CoinProvider extends ContentProvider {

    //Flags for choosing the URI
    private static final int COINTTABLE_URI = 1;
    private static final int COINTABLE_ROW_URI = 10;

    private DatabaseHelper mDBHelper;
    private static UriMatcher mMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //Add URI's to matcher for easy access
    static{
        mMatcher.addURI(CoinContract.AUTHORITY, CoinContract.URI_COINTABLE, COINTTABLE_URI);
        mMatcher.addURI(CoinContract.AUTHORITY, CoinContract.URI_COINTABLE + "/#", COINTABLE_ROW_URI);
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(CoinContract.CoinTable.TABLE_NAME);
        int uriType = mMatcher.match(uri);

        switch(uriType){
            case COINTTABLE_URI:
                //Query whole table
                break;
            case COINTABLE_ROW_URI:
                //Query for single row
                break;
            default:
                //Error Handling
        }
        Cursor cursor = queryBuilder.query(mDBHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    //Returns the MIME type of the given uri
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int uriType = mMatcher.match(uri);

        switch(uriType){
            case COINTTABLE_URI:
                return "text";
            default:
                return null;
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //Determine which table to open here
        int uriType = mMatcher.match(uri);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case COINTTABLE_URI:
                id = db.insert(CoinContract.CoinTable.TABLE_NAME, null, contentValues);
                break;
            default:
                throw new IllegalArgumentException("Bad");
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return Uri.parse(CoinContract.URI_COINTABLE + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = mMatcher.match(uri);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowsDeleted = 0;

        switch (uriType){
            case COINTTABLE_URI:
                rowsDeleted = db.delete(CoinContract.CoinTable.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Bad");
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = mMatcher.match(uri);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowsUpdated = 0;

        switch(uriType){
            case COINTTABLE_URI:
                rowsUpdated = db.update(CoinContract.CoinTable.TABLE_NAME, contentValues, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("No");
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdated;
    }
}
