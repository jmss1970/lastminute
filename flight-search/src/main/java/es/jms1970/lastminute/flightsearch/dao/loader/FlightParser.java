package es.jms1970.lastminute.flightsearch.dao.loader;

import java.math.BigDecimal;

import es.jms1970.lastminute.flightsearch.ApplicationKeys;
import es.jms1970.lastminute.flightsearch.dao.AirlineDao;
import es.jms1970.lastminute.flightsearch.dao.AirlineDaoMethods;
import es.jms1970.lastminute.flightsearch.dao.AirportDao;
import es.jms1970.lastminute.flightsearch.dao.AirportDaoMethods;
import es.jms1970.lastminute.flightsearch.model.FlighNumber;
import es.jms1970.lastminute.flightsearch.model.Flight;

/**
 * Parses one flight data line
 * 
 * @author jmss1970@gmail.com
 */
public class FlightParser implements DataParser<Flight> {

    /** Airports DAO */
    private AirportDaoMethods airportDao;

    /** Airlines DAO */
    private AirlineDaoMethods airlineDao;

    public FlightParser() {
        super();
        // With Spring-beans this is unneccesary
        setAirlineDao(new AirlineDao());
        setAirportDao(new AirportDao());
    }

    /**
     * Parse the line
     * 
     * @param Flight data line
     * @return <Flight> instance
     */
    public Flight parse(String line) {

        String[] items = line.split(",");

        Flight f = new Flight();
        f.setBasePrice(new BigDecimal(items[3]));
        f.setDestination(getAirportDao().findByIataCode(items[1]));
        f.setFlightNumber(new FlighNumber(getAirlineDao().findByIataCode(items[2].substring(0, 2)), items[2].substring(2)));
        f.setOrigin(getAirportDao().findByIataCode(items[0]));

        return f;

    }

    /**
     * @return Airports DAO
     */
    public AirportDaoMethods getAirportDao() {
        return airportDao;
    }

    /**
     * @param airportDao Airports DAO
     */
    public void setAirportDao(AirportDaoMethods airportDao) {
        this.airportDao = airportDao;
    }

    /**
     * @return Airlines DAO
     */
    public AirlineDaoMethods getAirlineDao() {
        return airlineDao;
    }

    /**
     * @param airlineDao Airlines DAO
     */
    public void setAirlineDao(AirlineDaoMethods airlineDao) {
        this.airlineDao = airlineDao;
    }

    /**
     * Validates the data line
     * 
     * @param line Data line
     * @return <TRUE> if the data line is valid, otherwise <FALSE>
     */
    public boolean isValid(String line) {
        String[] items = line.split(",");

        if (!items[2].matches(ApplicationKeys.STANDARD_FULL_FLIGHT_NUMBER_PATTERN)) {
            System.out.println(items[2] + " is not a valid flight number. Flight skipped");
            return false;
        }

        // The given list contains same origin and destination.
        // Due to there is a validation about this issue in the SearchRequest class, the flight is skipped
        if (items[0].equals(items[1])) {
            System.out.println("Origin airport (" + items[0] + ") and destination airport (" + items[1] + ") are equals. Flight skipped");
            return false;
        }

        return true;
    }
}
