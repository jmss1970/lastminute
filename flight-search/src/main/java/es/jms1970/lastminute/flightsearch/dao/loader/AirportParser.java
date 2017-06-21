package es.jms1970.lastminute.flightsearch.dao.loader;

import es.jms1970.lastminute.flightsearch.model.Airport;

/**
 * Parses one airport data line
 * 
 * @author jmss1970@gmail.com
 */
public class AirportParser implements DataParser<Airport> {

    public AirportParser() {
        super();
    }

    /**
     * Parse the line
     * 
     * @param Airport data line
     * @return <Airport> instance
     */
    public Airport parse(String line) {

        String[] items = line.split(",");

        Airport a = new Airport();
        a.setCity(items[1]);
        a.setIataCode(items[0]);

        return a;

    }

    /**
     * Validates the data line
     * 
     * @param line Data line
     * @return <TRUE> if the data line is valid, otherwise <FALSE>
     */
    public boolean isValid(String line) {
        return line != null && line.trim().length() > 0;
    }

}
