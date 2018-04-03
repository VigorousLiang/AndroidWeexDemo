package com.vigorous.weexdemo.model;

import com.google.gson.annotations.SerializedName;
import com.vigorous.weexdemo.model.base.BaseResp;

/**
 * Created by vigorousliang
 * Created on 2018/3/13
 */

/**
 * 股票资金
 */
public class TotalAssets {
    @SerializedName("1017")
    //资金账号
    private String accountNumber;
    @SerializedName("1187")
    //银行名称
    private String bankName;
    @SerializedName("1087")
    //总资产
    private String totalAssets;
    @SerializedName("1028")
    //币种
    private String currency;
    @SerializedName("1077")
    //资金余额
    private String fundBalance;
    @SerializedName("1716")
    private String repurchaseAvailableFund;
    @SerializedName("1065")
    private String lastestMarketValue;
    @SerializedName("1413")
    private String mainAccountType;
    @SerializedName("pendingSecAssets")
    private String pendingSecAssets;

    @SerializedName("1078")
    private String availableFund;
    @SerializedName("1079")
    private String withdrawash;
    @SerializedName("1064")
    private String profitOrLossAmount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(String totalAssets) {
        this.totalAssets = totalAssets;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFundBalance() {
        return fundBalance;
    }

    public void setFundBalance(String fundBalance) {
        this.fundBalance = fundBalance;
    }

    public String getRepurchaseAvailableFund() {
        return repurchaseAvailableFund;
    }

    public void setRepurchaseAvailableFund(String repurchaseAvailableFund) {
        this.repurchaseAvailableFund = repurchaseAvailableFund;
    }

    public String getLastestMarketValue() {
        return lastestMarketValue;
    }

    public void setLastestMarketValue(String lastestMarketValue) {
        this.lastestMarketValue = lastestMarketValue;
    }

    public String getMainAccountType() {
        return mainAccountType;
    }

    public void setMainAccountType(String mainAccountType) {
        this.mainAccountType = mainAccountType;
    }

    public String getPendingSecAssets() {
        return pendingSecAssets;
    }

    public void setPendingSecAssets(String pendingSecAssets) {
        this.pendingSecAssets = pendingSecAssets;
    }

    public String getAvailableFund() {
        return availableFund;
    }

    public void setAvailableFund(String availableFund) {
        this.availableFund = availableFund;
    }

    public String getWithdrawash() {
        return withdrawash;
    }

    public void setWithdrawash(String withdrawash) {
        this.withdrawash = withdrawash;
    }

    public String getProfitOrLossAmount() {
        return profitOrLossAmount;
    }

    public void setProfitOrLossAmount(String profitOrLossAmount) {
        this.profitOrLossAmount = profitOrLossAmount;
    }
}
