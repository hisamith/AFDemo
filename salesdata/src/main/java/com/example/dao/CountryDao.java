package com.example.dao;

import com.example.beans.Country;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samith on 3/2/16.
 */
public class CountryDao {

    public List<Country> getAllCountries() throws SQLException {
        Connection conn = DBUtil.getDBConnection();
        String sql = "select * from CountryCodes";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                Country country = new Country(id, code, name);
                countries.add(country);
            }
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(conn);
        }
        return countries;
    }

    public List<Country> getRemainingCountries() throws SQLException {
        Connection conn = DBUtil.getDBConnection();
        String sql = "select * from CountryCodes where CountryCodes.code NOT IN (SELECT code FROM CountryData)";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                Country country = new Country(id, code, name);
                countries.add(country);
            }
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(conn);
        }
        return countries;
    }
}
