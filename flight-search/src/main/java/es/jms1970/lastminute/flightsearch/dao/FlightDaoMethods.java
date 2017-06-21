package es.jms1970.lastminute.flightsearch.dao;

import java.util.List;

import es.jms1970.lastminute.flightsearch.model.Airport;
import es.jms1970.lastminute.flightsearch.model.Flight;

/**
 * Search flights methods
 * 
 * @author jmss1970@gmail.com
 */
public interface FlightDaoMethods {

    /**
     * Find flights. Assuming permanent avalaibility, no more arguments are needed
     * 
     * @param originAirport Origin airport
     * @param destinationAirport Destination airport
     * @return List of flights
     */
    List<Flight> findFlights(Airport originAirport, Airport destinationAirport);

    /**
     * @return List of all flights.
     */
    List<Flight> findAll();
}
