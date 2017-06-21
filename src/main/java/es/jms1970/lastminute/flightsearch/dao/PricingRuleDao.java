package es.jms1970.lastminute.flightsearch.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.jms1970.lastminute.flightsearch.ApplicationKeys;
import es.jms1970.lastminute.flightsearch.ConfigManager;
import es.jms1970.lastminute.flightsearch.DateUtility;
import es.jms1970.lastminute.flightsearch.dao.loader.PricingRuleLoader;
import es.jms1970.lastminute.flightsearch.model.PricingRule;

/**
 * Implementation of DAO for pricing rules data
 * 
 * @author jmss1970@gmail.com
 */
public class PricingRuleDao implements PricingRuleDaoMethods {

    /** List of pricing rules loaded from data file */
    static List<PricingRule> ruleList = new ArrayList<PricingRule>();

    /**
     * Loads pricing rules data
     */
    static {

        PricingRuleLoader loader = new PricingRuleLoader();
        loader.loadFile(ConfigManager.getValue(ApplicationKeys.PRICING_RULES_FILE), ruleList);

    }

    public PricingRuleDao() {
        super();
    }

    /***
     * Return the pricing rule related to the departure date
     * 
     * @param departureDate Departure date
     * @return <PricingRule> instance
     */
    public PricingRule getPricingRule(Date departureDate) {

        PricingRule rule = null;

        Integer numberOfDays = DateUtility.getNumberOfDays(departureDate);

        for (PricingRule pr : ruleList) {

            // First attemp, at least above minimun days
            if (numberOfDays >= pr.getMinimunDays()) {
                rule = pr;
                // Between both figures, that's what we're looking for
                // Otherwise, we always should have the interval within the minimun days
                // Thus the artificial upper limit is not a problem
                if (numberOfDays <= pr.getMaximunDays()) {
                    break;
                }
            }
        }

        return rule;
    }

    /**
     * @return All pricing rules
     */
    public List<PricingRule> findAll() {
        return ruleList;
    }

}
