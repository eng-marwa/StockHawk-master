package com.udacity.stockhawk.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import yahoofinance.histquotes.HistoricalQuote;


/**
 * Created by MarwaTalaat on 3/24/2017.
 */

public class Quote implements Parcelable{

    private int _id;
    private String Ask;
    private String AverageDailyVolume;
    private String Bid;
    private String AskRealtime;
    private String BidRealtime;
    private String BookValue;
    private String Change_PercentChange;
    private String Change;
    private String Commission;
    private String Currency;
    private String ChangeRealtime;
    private String AfterHoursChangeRealtime;
    private String DividendShare;
    private String LastTradeDate;
    private String TradeDate;
    private String EarningsShare;
    private String ErrorIndicationreturnedforsymbolchangedinvalid;
    private String EPSEstimateCurrentYear;
    private String EPSEstimateNextYear;
    private String EPSEstimateNextQuarter;
    private String DaysLow;
    private String DaysHigh;
    private String YearLow;
    private String YearHigh;
    private String HoldingsGainPercent;

    private String AnnualizedGain;

    private String HoldingsGain;

    private String HoldingsGainPercentRealtime;

    private String HoldingsGainRealtime;

    private String MoreInfo;

    private String OrderBookRealtime;

    private String MarketCapRealtime;

    private String EBITDA;

    private String ChangeFromYearLow;

    private String PercentChangeFromYearLow;

    private String LastTradeRealtimeWithTime;

    private String ChangePercentRealtime;

    private String ChangeFromYearHigh;

    private String PercebtChangeFromYearHigh;

    private String LastTradeWithTime;

    private String LastTradePriceOnly;

    private String HighLimit;

    private String LowLimit;

    private String DaysRange;

    private String DaysRangeRealtime;

    private String FiftydayMovingAverage;

    private String TwoHundreddayMovingAverage;

    private String ChangeFromTwoHundreddayMovingAverage;

    private String PercentChangeFromTwoHundreddayMovingAverage;

    private String ChangeFromFiftydayMovingAverage;

    private String PercentChangeFromFiftydayMovingAverage;

    private String Name;

    private String Notes;

    private String Open;

    private String PreviousClose;

    private String PricePaid;

    private String ChangeinPercent;

    private String PriceSales;

    private String PriceBook;

    private String ExDividendDate;

    private String PERatio;

    private String DividendPayDate;

    private String PERatioRealtime;

    private String PEGRatio;

    private String PriceEPSEstimateCurrentYear;

    private String PriceEPSEstimateNextYear;

    private String symbol;

    private String SharesOwned;

    private String ShortRatio;

    private String LastTradeTime;

    private String TickerTrend;

    private String OneyrTargetPrice;

    private String Volume;

    private String HoldingsValue;

    private String HoldingsValueRealtime;

    private String YearRange;

    private String DaysValueChange;

    private String DaysValueChangeRealtime;

    private String StockExchange;

    private String DividendYield;

    private String PercentChange;
    private String history;
    private String MarketCapitalization;
    public Quote() {
    }

    public Quote(String symbol, String bid, String change_PercentChange, String change , String history ,String LastTradeDate ,String open ,String previousClose , String daysLow , String daysHigh , String yearLow ,String yearHigh, String name, String marketCapitalization) {
        this.symbol = symbol;
        Bid = bid;
        Change_PercentChange = change_PercentChange;
        Change = change;
        this.history = history;
        this.LastTradeDate=LastTradeDate;
        PreviousClose =previousClose;
        Open =open;
        this.DaysHigh = daysHigh;
        this.DaysLow = daysLow;
        this.YearLow = yearLow;
        this.YearHigh = yearHigh;
        this.Name = name;
        this.MarketCapitalization = marketCapitalization;
    }

