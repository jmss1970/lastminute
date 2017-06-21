package es.jms1970.lastminute.flightsearch;

/**
 * Public system keys
 * 
 * @author jmss1970@gmail.com
 */
public interface ApplicationKeys {

    /** Flight number patttern. For validation purposes */
    String STANDARD_FLIGHT_NUMBER_PATTERN = "[0-9]{4}";

    /** Full flight number pattern. For validation pursoses */
    String STANDARD_FULL_FLIGHT_NUMBER_PATTERN = "[A-Z]{1}[A-Z0-9]{1}[0-9]{4}";

    /** Property name in config-flightsearches-properties file. Contains the file name which contains airlines data */
    String AIRLINES_FILE = "airlines.file";

    /** Property name in config-flightsearches-properties file. Contains the file name which contains airports data */
    String AIRPORTS_FILE = "airports.file";

    /** Property name in config-flightsearches-properties file. Contains the file name which contains flights data */
    String FLIGHTS_FILE = "flights.file";

    /**
     * Property name in config-flightsearches-properties file. Contains the file name which contains pricinf rules data
     */
    String PRICING_RULES_FILE = "pricing.rules.file";

}
