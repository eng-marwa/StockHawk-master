package com.udacity.stockhawk.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.model.Quote;
import com.udacity.stockhawk.ui.ChartFragment;
import com.udacity.stockhawk.ui.DetailsFragment;

/**
 * Created by MarwaTalaat on 3/24/2017.
 */

public class StockPagerAdapter extends FragmentPagerAdapter {
    private TypedArray tabIcons;
    private Quote quote;

    public StockPagerAdapter(FragmentManager fm, Context context , Quote quote) {
        super(fm);
        tabIcons  = context.getResources().obtainTypedArray(R.array.icons);
        this.quote = quote;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0) return DetailsFragment.getInstance(quote);
        else return ChartFragment.getInstance(quote);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
    public void setupTabIcons(TabLayout tabs) {
        for(int i = 0; i<getCount() ; i++){
            tabs.getTabAt(i).setIcon(tabIcons.getDrawable(i));

        }

    }
}
