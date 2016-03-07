package com.example.restclient;

import com.example.beans.CountryData;
import com.example.beans.DashboardDataHolder;
import com.example.beans.Stats;
import com.example.util.ConfigUtil;
import com.example.util.Constants;
import com.example.util.Util;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

;

public class RestClient {

    private static Log log = LogFactory.getLog(RestClient.class);
    private static String tokenUrl;

    static {
        try {
            tokenUrl = Util.getResourceFromRegistry(Constants.TOKEN_URL_PROP_KEY);
        } catch (Exception e) {
            log.error("Error while getting "+Constants.TOKEN_URL_PROP_KEY+ " from registry" ,e);
            log.info("Using default access token : "+  Constants.DEFAULT_TOKEN_URL);
            tokenUrl = Constants.DEFAULT_TOKEN_URL;
        }
    }


    public DashboardDataHolder getData() throws Exception {
        DashboardDataHolder dashboardDataHolder = getRegionalData();
        Stats stats = getStats();
        dashboardDataHolder.setStats(stats);
        return dashboardDataHolder;
    }

    public DashboardDataHolder getRegionalData() throws Exception {

        String regionalDataApi = Util.getResourceFromRegistry(Constants.SALES_DATA_API_PROP_KEY) + "/" +
                                 ConfigUtil.getPropertyValue(Constants.SUBSCRIBED_API_RESOURCE_PATH);
        String consumerKey = Util.getResourceFromRegistry(Constants.SUBSCRIBED_API_CONSUMER_KEY_PROP_KEY);
        String consumerSecret = Util.getResourceFromRegistry(Constants.SUBSCRIBED_API_CONSUMER_SECRET_PROP_KEY);
        String accessToken = getAccessToken(tokenUrl, consumerKey, consumerSecret);
        GetMethod getMethod = getHTTPGetMethod(regionalDataApi, accessToken);
        List<CountryData> countryDataList = new ArrayList<>();
        JSONObject countryMapData = new JSONObject();
        DashboardDataHolder dashboardDataHolder;
        try {
            log.info(" Calling sales api : "+ regionalDataApi);
            int httpStatusCode = executeMethod(getMethod);
            if (HttpStatus.SC_OK == httpStatusCode) {
                Object obj = getresponseJSONObject(getMethod);
                JSONArray jsonCountryDataArray = (JSONArray) obj;

                log.info("Regional Data Api response: " + jsonCountryDataArray);
                // Creating json object array to display the map data
                for (Object jsonObject : jsonCountryDataArray) {
                    JSONObject countryObj = (JSONObject) jsonObject;

                    // Setting CountryData to countryDataList. This will be used to draw the table in the dashboard
                    CountryData countryData = new CountryData();
                    countryData.setName((String) countryObj.get("name"));
                    countryData.setCode((String) countryObj.get("code"));
                    countryData.setTargetSales((int)(long) countryObj.get("targetSales"));
                    countryData.setCurrentSales((int)(long) countryObj.get("currentSales"));
                    countryData.setMarketShare((int)(long) countryObj.get("marketShare"));
                    countryDataList.add(countryData);
                    countryMapData.put(countryObj.get("code"), (int)(long) countryObj.get("marketShare"));
                }
                dashboardDataHolder = new DashboardDataHolder();
                dashboardDataHolder.setCountryDataList(countryDataList);
                dashboardDataHolder.setMapDataJsonArray(countryMapData);
            } else {
                throw new Exception("Error while getting regional data from api : " + regionalDataApi);
            }
        } catch (Exception e) {
            throw new Exception("Error while getting regional data from api : " + regionalDataApi ,e);
        }
        return dashboardDataHolder;
    }

