package es.jms1970.lastminute.flightsearch.dao.loader;

/**
 * Flights data file loader
 * 
 * @author jmss1970@gmail.com
 */
public class FlightLoader extends BasicCsvLoader<FlightParser> {

    /**
     * Sets the data parser
     */
    public FlightLoader() {
        super();
        setParser(new FlightParser());
    }

}
