package com.vigorous.weexdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vigorousliang
 * Created on 2018/3/14
 */

public class StocksAssets {
    @SerializedName("1021")
    //账号类型
    private String accountType;

    @SerializedName("1064")
    private String profitOrLossAmount;

    @SerializedName("1060")
    private String stockAmount;

    @SerializedName("1461")
    private String stocksBalance;
    //币种
    @SerializedName("1028")
    private String currency;

    @SerializedName("1004")
    private String stocksExchange;

    @SerializedName("1596")
    private String buyAverageCost;

    @SerializedName("1062")
    private String cost;

    @SerializedName("1181")
    private String currentPrice;

    @SerializedName("1037")
    private String stockName;

    @SerializedName("1036")
    private String stockCode;

    @SerializedName("6001")
    private String stockType;

    @SerializedName("1320")
    private String profitOrLossRatio;

    @SerializedName("1065")
    private String latestMarketPrice;

    @SerializedName("1061")
    private String marketableStockAmount;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getProfitOrLossAmount() {
        return profitOrLossAmount;
    }

    public void setProfitOrLossAmount(String profitOrLossAmount) {
        this.profitOrLossAmount = profitOrLossAmount;
    }

    public String getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(String stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getStocksBalance() {
        return stocksBalance;
    }

    public void setStocksBalance(String stocksBalance) {
        this.stocksBalance = stocksBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStocksExchange() {
        return stocksExchange;
    }

    public void setStocksExchange(String stocksExchange) {
        this.stocksExchange = stocksExchange;
    }

    public String getBuyAverageCost() {
        return buyAverageCost;
    }

    public void setBuyAverageCost(String buyAverageCost) {
        this.buyAverageCost = buyAverageCost;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getProfitOrLossRatio() {
        return profitOrLossRatio;
    }

    public void setProfitOrLossRatio(String profitOrLossRatio) {
        this.profitOrLossRatio = profitOrLossRatio;
    }

    public String getLatestMarketPrice() {
        return latestMarketPrice;
    }

    public void setLatestMarketPrice(String latestMarketPrice) {
        this.latestMarketPrice = latestMarketPrice;
    }

    public String getMarketableStockAmount() {
        return marketableStockAmount;
    }

    public void setMarketableStockAmount(String marketableStockAmount) {
        this.marketableStockAmount = marketableStockAmount;
    }
}