    public Stats getStats() throws Exception {
        String statsApi = Util.getExternalApiValue(Constants.EXT_API_URL_PROP_KEY) + "/"
                          + ConfigUtil.getPropertyValue(Constants.CONFIG_EXTERNAL_API_RESOURCE_PATH);
        String consumerKey = Util.getExternalApiValue(Constants.EXT_API_CONSUMER_KEY_PROP_KEY);
        String consumerSecret = Util.getExternalApiValue(Constants.EXT_API_CONSUMER_SECRET_PROP_KEY);
        String accessToken = getAccessToken(tokenUrl, consumerKey, consumerSecret);
        GetMethod getMethod = getHTTPGetMethod(statsApi, accessToken);
        Stats stats;
        try {
            log.info(" Calling stats api : "+ statsApi);
            int httpStatusCode = executeMethod(getMethod);
            if (HttpStatus.SC_OK == httpStatusCode) {
                Object obj = getresponseJSONObject(getMethod);
                JSONObject statObj = (JSONObject) obj;
                stats = new Stats();
                stats.setTotalLeads((int)(double) statObj.get("totalLeads"));
                stats.setNewCustomers((int)(double) statObj.get("newCustomers"));
                stats.setTotalSales((int)(double) statObj.get("totalSales"));
                stats.setStock((double) statObj.get("stock"));
                stats.setMonthlySalesData(((JSONArray) statObj.get("monthlySalesData")));
                log.info(statObj.get("monthlySalesData"));
                stats.setGlobalMarketShare(((JSONObject) statObj.get("globalMarketShare")));
                log.info(statObj.get("globalMarketShare"));
            } else {
                throw new Exception("Error while getting stats data from api : " + statsApi);
            }
        } catch (Exception e) {
            throw new Exception("Error while getting stats data from api : " + statsApi ,e);
        }
        return stats;
    }

    public String getAccessToken(String tokenUrl , String consumerKey , String consumerSecret) throws Exception {
        log.info("CK : " + consumerKey +" , CS : "+consumerSecret);
        String bearerToken = consumerKey + ":" + consumerSecret;
        BASE64Encoder base64Encoder = new BASE64Encoder();
        bearerToken = "Bearer " + base64Encoder.encode(bearerToken.getBytes()).trim();
        log.info("bearerToken after encoding: " + bearerToken);
        PostMethod postMethod = getHTTPPostMethod(tokenUrl, bearerToken);
        try {
            int httpStatusCode = executeMethod(postMethod);
            log.info("Http status code for token endpoint: " + httpStatusCode);
            if (HttpStatus.SC_OK == httpStatusCode) {
                String accessTokenJson = postMethod.getResponseBodyAsString();
                log.info("Access token response : " + accessTokenJson);
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(accessTokenJson);
                JSONObject jsonObject = (JSONObject) obj;
                String accessToken = (String) jsonObject.get("access_token");
                log.info("Access Token : " + accessToken);
                return accessToken;
            } else {
                String errMag = "Error while getting access token for CK : " + consumerKey +" , CS : "+consumerSecret + " , " +
                                "tokenURL: "+tokenUrl +". HTTP Status Code : " + httpStatusCode;
                log.info(errMag);
                throw new Exception(errMag);
            }
        } catch (IOException e) {
            log.error("Error while getting access token for CK : " + consumerKey +" , CS : "+consumerSecret + " , " +
                      "tokenURL: "+tokenUrl , e);
            throw new Exception(e);
        }

    }

    private PostMethod getHTTPPostMethod(String tokenUrl, String bearerToken) {
        PostMethod postMethod = new PostMethod(tokenUrl);
        postMethod.addRequestHeader("Authorization", bearerToken);
        postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        postMethod.addParameter("grant_type", "client_credentials");
        return postMethod;
    }

    private GetMethod getHTTPGetMethod(String regionalDataAmi, String accessToken) {
        GetMethod getMethod = new GetMethod(regionalDataAmi);
        getMethod.addRequestHeader("Authorization", "Bearer " + accessToken);
        getMethod.addRequestHeader("Content-Type", "application/json");
        return getMethod;
    }


    private int executeMethod(HttpMethod getMethod) throws IOException {
        HttpClient client = new HttpClient();
        return client.executeMethod(getMethod);
    }

    private Object getresponseJSONObject(GetMethod getMethod) throws IOException, ParseException {
        String response = getMethod.getResponseBodyAsString();
        JSONParser parser = new JSONParser();
        return parser.parse(response);
    }
}
