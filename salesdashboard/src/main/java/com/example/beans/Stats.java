package com.example.beans;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by samith on 3/2/16.
 */
public class Stats {
    public int getTotalLeads() {
        return totalLeads;
    }

    public void setTotalLeads(int totalLeads) {
        this.totalLeads = totalLeads;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getNewCustomers() {
        return newCustomers;
    }

    public void setNewCustomers(int newCustomers) {
        this.newCustomers = newCustomers;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    private int totalLeads;
    private int totalSales;
    private int newCustomers;
    private double stock;

    public JSONObject getGlobalMarketShare() {
        return globalMarketShare;
    }

    public void setGlobalMarketShare(JSONObject globalMarketShare) {
        this.globalMarketShare = globalMarketShare;
    }

    public JSONArray getMonthlySalesData() {
        return monthlySalesData;
    }

    public void setMonthlySalesData(JSONArray monthlySalesData) {
        this.monthlySalesData = monthlySalesData;
    }

    private JSONObject globalMarketShare;
    private JSONArray monthlySalesData;


}
