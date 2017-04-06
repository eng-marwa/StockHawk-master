package com.udacity.stockhawk.sync;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.udacity.stockhawk.api.StockClient;
import com.udacity.stockhawk.api.StockService;
import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.model.Quote;
import com.udacity.stockhawk.model.Results;
import com.udacity.stockhawk.model.Root;
import com.udacity.stockhawk.util.NetworkUtil;
import com.udacity.stockhawk.util.PrefUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockQuote;

public final class QuoteSyncJob {

    private static final int ONE_OFF_ID = 2;
    private static final String ACTION_DATA_UPDATED = "com.udacity.stockhawk.ACTION_DATA_UPDATED";
    private static final int PERIOD = 300000;
    private static final int INITIAL_BACKOFF = 10000;
    private static final int PERIODIC_ID = 1;
    private static final int YEARS_OF_HISTORY = 2;

    private QuoteSyncJob() {
    }

    static void getQuotes(final Context context) {
        Timber.d("Running sync job");

        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.YEAR, -YEARS_OF_HISTORY);


        StringBuilder mStoredSymbols = new StringBuilder();
        Set<String> stockPref = PrefUtils.getStocks(context);
        for (String s : stockPref) {
            mStoredSymbols.append("\"" + s + "\",");
        }



        try {

            if (NetworkUtil.networkUp(context)) {

                final Retrofit retrofit = StockClient.setRetrofitClient();
                StockService stockService = retrofit.create(StockService.class);


                mStoredSymbols.replace(mStoredSymbols.length() - 1, mStoredSymbols.length(), ")");


                String q = "select * from yahoo.finance.quotes where symbol in (" + mStoredSymbols.toString();
                Timber.i(q);
                Call<Root> call = stockService.getStockDetails(q, "json", "store://datatables.org/alltableswithkeys");

                call.enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        Results results = response.body().getQuery().getResults();
                        Quote quote = results.getQuote();
                        addQuote(quote, context);
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {
                        Log.i("resp", t.getMessage());

                    }
                });


                Set<String> stockCopy = new HashSet<>();
                stockCopy.addAll(stockPref);
                String[] stockArray = stockPref.toArray(new String[stockPref.size()]);


                if (stockArray.length == 0) {
                    return;
                }

                Map<String, Stock> quotes = YahooFinance.get(stockArray);
                Iterator<String> iterator = stockCopy.iterator();
                ArrayList<ContentValues> quoteCVs = new ArrayList<>();

