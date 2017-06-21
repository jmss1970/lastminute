package es.jms1970.lastminute.flightsearch.dao;

import java.util.Date;
import java.util.List;

import es.jms1970.lastminute.flightsearch.model.PricingRule;

/**
 * Pricing rules DAO definition
 * 
 * @author jmss1970@gmail.com
 */
public interface PricingRuleDaoMethods {
    /***
     * Return the pricing rule related to the departure date
     * 
     * @param departureDate Departure date
     * @return <PricingRule> instance
     */
    PricingRule getPricingRule(Date departureDate);

    /**
     * @return All pricing rules
     */
    List<PricingRule> findAll();

}
