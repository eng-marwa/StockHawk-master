package com.udacity.stockhawk.model;

import com.udacity.stockhawk.model.Query;

public class Root
{
    private Query query;

    public void setQuery(Query query){
        this.query = query;
    }
    public Query getQuery(){
        return this.query;
    }
}
