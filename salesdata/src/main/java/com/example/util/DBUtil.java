package com.example.util;/*
*Copyright (c) 2005-2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

    public static final String DATASOURCE_NAME = "DatasourceName";
    private static Log log = LogFactory.getLog(DBUtil.class);

    public static DataSource getDataSource() {
        DataSource dataSource = null;
        String dataSourceName = ConfigUtil.getPropertyValue(DATASOURCE_NAME);
        try {
            InitialContext context = new InitialContext();
            dataSource = (DataSource) context.lookup(dataSourceName);
        } catch (NamingException e) {
            log.error("Error while getting datasource : " + dataSourceName);
        }
        return dataSource;
    }

    public static Connection getDBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Jdbc driver not found",e);
        }
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(Connection dbConnection) {

        if (dbConnection != null) {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                String msg = "Error while closing the database connection";
                log.error(msg, e);
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) {

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                String msg = "Error while closing prepared statement";
                log.error(msg, e);
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            // This method is called within a finally block. Hence we do not throw an error from here
            String msg = "Could not close resultSet";
            log.error(msg, e);
        }
    }
}
