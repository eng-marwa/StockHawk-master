package com.udacity.stockhawk.model;

/**
 * Created by MarwaTalaat on 3/24/2017.
 */
public class Query {
    private int count;

    private String created;

    private String lang;

    private Results results;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated() {
        return this.created;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return this.lang;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public Results getResults() {
        return this.results;
    }
}
