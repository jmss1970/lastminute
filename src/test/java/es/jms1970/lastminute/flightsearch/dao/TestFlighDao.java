package es.jms1970.lastminute.flightsearch.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import es.jms1970.lastminute.flightsearch.model.Airport;
import es.jms1970.lastminute.flightsearch.model.Flight;

public class TestFlighDao {

    @Test
    public void testDao() {

        List<Flight> flights = null;
        Airport origin = null, destination = null;
        AirportDaoMethods airportDao = new AirportDao();

        FlightDao aDao = new FlightDao();

        try {
            aDao.findFlights(null, null);
            Assert.fail("Airports needed!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        origin = airportDao.findByIataCode("Mad");
        try {
            aDao.findFlights(origin, destination);
            Assert.fail("Airports needed!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        destination = airportDao.findByIataCode("BCN");

        if (aDao.findFlights(origin, destination).size() != 1) {
            Assert.fail("One flight expected!");
        }
    }

}
