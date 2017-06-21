package es.jms1970.lastminute.flightsearch.dao.loader;

/**
 * Pricing rules data file loader
 * 
 * @author jmss1970@gmail.com
 */
public class PricingRuleLoader extends BasicCsvLoader<PricingRuleParser> {

    /**
     * Sets the data parser
     */
    public PricingRuleLoader() {
        super();
        setParser(new PricingRuleParser());
    }

}
