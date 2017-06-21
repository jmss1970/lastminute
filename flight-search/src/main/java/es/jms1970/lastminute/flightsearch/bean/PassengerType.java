package es.jms1970.lastminute.flightsearch.bean;

/**
 * Identifies the different passenger types
 * 
 * @author jmss1970@gmail.com
 */
public enum PassengerType {

    /** Full price (i.e. price resulting from the *days to departure date* rule) */
    ADULT,
    /** 33% discount of the price calculated according to the *days to departure date* rule */
    CHILD,
    /** fixed price depending on the airline. Rule *days to departure date* is not applied for infants */
    INFANT;

}
