package com.example.vincent.wearablesapp.SimpleClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vincent on 1/23/2018.
 */

public class Alert implements Parcelable{

    private String mExchange;
    private String mCoinName;
    private String mMarket;
    private double mPriceThreshold;

    public Alert(){
        //Todo: Change Hardcoded Values
        mExchange = "Binance";
        mCoinName = "Bitcoin";
        mMarket = "BTC/USDT";
        mPriceThreshold = 10000;
    }
    public Alert(Coin c){
        mExchange = c.getExchange();
        mCoinName = c.getName();
        mMarket = c.getMarket();
        mPriceThreshold = 1000;
    }

    public void setExchange(String exchange){
        mExchange = exchange;
    }
    public void setCoinName(String coinName){
        mCoinName = coinName;
    }
    public void setMarket(String market){
        mMarket = market;
    }
    public void setPriceThreshold(double priceThreshold){
        mPriceThreshold = priceThreshold;
    }

    public String getExchange(){
        return mExchange;
    }
    public String getCoinName(){
        return mCoinName;
    }
    public String getMarket(){
        return mMarket;
    }
    public double getPriceThreshold(){
        return mPriceThreshold;
    }

    public Alert(Parcel in){
        mExchange = in.readString();
        mCoinName = in.readString();
        mMarket = in.readString();
        mPriceThreshold = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mExchange);
        parcel.writeString(mCoinName);
        parcel.writeString(mMarket);
        parcel.writeDouble(mPriceThreshold);
    }

    public static final Parcelable.Creator<Alert> CREATOR = new Parcelable.Creator<Alert>() {
        @Override
        public Alert createFromParcel(Parcel in) {
            return new Alert(in);
        }

        @Override
        public Alert[] newArray(int size) {
            return new Alert[size];
        }
    };
}
