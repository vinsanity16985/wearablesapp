package com.example.vincent.wearablesapp.Interfaces;

import android.view.MenuItem;

/**
 * Created by Vincent on 1/22/2018.
 */

public interface FragmentInterface {

    void manageActionDrawer(int menuId, MenuItem.OnMenuItemClickListener onMenuItemClickListener);

    void manageNavigationDrawer(boolean active);
}
