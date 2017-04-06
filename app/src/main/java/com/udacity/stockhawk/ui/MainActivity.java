package com.udacity.stockhawk.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.adapter.StockRecyclerAdapter;
import com.udacity.stockhawk.model.Quote;
import com.udacity.stockhawk.util.PrefUtils;
import com.udacity.stockhawk.sync.QuoteSyncJob;
import com.udacity.stockhawk.util.NetworkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static com.udacity.stockhawk.ui.StockFragment.adapter;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        StockRecyclerAdapter.StockAdapterOnClickHandler {
    static boolean mTwoPane = false;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.error)
    TextView error;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;


    @OnClick(R.id.fab)
    public void button(@SuppressWarnings("UnusedParameters") View view) {
        new AddStockDialog().show(getFragmentManager(), "StockDialogFragment");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StockFragment fragment = (StockFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.getDataToFragment(error, swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();



    }

    @Override
    public void onClick(Quote quote) {
        Timber.d("Symbol clicked: %s", quote.getSymbol());

            Intent i = new Intent(MainActivity.this, DetailsActivity.class);
            i.putExtra("quote", quote);
            startActivity(i);

    }

    @Override
    public void onRefresh() {
        QuoteSyncJob.syncImmediately(this);

        if (!NetworkUtil.networkUp(this) && adapter.getItemCount() == 0) {
            swipeRefreshLayout.setRefreshing(false);
            error.setText(getString(R.string.error_no_network));
            error.setVisibility(View.VISIBLE);
        } else if (!NetworkUtil.networkUp(this)) {
            swipeRefreshLayout.setRefreshing(false);
            Snackbar.make(findViewById(R.id.coordinate), R.string.toast_no_connectivity,
                    Snackbar.LENGTH_LONG)
                    .show();
        } else if (PrefUtils.getStocks(this).size() == 0) {
            swipeRefreshLayout.setRefreshing(false);
            error.setText(getString(R.string.error_no_stocks));
            error.setVisibility(View.VISIBLE);
        } else {
            error.setVisibility(View.GONE);
        }
    }

    public void addStock(String symbol) {
        if (symbol != null && !symbol.isEmpty()) {

            if (NetworkUtil.networkUp(this)) {
                swipeRefreshLayout.setRefreshing(true);
            } else {
                String message = getString(R.string.toast_stock_added_no_connectivity, symbol);
                Snackbar.make(findViewById(R.id.coordinate), message,
                        Snackbar.LENGTH_LONG)
                        .show();            }

            PrefUtils.addStock(this, symbol);
            QuoteSyncJob.syncImmediately(this);
        }
    }


}
