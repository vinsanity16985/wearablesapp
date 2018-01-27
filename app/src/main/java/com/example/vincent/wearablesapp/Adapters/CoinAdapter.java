package com.example.vincent.wearablesapp.Adapters;

import android.content.Context;
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

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> {

    private LayoutInflater mInflater;
    private List<Coin> mCoinList = Collections.emptyList();
    private Context mContext;

    public CoinAdapter(Context context, List<Coin> coinList){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mCoinList = coinList;
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.coin_row, parent, false);
        CoinViewHolder viewHolder = new CoinViewHolder(view, mContext);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        Coin currentCoin = mCoinList.get(position);

        int logo = currentCoin.getImageId();
        String name = currentCoin.getName();
        String exchange = currentCoin.getExchange();
        String price = Double.toString(currentCoin.getPrice());

        holder.setCoin(currentCoin);
        holder.setLogo(logo);
        holder.setName(name);
        holder.setExchange(exchange);
        holder.setPrice(price);
    }

    @Override
    public int getItemCount() {
        return mCoinList.size();
    }



    class CoinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Keep track of the coin the current CoinViewHolder is attached to
        private Coin mCoin;
        private MainActivity mMainActivity;

        private ImageView ivLogo;
        private TextView tvName;
        private TextView tvExchange;
        private TextView tvPrice;

        public CoinViewHolder(View itemView, Context context) {
            super(itemView);

            mMainActivity = (MainActivity) context;

            itemView.setOnClickListener(this);
            ivLogo = itemView.findViewById(R.id.ivCoinLogo);
            tvName = itemView.findViewById(R.id.tvCoinName);
            tvExchange = itemView.findViewById(R.id.tvCoinExchange);
            tvPrice = itemView.findViewById(R.id.tvCoinPrice);
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

        public void setPrice(String price){
            tvPrice.setText(price);
        }

        public void setCoin(Coin coin){
            mCoin = coin;
        }

        @Override
        public void onClick(View view) {
            mMainActivity.passCoin(mCoin);
        }
    }
}
