package com.udacity.stockhawk.adapter;


import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.model.Quote;
import com.udacity.stockhawk.util.PrefUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockRecyclerAdapter extends RecyclerView.Adapter<StockRecyclerAdapter.StockViewHolder> {

    private final Context context;
    private final DecimalFormat dollarFormatWithPlus;
    private final DecimalFormat dollarFormat;
    private final DecimalFormat percentageFormat;
    private Cursor cursor;
    private final StockAdapterOnClickHandler clickHandler;

    public StockRecyclerAdapter(Context context, StockAdapterOnClickHandler clickHandler) {
        this.context = context;
        this.clickHandler = clickHandler;

        dollarFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        dollarFormatWithPlus = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        dollarFormatWithPlus.setPositivePrefix("+$");
        percentageFormat = (DecimalFormat) NumberFormat.getPercentInstance(Locale.getDefault());
        percentageFormat.setMaximumFractionDigits(2);
        percentageFormat.setMinimumFractionDigits(2);
        percentageFormat.setPositivePrefix("+");
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public String getSymbolAtPosition(int position) {

        cursor.moveToPosition(position);
        return cursor.getString(Contract.Quote.POSITION_SYMBOL);
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(context).inflate(R.layout.list_item_quote, parent, false);

        return new StockViewHolder(item);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {

         cursor.moveToPosition(position);


        holder.symbol.setText(cursor.getString(Contract.Quote.POSITION_SYMBOL));
        holder.price.setText(dollarFormat.format(cursor.getFloat(Contract.Quote.POSITION_PRICE)));


        float rawAbsoluteChange = cursor.getFloat(Contract.Quote.POSITION_ABSOLUTE_CHANGE);
        float percentageChange = cursor.getFloat(Contract.Quote.POSITION_PERCENTAGE_CHANGE);

        if (rawAbsoluteChange > 0) {
            holder.change.setBackgroundResource(R.drawable.percent_change_pill_green);
        } else {
            holder.change.setBackgroundResource(R.drawable.percent_change_pill_red);
        }

        String change = dollarFormatWithPlus.format(rawAbsoluteChange);
        String percentage = percentageFormat.format(percentageChange / 100);

        if (PrefUtils.getDisplayMode(context)
                .equals(context.getString(R.string.pref_display_mode_absolute_key))) {
            holder.change.setText(change);
        } else {
            holder.change.setText(percentage);
        }


    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (cursor != null) {
            count = cursor.getCount();
        }
        return count;
    }


    public interface StockAdapterOnClickHandler {
        void onClick(Quote quote);
    }

    class StockViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.symbol)
        TextView symbol;

        @BindView(R.id.price)
        TextView price;

        @BindView(R.id.change)
        TextView change;

        StockViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            cursor.moveToPosition(adapterPosition);
            String symbol = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_SYMBOL));
            String change = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_ABSOLUTE_CHANGE));
            String percentChange = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_PERCENTAGE_CHANGE));
            String price = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_PRICE));
            String history = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_HISTORY));
            String lastTrade = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_LAST_TRADE_DATE));
            String open = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_OPEN));
            String previousClose = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_PREVIOUS_CLOSE));
            String dayLow = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_DAY_LOW));
            String dayHigh = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_DAY_HIGH));
            String yearLow = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_YEAR_LOW));
            String yearHigh = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_YEAR_HIGH));
            String name = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_SYMBOL_NAME));
            String market = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_MARKET_CAP));
            Quote quote = new Quote(symbol,price,percentChange,change,history,lastTrade,open,previousClose,dayLow,dayHigh,yearLow,yearHigh,name,market);
            clickHandler.onClick(quote);
            
            

        }


    }
}
