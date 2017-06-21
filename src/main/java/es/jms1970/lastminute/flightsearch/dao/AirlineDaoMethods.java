package es.jms1970.lastminute.flightsearch.dao;

import java.util.List;

import es.jms1970.lastminute.flightsearch.model.Airline;

/**
 * Airlines DAO definition
 * 
 * @author jmss1970@gmail.com
 */
public interface AirlineDaoMethods {

    /**
     * Find airlines which IATA Code or name contain the criteria
     * 
     * @param criteria
     * @return List of matching airlines
     */
    List<Airline> findAirlines(String criteria);

    /**
     * Return all airlines data
     * 
     * @return List of airlines
     */
    List<Airline> findAll();

    /**
     * Find a unique airline by its IATA code
     * 
     * @param iataCode IATA code
     * @return <Airline> instance or <null> if not found
     */
    Airline findByIataCode(String iataCode);

}
