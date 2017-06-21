package es.jms1970.lastminute.flightsearch.dao.loader;

import java.math.BigDecimal;

import es.jms1970.lastminute.flightsearch.model.PricingRule;

/**
 * Parses one pricing rule data line
 * 
 * @author jmss1970@gmail.com
 */
public class PricingRuleParser implements DataParser<PricingRule> {

    public PricingRuleParser() {
        super();
    }

    /**
     * Parse the line
     * 
     * @param Pricing rule data line
     * @return <PricingRule> instance
     */
    public PricingRule parse(String line) {

        String[] items = line.split(",");

        PricingRule r = new PricingRule();
        r.setMaximunDays(Integer.parseInt(items[1]));
        r.setMinimunDays(Integer.parseInt(items[0]));
        r.setRate(new BigDecimal(items[2]));

        return r;

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
