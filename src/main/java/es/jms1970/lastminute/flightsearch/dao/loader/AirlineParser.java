package es.jms1970.lastminute.flightsearch.dao.loader;

import java.math.BigDecimal;

import es.jms1970.lastminute.flightsearch.model.Airline;

/**
 * Parses one airline data line
 * 
 * @author jmss1970@gmail.com
 */
public class AirlineParser implements DataParser<Airline> {

    public AirlineParser() {
        super();
    }

    /**
     * Parse the line
     * 
     * @param Airline data line
     * @return <Airline> instance
     */
    public Airline parse(String line) {

        String[] items = line.split(",");

        Airline a = new Airline();
        a.setCurrency(items[3]);
        a.setIataCode(items[0]);
        a.setInfantPrice(new BigDecimal(items[2]));
        a.setName(items[1]);

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
