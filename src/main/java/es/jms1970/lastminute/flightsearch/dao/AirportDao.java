package es.jms1970.lastminute.flightsearch.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import es.jms1970.lastminute.flightsearch.ApplicationKeys;
import es.jms1970.lastminute.flightsearch.ConfigManager;
import es.jms1970.lastminute.flightsearch.dao.loader.AirportLoader;
import es.jms1970.lastminute.flightsearch.model.Airport;

/**
 * Implementation on DAO for airports data
 * 
 * @author jmss1970@gmail.com
 */
public class AirportDao implements AirportDaoMethods {

    /** List of airports loaded from data file */
    static List<Airport> airportsList = new ArrayList<Airport>();

    /** Airports facility search map */
    static Map<String, Airport> airportsCriteriaMap = new HashMap<String, Airport>();

    /**
     * Loads airport data
     */
    static {

        AirportLoader loader = new AirportLoader();
        loader.loadFile(ConfigManager.getValue(ApplicationKeys.AIRPORTS_FILE), airportsList);

        for (Airport a : airportsList) {
            // Uses a hash to improve searches
            // Sad substitute for searh engines like Lucene, Solr, etc (plain Java is required)
            airportsCriteriaMap.put(a.getSearchValue().toUpperCase(), a);
        }

    }

    public AirportDao() {
        super();
    }

    /**
     * Find airports which IATA Code or name contain the criteria
     * 
     * @param criteria
     * @return List of matching airports
     */
    public List<Airport> findAirports(String criteria) {

        // Just want one ocurrence per hit
        Set<Airport> airports = new HashSet<Airport>();
        List<Airport> ret = new ArrayList<Airport>();

        if (criteria == null || criteria.trim().length() == 0) {
            return ret;
        }

        for (Entry<String, Airport> entry : airportsCriteriaMap.entrySet()) {
            if (entry.getKey().contains(criteria.toUpperCase())) {
                airports.add(entry.getValue());
            }
        }

        ret.addAll(airports);

        return ret;
    }

    /**
     * Find a unique airport by its IATA code
     * 
     * @param iataCode IATA code
     * @return <Airport> instance or <null> if not found
     */
    public Airport findByIataCode(String iataCode) {

        Airport a = null;

        for (Airport aa : airportsList) {
            if (aa.getIataCode().equalsIgnoreCase(iataCode)) {
                a = aa;
                break;
            }
        }

        return a;
    }

    /**
     * Return all airports data
     * 
     * @return List of airports
     */
    public List<Airport> findAll() {
        return airportsList;
    }
}
