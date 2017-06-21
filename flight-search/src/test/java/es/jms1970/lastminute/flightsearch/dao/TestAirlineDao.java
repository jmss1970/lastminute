package es.jms1970.lastminute.flightsearch.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import es.jms1970.lastminute.flightsearch.model.Airline;

public class TestAirlineDao {

    @Test
    public void testDao() {

        List<Airline> airlines = null;

        AirlineDao aDao = new AirlineDao();

        airlines = aDao.findAirlines("a");

        if (airlines.size() != 6) {
            Assert.fail("Six airlines with \"A\" in the name or code are exepcted!");
        }

        airlines = aDao.findAirlines("b");
        if (airlines.size() != 2) {
            Assert.fail("Just two airlines with \"B\" in the name or code are exepcted!");
        }

        airlines = aDao.findAirlines("air");
        if (airlines.size() != 3) {
            Assert.fail("Just three airlines with \"ar\" in the name or code are exepcted!");
        }

        airlines = aDao.findAirlines("IB");
        if (airlines.size() != 1) {
            Assert.fail("Just Iberia is exepcted!");
        }

        if (aDao.findByIataCode("FR") == null) {
            Assert.fail("Ryanair is exepcted!");
        }
        if (aDao.findByIataCode("fR") == null) {
            Assert.fail("Ryanair is exepcted!");
        }
        if (aDao.findByIataCode("NAN") != null) {
            Assert.fail("Nothing is exepcted!");
        }
        if (aDao.findAirlines("NAN").size() != 0) {
            Assert.fail("Nothing is exepcted!");
        }

        List<Airline> l1 = aDao.findAll();

        if (l1.size() != 7) {
            Assert.fail("Not all airlines are loaded!");
        }

    }

}
