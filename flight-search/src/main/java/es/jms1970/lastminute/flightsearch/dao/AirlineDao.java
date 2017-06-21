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
import es.jms1970.lastminute.flightsearch.dao.loader.AirlineLoader;
import es.jms1970.lastminute.flightsearch.model.Airline;

/**
 * Implementation on DAO for airlines data
 * 
 * @author jmss1970@gmail.com
 */
public class AirlineDao implements AirlineDaoMethods {

    /** List of airlines loaded from data file */
    static List<Airline> airlinesList = new ArrayList<Airline>();

    /** Airlines facility search map */
    static Map<String, Airline> airlinesCriteriaMap = new HashMap<String, Airline>();

    /**
     * Loads airline data
     */
    static {

        AirlineLoader loader = new AirlineLoader();
        loader.loadFile(ConfigManager.getValue(ApplicationKeys.AIRLINES_FILE), airlinesList);

        for (Airline a : airlinesList) {
            // Uses a hash to improve searches
            // Sad substitute for searh engines like Lucene, Solr, etc (plain Java is required)
            airlinesCriteriaMap.put(a.getSearchValue().toUpperCase(), a);
        }

    }

    public AirlineDao() {
        super();
    }

    /**
     * Find airlines which IATA Code or name contain the criteria
     * 
     * @param criteria
     * @return List of matching airlines
     */
    public List<Airline> findAirlines(String criteria) {

        // Just want one ocurrence per hit
        Set<Airline> airlines = new HashSet<Airline>();
        List<Airline> ret = new ArrayList<Airline>();

        if (criteria == null || criteria.trim().length() == 0) {
            return ret;
        }

        for (Entry<String, Airline> entry : airlinesCriteriaMap.entrySet()) {
            if (entry.getKey().contains(criteria.toUpperCase())) {
                airlines.add(entry.getValue());
            }
        }

        ret.addAll(airlines);

        return ret;
    }

    /**
     * Find a unique airline by its IATA code
     * 
     * @param iataCode IATA code
     * @return <Airline> instance or <null> if not found
     */
    public Airline findByIataCode(String iataCode) {

        Airline a = null;

        // A map could be used
        for (Airline aa : airlinesList) {
            if (aa.getIataCode().equalsIgnoreCase(iataCode)) {
                a = aa;
                break;
            }
        }

        return a;
    }

    /**
     * Return all airlines data
     * 
     * @return List of airlines
     */
    public List<Airline> findAll() {
        return airlinesList;
    }

}
