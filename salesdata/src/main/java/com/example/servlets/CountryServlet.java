/* Copyright � 2015 Oracle and/or its affiliates. All rights reserved. */
package com.example.servlets;

import com.example.beans.Country;
import com.example.beans.CountryData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CountryServlet extends HttpServlet {

    CountryService countryService = new CountryService();
    private static Log log = LogFactory.getLog(CountryServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("doAction");
        if (action!=null){
            switch (action) {           
            case "searchById":
                searchCountryById(req, resp);
                break;           
            case "searchByName":
                searchCountryByName(req, resp);
                break;
            case "addNew":
                addCountryData(req, resp);
                break;
            }

        }else{
            List<CountryData> result = null;
            try {
                result = countryService.getAllCountryData();
            } catch (Exception e) {
                log.error(e);
                forwardError(req,resp);
            }
            forwardListCountries(req, resp, result);
        }
    }

    private void searchCountryById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idString = req.getParameter("idCountry");
        int idCountry = Integer.valueOf(idString);
        CountryData countryData = null;

        try {
            countryData = countryService.getCountry(idCountry);
            List<Country> countryList = countryService.getRemainingCountries();
            req.setAttribute("remainingCountryList", countryList);
        } catch (Exception ex) {
            log.error(ex);
            forwardError(req,resp);
        }
        req.setAttribute("country", countryData);
        req.setAttribute("action", "edit");
        String nextJSP = "/new-country.jsp";
        log.info("searchCountryById    searchCountryById");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void addCountryData(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Country> countryList = countryService.getRemainingCountries();
        req.setAttribute("remainingCountryList", countryList);
        String nextJSP = "/new-country.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }
    
    private void searchCountryByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String countryName = req.getParameter("countryName");
        List<CountryData> result = null;
        try {
            result = countryService.searchCountryByName(countryName);
        } catch (Exception e) {
            log.error(e);
            forwardError(req,resp);
        }
        forwardListCountries(req, resp, result);
    }

    private void forwardListCountries(HttpServletRequest req, HttpServletResponse resp, List countriesList)
            throws ServletException, IOException {
        String nextJSP = "/list-countries.jsp";
        log.info("forwardListCountries forwardListCountries");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("countriesList", countriesList);
        dispatcher.forward(req, resp);
    }

    private void forwardError(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nextJSP = "/error.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addCountryAction(req, resp);
                break;
            case "edit":
                editCountryAction(req, resp);
                break;
            case "remove":
                removeCountryByName(req, resp);
                break;
        }

    }

    private void addCountryAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String code = req.getParameter("code");
        String targetSales = req.getParameter("targetSales");
        String currentSales = req.getParameter("currentSales");
        String marketShare = req.getParameter("marketShare");
        CountryData countryData = generateCountryData(code, targetSales, currentSales, marketShare);
        int idCountry = 0;
        List<CountryData> countriesList = null;
        try {
            idCountry = countryService.addCountry(countryData);
            countriesList = countryService.getAllCountryData();
        } catch (Exception e) {
            log.error(e);
            forwardError(req,resp);
        }
        req.setAttribute("idCountry", idCountry);
        String message = "The new country has been successfully created.";
        req.setAttribute("message", message);
        forwardListCountries(req, resp, countriesList);
    }

    private void editCountryAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String code = req.getParameter("code");
        String targetSales = req.getParameter("targetSales");
        String currentSales = req.getParameter("currentSales");
        String marketShare = req.getParameter("marketShare");
        log.info(req.getParameter("idCountry"));
        int idCountry = Integer.valueOf(req.getParameter("idCountry"));
        CountryData countryData = generateCountryData(code, targetSales, currentSales, marketShare);
        countryData.setId(idCountry);
        boolean success = countryService.updateCountry(countryData);
        String message = null;
        if (success) {
            message = "The country has been successfully updated.";
        }
        List<CountryData> countriesList = null;
        try {
            countriesList = countryService.getAllCountryData();
        } catch (Exception e) {
            log.error(e);
            forwardError(req,resp);
        }
        req.setAttribute("idCountry", idCountry);
        req.setAttribute("message", message);
        forwardListCountries(req, resp, countriesList);
    }

    private CountryData generateCountryData(String code, String targetSales, String currentSales, String marketShare) {
        CountryData countryData = new CountryData();
        countryData.setCode(code);
        countryData.setTargetSales(Integer.valueOf(targetSales));
        countryData.setCurrentSales(Integer.valueOf(currentSales));
        countryData.setMarketShare(Integer.valueOf(marketShare));
        return countryData;
    }

    private void removeCountryByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int idCountry = Integer.valueOf(req.getParameter("idCountry"));
        boolean confirm = false;
        try {
            confirm = countryService.deleteCountry(idCountry);
        } catch (Exception ex) {
            Logger.getLogger(CountryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (confirm){
            String message = "The country has been successfully removed.";
            req.setAttribute("message", message);
        }
        List<CountryData> countriesList = null;
        try {
            countriesList = countryService.getAllCountryData();
        } catch (Exception e) {
            log.error(e);
            forwardError(req,resp);
        }
        forwardListCountries(req, resp, countriesList);
    }

}
