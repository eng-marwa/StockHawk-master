package com.udacity.stockhawk.api;

import com.udacity.stockhawk.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**h
 * Created by MarwaTalaat on 3/11/2017.
 */


public interface StockService {

    @GET("yql")
    Call<Root> getStockDetails(@Query("q") String q, @Query("format") String format , @Query("env") String env);
}