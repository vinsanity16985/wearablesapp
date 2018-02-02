package com.example.vincent.wearablesapp.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vincent.wearablesapp.Activities.MainActivity;
import com.example.vincent.wearablesapp.Adapters.CoinListAdapter;
import com.example.vincent.wearablesapp.Data.CoinContract;
import com.example.vincent.wearablesapp.Interfaces.FragmentInterface;
import com.example.vincent.wearablesapp.R;


public class CoinListFragment extends Fragment implements MenuItem.OnMenuItemClickListener, LoaderManager.LoaderCallbacks{

    private SharedPreferences mSharedPrefs;
    private FragmentInterface mInterface;
    private Context mContext;
    private CoinListAdapter mCoinListAdapter;

    private WearableRecyclerView rvCoinList;


    public CoinListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coin_list, container, false);
        rvCoinList = view.findViewById(R.id.rvCoinList);

        if(savedInstanceState != null){
            //Take saved data and put into mCursor
        }
        //define the recycler view
        mCoinListAdapter = new CoinListAdapter(getContext());
        rvCoinList.setAdapter(mCoinListAdapter);
        rvCoinList.setLayoutManager(new WearableLinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //Tell MainActivity how to manage the Nav and Action Drawers
        mInterface = (MainActivity)mContext;
        mInterface.manageActionDrawer(R.menu.amenu_coinlist, this);
        mInterface.manageNavigationDrawer(true);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();

        switch(itemId){
            case R.id.mCoinListAdd:
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, new CoinAddFragment(), getString(R.string.tag_fragment_coinadd)).commit();
                return true;
            case R.id.mCoinListDelete:
                Toast.makeText(mContext, "Delete Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    //Basically Logically determine what SQL query needs to be sent to the db, and give to the loader
    @Override
    public android.content.Loader onCreateLoader(int i, Bundle bundle) {
        //Location of Data
        Uri uri = Uri.parse(CoinContract.URI_COINTABLE);

        //Columns to return
        String[] projection = CoinContract.CoinTable.COLUMNS;

        //WHERE clause in SQL
        String selection = null;

        //Placing ?'s in selection will be replaced by values in selectionArgs
        String[] selectionArgs = null;

        //ORDER BY clause in SQL
        String sortOrder = null;

        //Create CursorLoader with above parameters
        return new CursorLoader(mContext, uri, projection, selection, selectionArgs,sortOrder);
    }

    @Override
    public void onLoadFinished(android.content.Loader loader, Object o) {
        //Do something with the data returned, update UI with new data
        //Set up Recycler View with data from CoinListAdapter
        mCoinListAdapter.swapCursor((Cursor)o);
    }

    @Override
    public void onLoaderReset(android.content.Loader loader) {
        //Discard old data
        mCoinListAdapter.swapCursor(null);
    }


}
