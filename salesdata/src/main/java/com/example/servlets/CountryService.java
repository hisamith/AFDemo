/* Copyright � 2015 Oracle and/or its affiliates. All rights reserved. */
package com.example.servlets;

import com.example.beans.Country;
import com.example.beans.CountryData;
import com.example.dao.CountryDao;
import com.example.dao.CountryDataDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.List;

public class CountryService {

    private static Log log = LogFactory.getLog(CountryService.class);
    private CountryDataDao countryDataDao = new CountryDataDao();
    private CountryDao countryDao = new  CountryDao();

    public List<CountryData> getAllCountryData() throws Exception {
        return countryDataDao.getAllCountryData();
    }

    public CountryData getCountry(final int id) throws Exception {
        return countryDataDao.getCountryDataByDataId(id);
    }

    public List<CountryData> searchCountryByName(String name) throws Exception {
        return countryDataDao.getCountryListDataByName(name);
    }

    public int addCountry(CountryData countryData) throws Exception {
        return countryDataDao.addCountryData(countryData);
    }

    public boolean updateCountry(CountryData countryData) {
        try {
            countryDataDao.updateCountryData(countryData);
            return true;
        } catch (SQLException e) {
            log.error("Error while updating country data :"+countryData.getId(),e);
            return false;
        }
    }

    public boolean deleteCountry(int id) throws Exception {
        try {
            countryDataDao.deleteCountryData(id);
            return true;
        } catch (SQLException e) {
            log.error("Error while deleting country data id:"+id,e);
            return false;
        }
    }

    public List<Country> getRemainingCountries() throws ServletException {
        try {
            return countryDao.getRemainingCountries();
        } catch (SQLException e) {
            log.error(e);
            throw new ServletException(e);
        }
    }

    public List<Country> getAllCountries() throws ServletException {
        try {
            return countryDao.getAllCountries();
        } catch (SQLException e) {
            log.error(e);
            throw new ServletException(e);
        }
    }
}
