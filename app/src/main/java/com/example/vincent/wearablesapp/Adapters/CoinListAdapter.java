package com.example.vincent.wearablesapp.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vincent.wearablesapp.Activities.MainActivity;
import com.example.vincent.wearablesapp.SimpleClasses.Coin;
import com.example.vincent.wearablesapp.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Vincent on 1/21/2018.
 */

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.CoinViewHolder> {

    private LayoutInflater mInflater;
    private Cursor mCursor;
    private Context mContext;

    public CoinListAdapter(Context context, Cursor coinCursor){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mCursor = coinCursor;
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.coin_row, parent, false);
        CoinViewHolder viewHolder = new CoinViewHolder(view, mContext);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        String name = mCursor.getString(1);
        String ticker = mCursor.getString(2);
        String market = mCursor.getString(3);
        String exchange = mCursor.getString(4);
        double price = mCursor.getDouble(5);
        int logo = mCursor.getInt(6);

        holder.setName(name);
        holder.setTicker(ticker);
        holder.setMarket(market);
        holder.setExchange(exchange);
        holder.setPrice(price);
        holder.setLogo(logo);
    }

    @Override
    public int getItemCount() {
        if(mCursor != null)
        {
            return mCursor.getCount();
        }
        return 0;
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor == newCursor){
            return;
        }

        if(newCursor != null){
            mCursor = newCursor;
            notifyDataSetChanged();
        }
    }

    class CoinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private MainActivity mMainActivity;

        private ImageView ivLogo;
        private TextView tvName;
        private TextView tvExchange;
        private TextView tvPrice;
        private TextView tvTicker;
        private TextView tvMarket;

        public CoinViewHolder(View itemView, Context context) {
            super(itemView);

            mMainActivity = (MainActivity) context;

            itemView.setOnClickListener(this);
            ivLogo = itemView.findViewById(R.id.ivCoinLogo);
            tvName = itemView.findViewById(R.id.tvCoinName);
            tvExchange = itemView.findViewById(R.id.tvCoinExchange);
            tvPrice = itemView.findViewById(R.id.tvCoinPrice);
            tvTicker = itemView.findViewById(R.id.tvCoinTicker);
            tvMarket = itemView.findViewById(R.id.tvCoinMarket);
        }

        public void setTicker(String ticker){
            tvTicker.setText(ticker);
        }

        public void setMarket(String market){
            tvMarket.setText(market);
        }

        public void setLogo(int logoId){
            ivLogo.setImageResource(logoId);
        }

        public void setName(String name){
            tvName.setText(name);
        }

        public void setExchange(String exchange){
            tvExchange.setText(exchange);
        }

        public void setPrice(double price){
            tvPrice.setText(Double.toString(price));
        }

        @Override
        public void onClick(View view) {
            //mMainActivity.changeFragment(1);
        }
    }
}
