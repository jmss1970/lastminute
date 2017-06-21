package es.jms1970.lastminute.flightsearch.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import es.jms1970.lastminute.flightsearch.ApplicationKeys;
import es.jms1970.lastminute.flightsearch.ConfigManager;
import es.jms1970.lastminute.flightsearch.dao.loader.FlightLoader;
import es.jms1970.lastminute.flightsearch.exception.FlightSearchException;
import es.jms1970.lastminute.flightsearch.model.Airport;
import es.jms1970.lastminute.flightsearch.model.Flight;

/**
 * Implementation of DAO for flights data
 * 
 * @author jmss1970@gmail.com
 */
public class FlightDao implements FlightDaoMethods {

    /** List of flights loaded from data file */
    static List<Flight> flightList = new ArrayList<Flight>();

    /** Flights facility search map */
    static Map<String, List<Flight>> flightsCriteriaMap = new HashMap<String, List<Flight>>();

    /**
     * Loads flights data
     */
    static {

        FlightLoader loader = new FlightLoader();
        loader.loadFile(ConfigManager.getValue(ApplicationKeys.FLIGHTS_FILE), flightList);

        for (Flight f : flightList) {
            // Uses a hash to improve searches
            // Sad substitute for searh engines like Lucene, Solr, etc (plain Java is required)
            if (!flightsCriteriaMap.containsKey(f.getSearchValue().toUpperCase())) {
                flightsCriteriaMap.put(f.getSearchValue().toUpperCase(), new ArrayList<Flight>());
            }
            flightsCriteriaMap.get(f.getSearchValue().toUpperCase()).add(f);
        }

    }

    public FlightDao() {
        super();
    }

    /**
     * Find flights. Assuming permanent avalaibility, no more arguments are needed
     * 
     * @param originAirport Origin airport
     * @param destinationAirport Destination airport
     * @return List of flights
     */
    public List<Flight> findFlights(Airport originAirport, Airport destinationAirport) {
        List<Flight> ret = new ArrayList<Flight>();
        String criteria = null;

        if (originAirport == null) {
            throw new FlightSearchException("Origin airport is mandatory!");
        }

        if (destinationAirport == null) {
            throw new FlightSearchException("Destination airport is mandatory!");
        }

        criteria = originAirport.getIataCode() + ";" + destinationAirport.getIataCode();
        for (Entry<String, List<Flight>> entry : flightsCriteriaMap.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(criteria)) {
                ret.addAll(entry.getValue());
            }
        }

        return ret;
    }

    /**
     * @return List of all flights.
     */
    public List<Flight> findAll() {
        return flightList;
    }

}