    protected Quote(Parcel in) {
        symbol = in.readString();
        Ask = in.readString();
        AverageDailyVolume = in.readString();
        Bid = in.readString();
        AskRealtime = in.readString();
        BidRealtime = in.readString();
        BookValue = in.readString();
        Change_PercentChange = in.readString();
        Change = in.readString();
        Commission = in.readString();
        Currency = in.readString();
        ChangeRealtime = in.readString();
        AfterHoursChangeRealtime = in.readString();
        DividendShare = in.readString();
        LastTradeDate = in.readString();
        TradeDate = in.readString();
        EarningsShare = in.readString();
        ErrorIndicationreturnedforsymbolchangedinvalid = in.readString();
        EPSEstimateCurrentYear = in.readString();
        EPSEstimateNextYear = in.readString();
        EPSEstimateNextQuarter = in.readString();
        DaysLow = in.readString();
        DaysHigh = in.readString();
        YearLow = in.readString();
        YearHigh = in.readString();
        HoldingsGainPercent = in.readString();
        AnnualizedGain = in.readString();
        HoldingsGain = in.readString();
        HoldingsGainPercentRealtime = in.readString();
        HoldingsGainRealtime = in.readString();
        MoreInfo = in.readString();
        OrderBookRealtime = in.readString();
        MarketCapitalization = in.readString();
        MarketCapRealtime = in.readString();
        EBITDA = in.readString();
        ChangeFromYearLow = in.readString();
        PercentChangeFromYearLow = in.readString();
        LastTradeRealtimeWithTime = in.readString();
        ChangePercentRealtime = in.readString();
        ChangeFromYearHigh = in.readString();
        PercebtChangeFromYearHigh = in.readString();
        LastTradeWithTime = in.readString();
        LastTradePriceOnly = in.readString();
        HighLimit = in.readString();
        LowLimit = in.readString();
        DaysRange = in.readString();
        DaysRangeRealtime = in.readString();
        FiftydayMovingAverage = in.readString();
        TwoHundreddayMovingAverage = in.readString();
        ChangeFromTwoHundreddayMovingAverage = in.readString();
        PercentChangeFromTwoHundreddayMovingAverage = in.readString();
        ChangeFromFiftydayMovingAverage = in.readString();
        PercentChangeFromFiftydayMovingAverage = in.readString();
        Name = in.readString();
        history=in.readString();
        Notes = in.readString();
        Open = in.readString();
        PreviousClose = in.readString();
        PricePaid = in.readString();
        ChangeinPercent = in.readString();
        PriceSales = in.readString();
        PriceBook = in.readString();
        ExDividendDate = in.readString();
        PERatio = in.readString();
        DividendPayDate = in.readString();
        PERatioRealtime = in.readString();
        PEGRatio = in.readString();
        PriceEPSEstimateCurrentYear = in.readString();
        PriceEPSEstimateNextYear = in.readString();
        symbol = in.readString();
        SharesOwned = in.readString();
        ShortRatio = in.readString();
        LastTradeTime = in.readString();
        TickerTrend = in.readString();
        OneyrTargetPrice = in.readString();
        Volume = in.readString();
        HoldingsValue = in.readString();
        HoldingsValueRealtime = in.readString();
        YearRange = in.readString();
        DaysValueChange = in.readString();
        DaysValueChangeRealtime = in.readString();
        StockExchange = in.readString();
        DividendYield = in.readString();
        PercentChange = in.readString();
    }

    public static final Creator<Quote> CREATOR = new Creator<Quote>() {
        @Override
        public Quote createFromParcel(Parcel in) {
            return new Quote(in);
        }

        @Override
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };

    public void setAsk(String Ask) {
        this.Ask = Ask;
    }

    public String getAsk() {
        return this.Ask;
    }

    public void setAverageDailyVolume(String AverageDailyVolume) {
        this.AverageDailyVolume = AverageDailyVolume;
    }

    public String getAverageDailyVolume() {
        return this.AverageDailyVolume;
    }

    public void setBid(String Bid) {
        this.Bid = Bid;
    }

    public String getBid() {
        return this.Bid;
    }

    public void setAskRealtime(String AskRealtime) {
        this.AskRealtime = AskRealtime;
    }

