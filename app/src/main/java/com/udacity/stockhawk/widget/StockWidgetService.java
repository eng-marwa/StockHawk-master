package com.udacity.stockhawk.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.util.PrefUtils;
import com.udacity.stockhawk.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarwaTalaat on 3/16/2017.
 */

public class StockWidgetService extends RemoteViewsService {

    private boolean showPercent;
    private float rawAbsoluteChange;
    private float percentageChange;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StockViewsFactory(this.getApplicationContext(),
                intent);
    }

    class StockViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context ctxt;
        private int appWidgetId;
        private List<Stock> stocks;

        public StockViewsFactory(Context ctxt, Intent intent) {
            this.ctxt = ctxt;
            appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        }

        @Override
        public void onCreate() {
            // no-op
        }

        @Override
        public void onDestroy() {
            // no-op
        }

        @Override
        public int getCount() {
            return stocks.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews row = new RemoteViews(ctxt.getPackageName(), R.layout.row);

            String symbol = stocks.get(position).symbol;
            String bidPrice = stocks.get(position).bidPrice;
            String change = stocks.get(position).change;


            row.setTextViewText(R.id.symbol, symbol);
            row.setTextViewText(R.id.price, bidPrice);


            if (rawAbsoluteChange > 0) {
                row.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_green);
            } else {
                row.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_red);
            }
            String abschange = Utility.dollarFormatWithPlus.format(rawAbsoluteChange);
            String percentage = Utility.percentageFormat.format(percentageChange / 100);

            if (PrefUtils.getDisplayMode(ctxt)
                    .equals(ctxt.getString(R.string.pref_display_mode_absolute_key))) {
                row.setTextViewText(R.id.change, abschange);
            } else {
                row.setTextViewText(R.id.change, percentage);
            }


            Intent fillInIntent = new Intent();
            Uri quoteUri = Contract.Quote.URI;
            fillInIntent.setData(quoteUri);
            fillInIntent.putExtra("symbol",symbol);
            row.setOnClickFillInIntent(R.id.symbol, fillInIntent);


            return row;
        }

        @Override
        public RemoteViews getLoadingView() {
            return (null);
        }

        @Override
        public int getViewTypeCount() {
            return (1);
        }

        @Override
        public long getItemId(int position) {
            return (position);
        }

        @Override
        public boolean hasStableIds() {
            return (true);
        }

        @Override
        public void onDataSetChanged() {
            Cursor cursor = ctxt.getContentResolver().query(Contract.Quote.URI,
                    new String[]{Contract.Quote._ID, Contract.Quote.COLUMN_SYMBOL, Contract.Quote.COLUMN_PRICE,
                            Contract.Quote.COLUMN_ABSOLUTE_CHANGE, Contract.Quote.COLUMN_PERCENTAGE_CHANGE}, null, null, Contract.Quote.COLUMN_PRICE + " desc");
            stocks = new ArrayList<>(cursor.getCount());

            if (cursor.moveToFirst()) {
                do {
                    rawAbsoluteChange = Float.parseFloat(cursor.getString(Contract.Quote.POSITION_ABSOLUTE_CHANGE));
                    percentageChange = Float.parseFloat(cursor.getString(Contract.Quote.POSITION_PERCENTAGE_CHANGE));

                    stocks.add(new Stock(
                            cursor.getString(Contract.Quote.POSITION_SYMBOL),
                            cursor.getString(Contract.Quote.POSITION_PRICE),
                            showPercent ? cursor.getString(Contract.Quote.POSITION_PERCENTAGE_CHANGE) : cursor.getString(Contract.Quote.POSITION_ABSOLUTE_CHANGE))

                    );
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    class Stock {
        public final String symbol;
        public final String bidPrice;
        public final String change;

        public Stock(String symbol, String bidPrice, String change) {
            this.symbol = symbol;
            this.bidPrice = bidPrice;
            this.change = change;
        }
    }
}