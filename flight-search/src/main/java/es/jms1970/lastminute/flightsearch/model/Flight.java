package es.jms1970.lastminute.flightsearch.model;

import java.math.BigDecimal;

/**
 * ORM for flights data
 * 
 * @author jmss1970@gmail.com
 */
public class Flight {

    /**
     * Origin airport
     */
    private Airport origin;

    /**
     * Destination airport
     */
    private Airport destination;

    /**
     * Flight number
     */
    private FlighNumber flightNumber;

    /**
     * Base price
     */
    private BigDecimal basePrice;

    /**
     * Constructor
     */
    public Flight() {
        super();
    }

    /**
     * @return Origin airport
     */
    public Airport getOrigin() {
        return origin;
    }

    /**
     * @param origin Origin airport
     */
    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    /**
     * @return Destination airport
     */
    public Airport getDestination() {
        return destination;
    }

    /**
     * @param destination Destination airport
     */
    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    /**
     * @return Flight number
     */
    public FlighNumber getFlightNumber() {
        return flightNumber;
    }

    /**
     * @param flightNumber Flight number
     */
    public void setFlightNumber(FlighNumber flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @return Base price
     */
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    /**
     * @param basePrice Base price
     */
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * @return Hash value for quick searches
     */
    public String getSearchValue() {
        return getOrigin().getIataCode() + ";" + getDestination().getIataCode();
    }

    @Override
    public String toString() {
        return "Flight [origin=" + origin + ", destination=" + destination + ", flightNumber=" + flightNumber + ", basePrice="
                + basePrice.toPlainString() + "]";
    }

}
