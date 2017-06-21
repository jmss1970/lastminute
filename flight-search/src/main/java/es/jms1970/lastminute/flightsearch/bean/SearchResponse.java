package es.jms1970.lastminute.flightsearch.bean;

import java.util.List;

import es.jms1970.lastminute.flightsearch.model.Flight;
import es.jms1970.lastminute.flightsearch.model.PricingRule;

/**
 * Flight search response container
 * 
 * @author jmss1970@gmail.com
 */
public class SearchResponse {

    /** Original request */
    private SearchRequest request;

    /** Pricing rule based on departure date */
    private PricingRule pricingRule;

    /** List of available flights for the request */
    private List<Flight> availableFlights;

    public SearchResponse() {
        super();
    }

    /**
     * @return Original request
     */
    public SearchRequest getRequest() {
        return request;
    }

    /**
     * @param request Original request
     */
    public void setRequest(SearchRequest request) {
        this.request = request;
    }

    /**
     * @return List of available flights
     */
    public List<Flight> getAvailableFlights() {
        return availableFlights;
    }

    /**
     * @param availableFlights List of available flights
     */
    public void setAvailableFlights(List<Flight> availableFlights) {
        this.availableFlights = availableFlights;
    }

    /**
     * @return Pricing rule
     */
    public PricingRule getPricingRule() {
        return pricingRule;
    }

    /**
     * @param pricingRule Pricing rule
     */
    public void setPricingRule(PricingRule pricingRule) {
        this.pricingRule = pricingRule;
    }
}