    public String getAskRealtime() {
        return this.AskRealtime;
    }

    public void setBidRealtime(String BidRealtime) {
        this.BidRealtime = BidRealtime;
    }

    public String getBidRealtime() {
        return this.BidRealtime;
    }

    public void setBookValue(String BookValue) {
        this.BookValue = BookValue;
    }

    public String getBookValue() {
        return this.BookValue;
    }

    public void setChange_PercentChange(String Change_PercentChange) {
        this.Change_PercentChange = Change_PercentChange;
    }

    public String getChange_PercentChange() {
        return this.Change_PercentChange;
    }

    public void setChange(String Change) {
        this.Change = Change;
    }

    public String getChange() {
        return this.Change;
    }

    public void setCommission(String Commission) {
        this.Commission = Commission;
    }

    public String getCommission() {
        return this.Commission;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    public String getCurrency() {
        return this.Currency;
    }

    public void setChangeRealtime(String ChangeRealtime) {
        this.ChangeRealtime = ChangeRealtime;
    }

    public String getChangeRealtime() {
        return this.ChangeRealtime;
    }

    public void setAfterHoursChangeRealtime(String AfterHoursChangeRealtime) {
        this.AfterHoursChangeRealtime = AfterHoursChangeRealtime;
    }

    public String getAfterHoursChangeRealtime() {
        return this.AfterHoursChangeRealtime;
    }

    public void setDividendShare(String DividendShare) {
        this.DividendShare = DividendShare;
    }

    public String getDividendShare() {
        return this.DividendShare;
    }

    public void setLastTradeDate(String LastTradeDate) {
        this.LastTradeDate = LastTradeDate;
    }

    public String getLastTradeDate() {
        return this.LastTradeDate;
    }

    public void setTradeDate(String TradeDate) {
        this.TradeDate = TradeDate;
    }

    public String getTradeDate() {
        return this.TradeDate;
    }

    public void setEarningsShare(String EarningsShare) {
        this.EarningsShare = EarningsShare;
    }

    public String getEarningsShare() {
        return this.EarningsShare;
    }

    public void setErrorIndicationreturnedforsymbolchangedinvalid(String ErrorIndicationreturnedforsymbolchangedinvalid) {
        this.ErrorIndicationreturnedforsymbolchangedinvalid = ErrorIndicationreturnedforsymbolchangedinvalid;
    }

    public String getErrorIndicationreturnedforsymbolchangedinvalid() {
        return this.ErrorIndicationreturnedforsymbolchangedinvalid;
    }

    public void setEPSEstimateCurrentYear(String EPSEstimateCurrentYear) {
        this.EPSEstimateCurrentYear = EPSEstimateCurrentYear;
    }

    public String getEPSEstimateCurrentYear() {
        return this.EPSEstimateCurrentYear;
    }

    public void setEPSEstimateNextYear(String EPSEstimateNextYear) {
        this.EPSEstimateNextYear = EPSEstimateNextYear;
    }

    public String getEPSEstimateNextYear() {
        return this.EPSEstimateNextYear;
    }

    public void setEPSEstimateNextQuarter(String EPSEstimateNextQuarter) {
        this.EPSEstimateNextQuarter = EPSEstimateNextQuarter;
    }

    public String getEPSEstimateNextQuarter() {
        return this.EPSEstimateNextQuarter;
    }

    public void setDaysLow(String DaysLow) {
        this.DaysLow = DaysLow;
    }

    public String getDaysLow() {
        return this.DaysLow;
    }

    public void setDaysHigh(String DaysHigh) {
        this.DaysHigh = DaysHigh;
    }

    public String getDaysHigh() {
        return this.DaysHigh;
    }

    public void setYearLow(String YearLow) {
        this.YearLow = YearLow;
    }

    public String getYearLow() {
        return this.YearLow;
    }

    public void setYearHigh(String YearHigh) {
        this.YearHigh = YearHigh;
    }

    public String getYearHigh() {
        return this.YearHigh;
    }

    public void setHoldingsGainPercent(String HoldingsGainPercent) {
        this.HoldingsGainPercent = HoldingsGainPercent;
    }

    public String getHoldingsGainPercent() {
        return this.HoldingsGainPercent;
    }

    public void setAnnualizedGain(String AnnualizedGain) {
        this.AnnualizedGain = AnnualizedGain;
    }

    public String getAnnualizedGain() {
        return this.AnnualizedGain;
    }

    public void setHoldingsGain(String HoldingsGain) {
        this.HoldingsGain = HoldingsGain;
    }

    public String getHoldingsGain() {
        return this.HoldingsGain;
    }

    public void setHoldingsGainPercentRealtime(String HoldingsGainPercentRealtime) {
        this.HoldingsGainPercentRealtime = HoldingsGainPercentRealtime;
    }

    public String getHoldingsGainPercentRealtime() {
        return this.HoldingsGainPercentRealtime;
    }

    public void setHoldingsGainRealtime(String HoldingsGainRealtime) {
        this.HoldingsGainRealtime = HoldingsGainRealtime;
    }

    public String getHoldingsGainRealtime() {
        return this.HoldingsGainRealtime;
    }

    public void setMoreInfo(String MoreInfo) {
        this.MoreInfo = MoreInfo;
    }

    public String getMoreInfo() {
        return this.MoreInfo;
    }

    public void setOrderBookRealtime(String OrderBookRealtime) {
        this.OrderBookRealtime = OrderBookRealtime;
    }

    public String getOrderBookRealtime() {
        return this.OrderBookRealtime;
    }

    public void setMarketCapitalization(String MarketCapitalization) {
        this.MarketCapitalization = MarketCapitalization;
    }

    public String getMarketCapitalization() {
        return this.MarketCapitalization;
    }

    public void setMarketCapRealtime(String MarketCapRealtime) {
        this.MarketCapRealtime = MarketCapRealtime;
    }

    public String getMarketCapRealtime() {
        return this.MarketCapRealtime;
    }

    public void setEBITDA(String EBITDA) {
        this.EBITDA = EBITDA;
    }

    public String getEBITDA() {
        return this.EBITDA;
    }

    public void setChangeFromYearLow(String ChangeFromYearLow) {
        this.ChangeFromYearLow = ChangeFromYearLow;
    }

    public String getChangeFromYearLow() {
        return this.ChangeFromYearLow;
    }

    public void setPercentChangeFromYearLow(String PercentChangeFromYearLow) {
        this.PercentChangeFromYearLow = PercentChangeFromYearLow;
    }

    public String getPercentChangeFromYearLow() {
        return this.PercentChangeFromYearLow;
    }

    public void setLastTradeRealtimeWithTime(String LastTradeRealtimeWithTime) {
        this.LastTradeRealtimeWithTime = LastTradeRealtimeWithTime;
    }

    public String getLastTradeRealtimeWithTime() {
        return this.LastTradeRealtimeWithTime;
    }

    public void setChangePercentRealtime(String ChangePercentRealtime) {
        this.ChangePercentRealtime = ChangePercentRealtime;
    }

    public String getChangePercentRealtime() {
        return this.ChangePercentRealtime;
    }

    public void setChangeFromYearHigh(String ChangeFromYearHigh) {
        this.ChangeFromYearHigh = ChangeFromYearHigh;
    }

    public String getChangeFromYearHigh() {
        return this.ChangeFromYearHigh;
    }

    public void setPercebtChangeFromYearHigh(String PercebtChangeFromYearHigh) {
        this.PercebtChangeFromYearHigh = PercebtChangeFromYearHigh;
    }

    public String getPercebtChangeFromYearHigh() {
        return this.PercebtChangeFromYearHigh;
    }

    public void setLastTradeWithTime(String LastTradeWithTime) {
        this.LastTradeWithTime = LastTradeWithTime;
    }

    public String getLastTradeWithTime() {
        return this.LastTradeWithTime;
    }

    public void setLastTradePriceOnly(String LastTradePriceOnly) {
        this.LastTradePriceOnly = LastTradePriceOnly;
    }

    public String getLastTradePriceOnly() {
        return this.LastTradePriceOnly;
    }

    public void setHighLimit(String HighLimit) {
        this.HighLimit = HighLimit;
    }

    public String getHighLimit() {
        return this.HighLimit;
    }

    public void setLowLimit(String LowLimit) {
        this.LowLimit = LowLimit;
    }

    public String getLowLimit() {
        return this.LowLimit;
    }

    public void setDaysRange(String DaysRange) {
        this.DaysRange = DaysRange;
    }

    public String getDaysRange() {
        return this.DaysRange;
    }

    public void setDaysRangeRealtime(String DaysRangeRealtime) {
        this.DaysRangeRealtime = DaysRangeRealtime;
    }

    public String getDaysRangeRealtime() {
        return this.DaysRangeRealtime;
    }

    public void setFiftydayMovingAverage(String FiftydayMovingAverage) {
        this.FiftydayMovingAverage = FiftydayMovingAverage;
    }

    public String getFiftydayMovingAverage() {
        return this.FiftydayMovingAverage;
    }

    public void setTwoHundreddayMovingAverage(String TwoHundreddayMovingAverage) {
        this.TwoHundreddayMovingAverage = TwoHundreddayMovingAverage;
    }

    public String getTwoHundreddayMovingAverage() {
        return this.TwoHundreddayMovingAverage;
    }

    public void setChangeFromTwoHundreddayMovingAverage(String ChangeFromTwoHundreddayMovingAverage) {
        this.ChangeFromTwoHundreddayMovingAverage = ChangeFromTwoHundreddayMovingAverage;
    }

    public String getChangeFromTwoHundreddayMovingAverage() {
        return this.ChangeFromTwoHundreddayMovingAverage;
    }

    public void setPercentChangeFromTwoHundreddayMovingAverage(String PercentChangeFromTwoHundreddayMovingAverage) {
        this.PercentChangeFromTwoHundreddayMovingAverage = PercentChangeFromTwoHundreddayMovingAverage;
    }

    public String getPercentChangeFromTwoHundreddayMovingAverage() {
        return this.PercentChangeFromTwoHundreddayMovingAverage;
    }

    public void setChangeFromFiftydayMovingAverage(String ChangeFromFiftydayMovingAverage) {
        this.ChangeFromFiftydayMovingAverage = ChangeFromFiftydayMovingAverage;
    }

    public String getChangeFromFiftydayMovingAverage() {
        return this.ChangeFromFiftydayMovingAverage;
    }

    public void setPercentChangeFromFiftydayMovingAverage(String PercentChangeFromFiftydayMovingAverage) {
        this.PercentChangeFromFiftydayMovingAverage = PercentChangeFromFiftydayMovingAverage;
    }

    public String getPercentChangeFromFiftydayMovingAverage() {
        return this.PercentChangeFromFiftydayMovingAverage;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return this.Name;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public String getNotes() {
        return this.Notes;
    }

    public void setOpen(String Open) {
        this.Open = Open;
    }

    public String getOpen() {
        return this.Open;
    }

    public void setPreviousClose(String PreviousClose) {
        this.PreviousClose = PreviousClose;
    }

    public String getPreviousClose() {
        return this.PreviousClose;
    }

    public void setPricePaid(String PricePaid) {
        this.PricePaid = PricePaid;
    }

    public String getPricePaid() {
        return this.PricePaid;
    }

    public void setChangeinPercent(String ChangeinPercent) {
        this.ChangeinPercent = ChangeinPercent;
    }

    public String getChangeinPercent() {
        return this.ChangeinPercent;
    }

    public void setPriceSales(String PriceSales) {
        this.PriceSales = PriceSales;
    }

    public String getPriceSales() {
        return this.PriceSales;
    }

    public void setPriceBook(String PriceBook) {
        this.PriceBook = PriceBook;
    }

    public String getPriceBook() {
        return this.PriceBook;
    }

    public void setExDividendDate(String ExDividendDate) {
        this.ExDividendDate = ExDividendDate;
    }

    public String getExDividendDate() {
        return this.ExDividendDate;
    }

    public void setPERatio(String PERatio) {
        this.PERatio = PERatio;
    }

    public String getPERatio() {
        return this.PERatio;
    }

    public void setDividendPayDate(String DividendPayDate) {
        this.DividendPayDate = DividendPayDate;
    }

    public String getDividendPayDate() {
        return this.DividendPayDate;
    }

    public void setPERatioRealtime(String PERatioRealtime) {
        this.PERatioRealtime = PERatioRealtime;
    }

    public String getPERatioRealtime() {
        return this.PERatioRealtime;
    }

    public void setPEGRatio(String PEGRatio) {
        this.PEGRatio = PEGRatio;
    }

    public String getPEGRatio() {
        return this.PEGRatio;
    }

    public void setPriceEPSEstimateCurrentYear(String PriceEPSEstimateCurrentYear) {
        this.PriceEPSEstimateCurrentYear = PriceEPSEstimateCurrentYear;
    }

    public String getPriceEPSEstimateCurrentYear() {
        return this.PriceEPSEstimateCurrentYear;
    }

    public void setPriceEPSEstimateNextYear(String PriceEPSEstimateNextYear) {
        this.PriceEPSEstimateNextYear = PriceEPSEstimateNextYear;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getPriceEPSEstimateNextYear() {
        return this.PriceEPSEstimateNextYear;
    }

    public void setSymbol(String Symbol) {
        this.symbol = Symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSharesOwned(String SharesOwned) {
        this.SharesOwned = SharesOwned;
    }

    public String getSharesOwned() {
        return this.SharesOwned;
    }

    public void setShortRatio(String ShortRatio) {
        this.ShortRatio = ShortRatio;
    }

    public String getShortRatio() {
        return this.ShortRatio;
    }

    public void setLastTradeTime(String LastTradeTime) {
        this.LastTradeTime = LastTradeTime;
    }

    public String getLastTradeTime() {
        return this.LastTradeTime;
    }

    public void setTickerTrend(String TickerTrend) {
        this.TickerTrend = TickerTrend;
    }

    public String getTickerTrend() {
        return this.TickerTrend;
    }

    public void setOneyrTargetPrice(String OneyrTargetPrice) {
        this.OneyrTargetPrice = OneyrTargetPrice;
    }

    public String getOneyrTargetPrice() {
        return this.OneyrTargetPrice;
    }

    public void setVolume(String Volume) {
        this.Volume = Volume;
    }

    public String getVolume() {
        return this.Volume;
    }

    public void setHoldingsValue(String HoldingsValue) {
        this.HoldingsValue = HoldingsValue;
    }

    public String getHoldingsValue() {
        return this.HoldingsValue;
    }

    public void setHoldingsValueRealtime(String HoldingsValueRealtime) {
        this.HoldingsValueRealtime = HoldingsValueRealtime;
    }

    public String getHoldingsValueRealtime() {
        return this.HoldingsValueRealtime;
    }

    public void setYearRange(String YearRange) {
        this.YearRange = YearRange;
    }

    public String getYearRange() {
        return this.YearRange;
    }

    public void setDaysValueChange(String DaysValueChange) {
        this.DaysValueChange = DaysValueChange;
    }

    public String getDaysValueChange() {
        return this.DaysValueChange;
    }

    public void setDaysValueChangeRealtime(String DaysValueChangeRealtime) {
        this.DaysValueChangeRealtime = DaysValueChangeRealtime;
    }

    public String getDaysValueChangeRealtime() {
        return this.DaysValueChangeRealtime;
    }

    public void setStockExchange(String StockExchange) {
        this.StockExchange = StockExchange;
    }

    public String getStockExchange() {
        return this.StockExchange;
    }

    public void setDividendYield(String DividendYield) {
        this.DividendYield = DividendYield;
    }

    public String getDividendYield() {
        return this.DividendYield;
    }

    public void setPercentChange(String PercentChange) {
        this.PercentChange = PercentChange;
    }

    public String getPercentChange() {
        return this.PercentChange;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeString(Ask);
        dest.writeString(AverageDailyVolume);
        dest.writeString(Bid);
        dest.writeString(AskRealtime);
        dest.writeString(BidRealtime);
        dest.writeString(BookValue);
        dest.writeString(Change_PercentChange);
        dest.writeString(Change);
        dest.writeString(Commission);
        dest.writeString(Currency);
        dest.writeString(ChangeRealtime);
        dest.writeString(AfterHoursChangeRealtime);
        dest.writeString(DividendShare);
        dest.writeString(LastTradeDate);
        dest.writeString(TradeDate);
        dest.writeString(EarningsShare);
        dest.writeString(ErrorIndicationreturnedforsymbolchangedinvalid);
        dest.writeString(EPSEstimateCurrentYear);
        dest.writeString(EPSEstimateNextYear);
        dest.writeString(EPSEstimateNextQuarter);
        dest.writeString(DaysLow);
        dest.writeString(DaysHigh);
        dest.writeString(YearLow);
        dest.writeString(YearHigh);
        dest.writeString(HoldingsGainPercent);
        dest.writeString(AnnualizedGain);
        dest.writeString(HoldingsGain);
        dest.writeString(HoldingsGainPercentRealtime);
        dest.writeString(HoldingsGainRealtime);
        dest.writeString(MoreInfo);
        dest.writeString(OrderBookRealtime);
        dest.writeString(MarketCapitalization);
        dest.writeString(MarketCapRealtime);
        dest.writeString(EBITDA);
        dest.writeString(ChangeFromYearLow);
        dest.writeString(PercentChangeFromYearLow);
        dest.writeString(LastTradeRealtimeWithTime);
        dest.writeString(ChangePercentRealtime);
        dest.writeString(ChangeFromYearHigh);
        dest.writeString(PercebtChangeFromYearHigh);
        dest.writeString(LastTradeWithTime);
        dest.writeString(LastTradePriceOnly);
        dest.writeString(HighLimit);
        dest.writeString(LowLimit);
        dest.writeString(DaysRange);
        dest.writeString(DaysRangeRealtime);
        dest.writeString(FiftydayMovingAverage);
        dest.writeString(TwoHundreddayMovingAverage);
        dest.writeString(ChangeFromTwoHundreddayMovingAverage);
        dest.writeString(PercentChangeFromTwoHundreddayMovingAverage);
        dest.writeString(ChangeFromFiftydayMovingAverage);
        dest.writeString(PercentChangeFromFiftydayMovingAverage);
        dest.writeString(Name);
        dest.writeString(history);
        dest.writeString(Notes);
        dest.writeString(Open);
        dest.writeString(PreviousClose);
        dest.writeString(PricePaid);
        dest.writeString(ChangeinPercent);
        dest.writeString(PriceSales);
        dest.writeString(PriceBook);
        dest.writeString(ExDividendDate);
        dest.writeString(PERatio);
        dest.writeString(DividendPayDate);
        dest.writeString(PERatioRealtime);
        dest.writeString(PEGRatio);
        dest.writeString(PriceEPSEstimateCurrentYear);
        dest.writeString(PriceEPSEstimateNextYear);
        dest.writeString(symbol);
        dest.writeString(SharesOwned);
        dest.writeString(ShortRatio);
        dest.writeString(LastTradeTime);
        dest.writeString(TickerTrend);
        dest.writeString(OneyrTargetPrice);
        dest.writeString(Volume);
        dest.writeString(HoldingsValue);
        dest.writeString(HoldingsValueRealtime);
        dest.writeString(YearRange);
        dest.writeString(DaysValueChange);
        dest.writeString(DaysValueChangeRealtime);
        dest.writeString(StockExchange);
        dest.writeString(DividendYield);
        dest.writeString(PercentChange);
    }
}
