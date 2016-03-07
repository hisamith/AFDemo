package com.example.api;

import com.example.beans.CountryData;
import com.example.servlets.CountryService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/regionalData")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(MediaType.APPLICATION_JSON)
public class CountryDataResource {
    private CountryService countryService = new CountryService();

    @GET
    public Response getAllCountryData() throws Exception {
        List<CountryData> allCountryData = countryService.getAllCountryData();
        return Response.ok(allCountryData).build();
    }
}
