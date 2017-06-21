package es.jms1970.lastminute.flightsearch;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import es.jms1970.lastminute.flightsearch.exception.FlightSearchException;

/**
 * System's config container
 * 
 * @author jmss1970@gmail.com
 */
public class ConfigManager {

    /** Properties container */
    private final static Map<String, String> KEYS_CONTAINER = new HashMap<String, String>();

    private ConfigManager() {
        super();
    }

    /**
     * System's configuration loading
     */
    static {

        Properties config = new Properties();
        InputStream io = null;
        try {
            io = ConfigManager.class.getResourceAsStream("/config-flightsearcher.properties");
            config.load(io);
        } catch (Exception e) {
            throw new FlightSearchException("I/O Error", e);
        } finally {
            if (io != null) {
                try {
                    io.close();
                } catch (IOException i) {
                    throw new FlightSearchException("Error closing config-flightsearcher.properties", i);
                }
            }
        }

        // Set config values. Mainly file data names
        KEYS_CONTAINER.put(ApplicationKeys.AIRLINES_FILE, config.getProperty(ApplicationKeys.AIRLINES_FILE));
        KEYS_CONTAINER.put(ApplicationKeys.AIRPORTS_FILE, config.getProperty(ApplicationKeys.AIRPORTS_FILE));
        KEYS_CONTAINER.put(ApplicationKeys.FLIGHTS_FILE, config.getProperty(ApplicationKeys.FLIGHTS_FILE));
        KEYS_CONTAINER.put(ApplicationKeys.PRICING_RULES_FILE, config.getProperty(ApplicationKeys.PRICING_RULES_FILE));

    }

    /**
     * Return the configuration value
     * 
     * @param key Property in config-flightsearcher.properties file
     * @return Property value
     */
    public static String getValue(String key) {
        return KEYS_CONTAINER.get(key);
    }
}