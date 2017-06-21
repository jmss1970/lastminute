package es.jms1970.lastminute.flightsearch;

import es.jms1970.lastminute.flightsearch.bean.SearchRequest;
import es.jms1970.lastminute.flightsearch.bean.SearchResponse;
import es.jms1970.lastminute.flightsearch.dao.FlightDao;
import es.jms1970.lastminute.flightsearch.dao.FlightDaoMethods;
import es.jms1970.lastminute.flightsearch.dao.PricingRuleDao;
import es.jms1970.lastminute.flightsearch.dao.PricingRuleDaoMethods;

/**
 * Flight searches facade
 * 
 * @author jmss1970@gmail.com
 */
public class FlightSearcher {

    /** Flights DAO */
    private FlightDaoMethods flightDao;

    /** Pricing rule DAO */
    private PricingRuleDaoMethods pricingRuleDao;

    /**
     * Constructor
     */
    public FlightSearcher() {
        super();
        // With Spring-beans this instanciation is not neccesary  
        setFlightDao(new FlightDao());
        setPricingRuleDao(new PricingRuleDao());

    }

    /**
     * @return Flights DAO
     */
    public FlightDaoMethods getFlightDao() {
        return flightDao;
    }

    /**
     * @param flightDao Flights DAO
     */
    public void setFlightDao(FlightDaoMethods flightDao) {
        this.flightDao = flightDao;
    }

    /**
     * @return Pricing rule DAO
     */
    public PricingRuleDaoMethods getPricingRuleDao() {
        return pricingRuleDao;
    }

    /**
     * @param pricingRuleDao Pricing rule DAO
     */
    public void setPricingRuleDao(PricingRuleDaoMethods pricingRuleDao) {
        this.pricingRuleDao = pricingRuleDao;
    }

    /**
     * Returns a search response for the search request
     * If the request is invalid no search is launched
     * The response contains the original reques objetc, the available flights and the pricing rule to be applied
     * 
     * @param request Flight search request
     * @return Search response
     */
    public SearchResponse searchFlights(SearchRequest request) {

        SearchResponse response = null;

        if (request.isValid()) {
            response = search(request);
        }

        return response;

    }

    /**
     * Populate the response with the DAO results
     * 
     * @param request Flight search request
     * @return Search response
     */
    private SearchResponse search(SearchRequest request) {

        SearchResponse response = new SearchResponse();

        response.setRequest(request);
        response.setAvailableFlights(getFlightDao().findFlights(request.getOriginAirport(), request.getDestinationAirport()));
        response.setPricingRule(getPricingRuleDao().getPricingRule(request.getDepartureDate()));

        return response;

    }

}
