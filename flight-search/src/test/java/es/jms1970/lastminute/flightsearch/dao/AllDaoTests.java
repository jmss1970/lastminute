package es.jms1970.lastminute.flightsearch.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAirlineDao.class, TestAirportDao.class, TestFlighDao.class, TestPricingRuleDao.class })
public class AllDaoTests {

}
