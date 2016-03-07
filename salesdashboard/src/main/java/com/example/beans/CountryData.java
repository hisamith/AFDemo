package com.example.beans;

public class CountryData {
    private String name;
    private String code;
    private int targetSales;
    private int currentSales;
    private int marketShare;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTargetSales() {
        return targetSales;
    }

    public void setTargetSales(int targetSales) {
        this.targetSales = targetSales;
    }

    public int getCurrentSales() {
        return currentSales;
    }

    public void setCurrentSales(int currentSales) {
        this.currentSales = currentSales;
    }

    public int getMarketShare() {
        return marketShare;
    }

    public void setMarketShare(int marketShare) {
        this.marketShare = marketShare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
