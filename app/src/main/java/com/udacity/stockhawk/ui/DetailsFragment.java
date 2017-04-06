package com.udacity.stockhawk.ui;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.model.Quote;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    @BindView(R.id.stock_name)
    TextView stockNameView;

    @BindView(R.id.stock_symbol)
    TextView stockSymbolView;


    @BindView(R.id.last_trade_date)
    TextView lastTradeView;


    @BindView(R.id.open)
    TextView openView;

    @BindView(R.id.close)
    TextView previousCloseView;

    @BindView(R.id.dayLow)
    TextView dayLowView;

    @BindView(R.id.dayHigh)
    TextView dayHighView;

    @BindView(R.id.yearLow)
    TextView yearLowView;

    @BindView(R.id.yearHigh)
    TextView yearHighView;

    @BindView(R.id.market)
    TextView marketView;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    public static DetailsFragment getInstance(Quote quote) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("quote", quote);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        if (bundle != null) {
            Quote quote = bundle.getParcelable("quote");
            if (quote != null) {

                stockNameView.setText(quote.getName());
                stockSymbolView.setText(quote.getSymbol());
                openView.setText(new DecimalFormat("#.##").format(Float.parseFloat(quote.getOpen())));
                previousCloseView.setText(new DecimalFormat("#.##").format(Float.parseFloat(quote.getPreviousClose())));
                lastTradeView.setText(quote.getLastTradeDate());
                marketView.setText(quote.getMarketCapitalization());
                dayHighView.setText(new DecimalFormat("#.##").format(Float.parseFloat(quote.getDaysHigh())));
                dayLowView.setText(new DecimalFormat("#.##").format(Float.parseFloat(quote.getDaysLow())));
                yearHighView.setText(new DecimalFormat("#.##").format(Float.parseFloat(quote.getYearHigh())));
                yearLowView.setText(new DecimalFormat("#.##").format(Float.parseFloat(quote.getYearLow())));
            }
        }
    }
}
