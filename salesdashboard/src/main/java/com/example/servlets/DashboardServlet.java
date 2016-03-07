/* Copyright � 2015 Oracle and/or its affiliates. All rights reserved. */
package com.example.servlets;

import com.example.beans.DashboardDataHolder;
import com.example.restclient.RestClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DashboardServlet extends HttpServlet {

    private static Log log = LogFactory.getLog(DashboardServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RestClient client = new RestClient();
            DashboardDataHolder dashboardDataHolder = client.getData();
            forwardListEmployees(req, resp, dashboardDataHolder);
        }catch (Exception e) {
            log.error("Error occurred while getting data" ,e );
            forwardError(req,resp);
        }

    }




    private void forwardListEmployees(HttpServletRequest req, HttpServletResponse resp, DashboardDataHolder dashboardDataHolder)
            throws ServletException, IOException {
        String nextJSP = "/dashboard.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("countryDataList", dashboardDataHolder.getCountryDataList());
        req.setAttribute("stats", dashboardDataHolder.getStats());
        req.setAttribute("mapData", dashboardDataHolder.getMapDataJsonArray().toJSONString());
        req.setAttribute("globalMarketShare", dashboardDataHolder.getStats().getGlobalMarketShare().toJSONString());
        req.setAttribute("monthlySalesData", dashboardDataHolder.getStats().getMonthlySalesData().toJSONString());
        dispatcher.forward(req, resp);
    }   
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    private void forwardError(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nextJSP = "/error.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }



}
