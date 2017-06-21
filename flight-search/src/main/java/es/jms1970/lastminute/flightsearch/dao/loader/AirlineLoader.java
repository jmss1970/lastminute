package es.jms1970.lastminute.flightsearch.dao.loader;

/**
 * Airlines data file loader
 * 
 * @author jmss1970@gmail.com
 */
public class AirlineLoader extends BasicCsvLoader<AirlineParser> {

    /**
     * Sets the data parser
     */
    public AirlineLoader() {
        super();
        setParser(new AirlineParser());
    }

}
