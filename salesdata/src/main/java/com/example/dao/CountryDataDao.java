package com.example.dao;

import com.example.beans.CountryData;
import com.example.util.DBUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samith on 3/3/16.
 */
public class CountryDataDao {

    private static Log log = LogFactory.getLog(CountryDataDao.class);
    public List<CountryData> getAllCountryData() throws SQLException {
        Connection conn = DBUtil.getDBConnection();
        String sql = "SELECT CountryData.*,CountryCodes.name FROM CountryData INNER JOIN CountryCodes ON CountryData.code=CountryCodes.code";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CountryData> countryDataList = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int targetSales = resultSet.getInt("targetSales");
                int currentSales = resultSet.getInt("currentSales");
                int marketShare = resultSet.getInt("marketShare");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                CountryData countryData = generateCountryDataObject(id, targetSales, currentSales, marketShare, code, name);
                countryDataList.add(countryData);
            }
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(conn);
        }
        return countryDataList;
    }

    private CountryData generateCountryDataObject(int id, int targetSales, int currentSales, int marketShare,
                                                  String code, String name) {
        CountryData countryData = new CountryData();
        countryData.setId(id);
        countryData.setName(name);
        countryData.setCode(code);
        countryData.setTargetSales(targetSales);
        countryData.setCurrentSales(currentSales);
        countryData.setMarketShare(marketShare);
        return countryData;
    }

    public CountryData getCountryDataByDataId(int dataId) throws SQLException {
        Connection conn = DBUtil.getDBConnection();
        String sql = "SELECT CountryData.*,CountryCodes.name " +
                     "FROM CountryData INNER JOIN CountryCodes ON CountryData.code=CountryCodes.code " +
                     "WHERE CountryData.id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CountryData countryData = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,dataId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int targetSales = resultSet.getInt("targetSales");
                int currentSales = resultSet.getInt("currentSales");
                int marketShare = resultSet.getInt("marketShare");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                countryData = generateCountryDataObject(id, targetSales, currentSales, marketShare, code, name);
            }
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(conn);
        }
        return countryData;
    }

    public List<CountryData> getCountryListDataByName(String searchName) throws SQLException {
        Connection conn = DBUtil.getDBConnection();
        String sql = "SELECT CountryData.*,CountryCodes.name " +
                     "FROM CountryData INNER JOIN CountryCodes ON CountryData.code=CountryCodes.code " +
                     "WHERE CountryCodes.name LIKE ? ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CountryData> countryDataList = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchName + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int targetSales = resultSet.getInt("targetSales");
                int currentSales = resultSet.getInt("currentSales");
                int marketShare = resultSet.getInt("marketShare");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                CountryData countryData = generateCountryDataObject(id, targetSales, currentSales, marketShare, code, name);
                countryDataList.add(countryData);
            }
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(conn);
        }
        return countryDataList;
    }

    public int addCountryData(CountryData countryData) throws SQLException {
        Connection conn = DBUtil.getDBConnection();
        String sql = "INSERT INTO CountryData (code, targetSales, currentSales, marketShare) " +
                     "VALUES (?,?,?,?) ";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int dataId =-1;
        try {
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,countryData.getCode());
            preparedStatement.setInt(2,countryData.getTargetSales());
            preparedStatement.setInt(3,countryData.getCurrentSales());
            preparedStatement.setInt(4,countryData.getMarketShare());
            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                dataId = rs.getInt(1);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(conn);
        }
        return dataId;
    }

    public void updateCountryData(CountryData countryData) throws SQLException {
        Connection conn = DBUtil.getDBConnection();
        String sql = "UPDATE CountryData SET code =? , targetSales =? , currentSales =? , marketShare = ? " +
                     "WHERE id = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,countryData.getCode());
            preparedStatement.setInt(2,countryData.getTargetSales());
            preparedStatement.setInt(3,countryData.getCurrentSales());
            preparedStatement.setInt(4,countryData.getMarketShare());
            preparedStatement.setInt(5,countryData.getId());
            preparedStatement.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(conn);
        }
    }

    public void deleteCountryData(int countryDataId) throws SQLException {
        Connection conn = DBUtil.getDBConnection();
        String sql = "DELETE FROM CountryData WHERE id = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,countryDataId);
            preparedStatement.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(conn);
        }
    }
}
