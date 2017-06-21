package es.jms1970.lastminute.flightsearch.dao.loader;

/**
 * Airports data file loader
 * 
 * @author jmss1970@gmail.com
 */
public class AirportLoader extends BasicCsvLoader<AirportParser> {

    /**
     * Sets the data parser
     */
    public AirportLoader() {
        super();
        setParser(new AirportParser());
    }

}
