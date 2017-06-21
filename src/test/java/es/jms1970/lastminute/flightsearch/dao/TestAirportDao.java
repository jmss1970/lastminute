package es.jms1970.lastminute.flightsearch.dao;

import java.util.List;

import org.junit.Test;

import es.jms1970.lastminute.flightsearch.model.Airport;
import junit.framework.Assert;

public class TestAirportDao {

    @Test
    public void testDao() {

        List<Airport> airports = null;

        AirportDao aDao = new AirportDao();

        airports = aDao.findAirports("a");

        if (airports.size() != 7) {
            Assert.fail("Seven airports with \"A\" in the name or code are exepcted!");
        }

        airports = aDao.findAirports("b");
        if (airports.size() != 2) {
            Assert.fail("Just two airports with \"B\" in the name or code are exepcted!");
        }

        airports = aDao.findAirports("ar");
        if (airports.size() != 2) {
            Assert.fail("Just two airports with \"ar\" in the name or code are exepcted!");
        }

        airports = aDao.findAirports("BCN");
        if (airports.size() != 1) {
            Assert.fail("Just Barcelona is exepcted!");
        }

        if (aDao.findByIataCode("MAD") == null) {
            Assert.fail("Madrid is exepcted!");
        }
        if (aDao.findByIataCode("Mad") == null) {
            Assert.fail("Madrid is exepcted!");
        }
        if (aDao.findByIataCode("NAN") != null) {
            Assert.fail("Nothing is exepcted!");
        }
        if (aDao.findAirports("NAN").size() != 0) {
            Assert.fail("Nothing is exepcted!");
        }

    }

}
