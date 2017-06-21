package es.jms1970.lastminute.flightsearch.model;

import es.jms1970.lastminute.flightsearch.ApplicationKeys;

/**
 * Flight number bean
 * 
 * @author jmss1970@gmail.com
 */
public class FlighNumber {

    /** Airline */
    private Airline airline;

    /** Flight code number */
    private String number;

    /**
     * Constructor
     * 
     * @param airLine Airline bean
     * @param number Fligh code number
     */
    public FlighNumber(Airline airLine, String number) {
        super();
        setAirline(airLine);
        setNumber(number);
    }

    /**
     * @return Airline
     */
    public Airline getAirline() {
        return airline;
    }

    /**
     * @param airline Airline
     */
    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    /**
     * @return Flight code number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number Flight code number
     */
    public void setNumber(String number) {
        if (number != null && number.trim().matches(ApplicationKeys.STANDARD_FLIGHT_NUMBER_PATTERN)) {
            this.number = number.trim();
        }
    }

    /**
     * @return Full flight number
     */
    public String getFlightNumber() {

        String s = null;

        if (getAirline() != null && getNumber() != null) {
            s = airline.getIataCode().concat(getNumber());
        }

        return s;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFlightNumber() == null) ? 0 : getFlightNumber().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        FlighNumber other = null;

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        try {
            other = (FlighNumber) obj;
        } catch (Exception e) {
            return false;
        }

        other = (FlighNumber) obj;

        if (getFlightNumber() == null) {
            if (other.getFlightNumber() != null) {
                return false;
            }
        } else if (!getFlightNumber().equals(other.getFlightNumber())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "FlighNumber [getFlightNumber()=" + getFlightNumber() + "]";
    }
}
