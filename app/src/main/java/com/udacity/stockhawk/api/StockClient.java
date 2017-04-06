package com.udacity.stockhawk.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MarwaTalaat on 3/24/2017.
 */

public class StockClient {
    public static Retrofit setRetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://query.yahooapis.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
