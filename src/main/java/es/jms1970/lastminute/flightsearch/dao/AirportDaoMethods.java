package es.jms1970.lastminute.flightsearch.dao;

import java.util.List;

import es.jms1970.lastminute.flightsearch.model.Airport;

/**
 * Airports DAO definition
 * 
 * @author jmss1970@gmail.com
 */
public interface AirportDaoMethods {

    /**
     * Find airports which IATA Code or name contain the criteria
     * 
     * @param criteria
     * @return List of matching airports
     */
    List<Airport> findAirports(String criteria);

    /**
     * Return all airports data
     * 
     * @return List of airports
     */
    List<Airport> findAll();

    /**
     * Find a unique airport by its IATA code
     * 
     * @param iataCode IATA code
     * @return <Airport> instance or <null> if not found
     */
    Airport findByIataCode(String iataCode);

}
