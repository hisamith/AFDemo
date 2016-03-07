package com.example.beans;

import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by samith on 3/3/16.
 */
public class DashboardDataHolder {

    private List<CountryData> countryDataList;
    private JSONObject mapDataJsonArray;

    public Stats getStats() {
        return stats;
    }

    private Stats stats;

    public List<CountryData> getCountryDataList() {
        return countryDataList;
    }

    public void setCountryDataList(List<CountryData> countryDataList) {
        this.countryDataList = countryDataList;
    }

    public JSONObject getMapDataJsonArray() {
        return mapDataJsonArray;
    }

    public void setMapDataJsonArray(JSONObject mapDataJsonArray) {
        this.mapDataJsonArray = mapDataJsonArray;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
