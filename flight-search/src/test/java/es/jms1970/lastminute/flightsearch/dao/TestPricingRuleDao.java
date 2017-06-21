package es.jms1970.lastminute.flightsearch.dao;

import java.util.GregorianCalendar;

import org.junit.Test;

import es.jms1970.lastminute.flightsearch.model.PricingRule;
import junit.framework.Assert;

public class TestPricingRuleDao {

    @Test
    public void testDao() {

        PricingRuleDaoMethods pricingRuleDao = new PricingRuleDao();
        GregorianCalendar gc = new GregorianCalendar();

        // Nowadays we have three intervals:
        // less than 3
        // 3 - 15
        // 16 - 30
        // more than 30 (settled an upper limit of value 999)

        // Must response less than 3
        for (int i = 0; i < 3; i++) {
            testInterval(pricingRuleDao.getPricingRule(gc.getTime()), 0, 2);
            gc.add(GregorianCalendar.DATE, 1);
        }
        // Must response 3 - 15
        gc.setTimeInMillis(System.currentTimeMillis());
        gc.add(GregorianCalendar.DATE, 3);
        for (int i = 3; i < 16; i++) {
            testInterval(pricingRuleDao.getPricingRule(gc.getTime()), 3, 15);
            gc.add(GregorianCalendar.DATE, 1);
        }
        // Must response 16 - 30
        gc.setTimeInMillis(System.currentTimeMillis());
        gc.add(GregorianCalendar.DATE, 16);
        for (int i = 16; i < 31; i++) {
            testInterval(pricingRuleDao.getPricingRule(gc.getTime()), 16, 30);
            gc.add(GregorianCalendar.DATE, 1);
        }
        // Must response more than 30
        gc.setTimeInMillis(System.currentTimeMillis());
        gc.add(GregorianCalendar.DATE, 31);
        for (int i = 31; i < 1000; i++) {
            testInterval(pricingRuleDao.getPricingRule(gc.getTime()), 31, 999);
            gc.add(GregorianCalendar.DATE, 1);
        }

    }

    private void testInterval(PricingRule rule, int minimun, int maximun) {

        if (rule.getMinimunDays() != minimun) {
            Assert.fail("Bad rule found");
        }
        if (rule.getMaximunDays() != maximun) {
            Assert.fail("Bad rule found");
        }

    }

}
