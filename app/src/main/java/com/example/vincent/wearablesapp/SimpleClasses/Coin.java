package com.example.vincent.wearablesapp.SimpleClasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.vincent.wearablesapp.R;

import java.util.ArrayList;

/**
 * Created by Vincent on 1/21/2018.
 */

//Todo: Decided what data is necessary for this class
public class Coin implements Parcelable {

    private ArrayList<Alert> mAlerts;

    private int mImageId;
    private String mName;
    private String mTicker;
    private String mMarket;
    private String mExchange;
    private double mPrice;

    public Coin() {
        //Todo: Change hardcoded values
        mAlerts = new ArrayList<>();
        mImageId = R.drawable.btc_logo;
        mName = "Bitcoin";
        mTicker = "BTC";
        mMarket = "BTC/USD";
        mExchange = "Bitfinex";
        mPrice = 10000;
    }

    public void addAlert(Alert alert){
        mAlerts.add(alert);
    }
    public ArrayList<Alert> getAlerts(){
        return mAlerts;
    }
    public void removeAlert(int index){
        mAlerts.remove(index);
    }

    public void setImageId(int id){
        mImageId = id;
    }
    public void setName(String name){
        mName = name;
    }
    public void setTicker(String ticker){
        mTicker = ticker;
    }
    public void setMarket(String market){
        mMarket = market;
    }
    public void setExchange(String exchange){
        mExchange = exchange;
    }
    public void setPrice(double price){
        mPrice = price;
    }

    public int getImageId(){
        return mImageId;
    }
    public String getName(){
        return mName;
    }
    public String getTicker(){
        return mTicker;
    }
    public String getMarket(){
        return mMarket;
    }
    public String getExchange(){
        return mExchange;
    }
    public double getPrice(){
        return mPrice;
    }

    private Coin(Parcel in) {
        if(mAlerts == null){
            mAlerts = new ArrayList<>();
        }
        in.readTypedList(mAlerts, Alert.CREATOR);
        mImageId = in.readInt();
        mName = in.readString();
        mTicker = in.readString();
        mMarket = in.readString();
        mExchange = in.readString();
        mPrice = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mAlerts);
        dest.writeInt(mImageId);
        dest.writeString(mName);
        dest.writeString(mTicker);
        dest.writeString(mMarket);
        dest.writeString(mExchange);
        dest.writeDouble(mPrice);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Coin> CREATOR = new Parcelable.Creator<Coin>() {
        @Override
        public Coin createFromParcel(Parcel in) {
            return new Coin(in);
        }

        @Override
        public Coin[] newArray(int size) {
            return new Coin[size];
        }
    };

}
