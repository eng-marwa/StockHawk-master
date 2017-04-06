package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.adapter.StockPagerAdapter;
import com.udacity.stockhawk.model.Quote;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager viewPager;
    
    @BindView(R.id.tab)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Quote quote = getIntent().getExtras().getParcelable("quote");
        setUpTabWithPager(quote);



    }


    private void setUpTabWithPager(Quote quote) {
        Log.i("TT",quote.getSymbol());
        StockPagerAdapter adapter = new StockPagerAdapter(getSupportFragmentManager(),DetailsActivity.this,quote);
        tabLayout.setupWithViewPager(viewPager, true);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        adapter.setupTabIcons(tabLayout);
    }

}