                while (iterator.hasNext()) {
                    String symbol = iterator.next();


                    Stock stock = quotes.get(symbol);
                    StockQuote quote = stock.getQuote();
                    if (quote.getPrice() != null) {
                        Timber.i(symbol+"have a price");
                        symbol = quote.getSymbol();
                        float price = quote.getPrice().floatValue();
                        float change = quote.getChange().floatValue();
                        float percentChange = quote.getChangeInPercent().floatValue();
                        String lastTrade = quote.getLastTradeDateStr();
                        BigDecimal previousCloseDecimal =quote.getPreviousClose();
                        float previousClose = previousCloseDecimal!=null?previousCloseDecimal.floatValue():0.0f;
                        BigDecimal openDecimal = quote.getOpen();
                        float open = openDecimal!=null?openDecimal.floatValue():0.0f;
                        BigDecimal yearLowDecimal = quote.getYearLow();
                        float yearLow = yearLowDecimal!=null?yearLowDecimal.floatValue():0.0f;
                        BigDecimal yearHighDecimal =quote.getYearHigh();
                        float yearHigh = yearHighDecimal!=null?yearHighDecimal.floatValue():0.0f;
                        BigDecimal dayLowDecimal = quote.getDayLow();
                        float dayLow = dayLowDecimal!=null?dayLowDecimal.floatValue():0.0f;
                        BigDecimal dayHighDecimal =quote.getDayHigh();
                        float dayHigh = dayHighDecimal!=null?dayHighDecimal.floatValue():0.0f;
                        String name = stock.getName();
                        float market = stock.getStats().getMarketCap().floatValue();


                        // WARNING! Don't request historical data for a stock that doesn't exist!
                        // The request will hang forever X_x
                     List<HistoricalQuote> history = stock.getHistory(from, to, Interval.WEEKLY);
                        StringBuilder historyBuilder = new StringBuilder();

                        for (HistoricalQuote it : history) {
                            historyBuilder.append(it.getDate().getTimeInMillis());
                            historyBuilder.append(", ");
                            historyBuilder.append(it.getClose());
                            historyBuilder.append("\n");
                        }

                        ContentValues quoteCV = new ContentValues();
                        quoteCV.put(Contract.Quote.COLUMN_SYMBOL, symbol);
                        quoteCV.put(Contract.Quote.COLUMN_PRICE, price);
                        quoteCV.put(Contract.Quote.COLUMN_PERCENTAGE_CHANGE, percentChange);
                        quoteCV.put(Contract.Quote.COLUMN_ABSOLUTE_CHANGE, change);
                        quoteCV.put(Contract.Quote.COLUMN_HISTORY, historyBuilder.toString());
                        quoteCV.put(Contract.Quote.COLUMN_LAST_TRADE_DATE, lastTrade.toString());
                        quoteCV.put(Contract.Quote.COLUMN_OPEN, open);
                        quoteCV.put(Contract.Quote.COLUMN_PREVIOUS_CLOSE, previousClose);
                        quoteCV.put(Contract.Quote.COLUMN_DAY_LOW, dayLow);
                        quoteCV.put(Contract.Quote.COLUMN_DAY_HIGH, dayHigh);
                        quoteCV.put(Contract.Quote.COLUMN_YEAR_LOW, yearLow);
                        quoteCV.put(Contract.Quote.COLUMN_YEAR_HIGH, yearHigh);
                        quoteCV.put(Contract.Quote.COLUMN_SYMBOL_NAME, name);
                        quoteCV.put(Contract.Quote.COLUMN_MARKET_CAP, market);

                        quoteCVs.add(quoteCV);
                    }else{
                        Timber.i(symbol+"haven't a price");
                        PrefUtils.removeStock(context,symbol);
                    }
                }

                context.getContentResolver()
                        .bulkInsert(
                                Contract.Quote.URI,
                                quoteCVs.toArray(new ContentValues[quoteCVs.size()]));

                Intent dataUpdatedIntent = new Intent(ACTION_DATA_UPDATED);
                context.sendBroadcast(dataUpdatedIntent);
            }

            } catch(IOException exception){
                Timber.e(exception, "Error fetching stock quotes");
            }
        }


    private static void addQuote(Quote quote, Context context) {
        String symbol = quote.getSymbol();
        PrefUtils.addStock(context, symbol);


    }

    private static void schedulePeriodic(Context context) {
        Timber.d("Scheduling a periodic task");


        JobInfo.Builder builder = new JobInfo.Builder(PERIODIC_ID, new ComponentName(context, QuoteJobService.class));


        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(PERIOD)
                .setBackoffCriteria(INITIAL_BACKOFF, JobInfo.BACKOFF_POLICY_EXPONENTIAL);


        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        scheduler.schedule(builder.build());
    }


    public static synchronized void initialize(final Context context) {

        schedulePeriodic(context);
        syncImmediately(context);

    }

    public static synchronized void syncImmediately(Context context) {

        if (NetworkUtil.networkUp(context)) {
            Intent nowIntent = new Intent(context, QuoteIntentService.class);
            context.startService(nowIntent);
        } else {
            //construct the job
            JobInfo.Builder builder = new JobInfo.Builder(ONE_OFF_ID, new ComponentName(context, QuoteJobService.class));


            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setBackoffCriteria(INITIAL_BACKOFF, JobInfo.BACKOFF_POLICY_EXPONENTIAL);


            JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            scheduler.schedule(builder.build());


        }
    }


}
