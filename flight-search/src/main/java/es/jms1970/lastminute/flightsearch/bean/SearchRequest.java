package es.jms1970.lastminute.flightsearch.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.jms1970.lastminute.flightsearch.exception.AirportNotFoundException;
import es.jms1970.lastminute.flightsearch.exception.DepartureDateException;
import es.jms1970.lastminute.flightsearch.exception.PassengerException;
import es.jms1970.lastminute.flightsearch.model.Airport;

/**
 * Flight search request container
 * 
 * @author jmss1970@gmail.com
 */
public class SearchRequest {

    /** Origin airport */
    private Airport originAirport;

    /** Destination airport */
    private Airport destinationAirport;

    /** Departure date */
    private Date departureDate;

    /** List of passenger */
    private List<PassengerType> passengers;

    public SearchRequest() {
        super();
        this.passengers = new ArrayList<PassengerType>();
    }

    /**
     * @return Origin airport
     */
    public Airport getOriginAirport() {
        return originAirport;
    }

    /**
     * @param originAirport Origin airport
     */
    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    /**
     * @return Destination airport
     */
    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * @param destinationAirport Destination airport
     */
    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    /**
     * @return Departure date
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * @param departureDate Departure date
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * @return List of passengers
     */
    public List<PassengerType> getPassengers() {
        return passengers;
    }

    /**
     * @param passenger List of passengers
     */
    public void addPassengers(PassengerType passenger) {
        this.passengers.add(passenger);
    }

    /**
     * Validates whether the request is valid to launch a flight search.
     * If it's not valid it throws FlightSearchException instances
     * 
     * @return <TRUE> if the request is valid, <FALSE> otherwise.
     */
    public boolean isValid() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date d = new Date(System.currentTimeMillis());
        int today = Integer.parseInt(sdf.format(d)), departure = 0;

        if (this.getDepartureDate() == null) {
            throw new DepartureDateException("Departure date mandatory!");
        }
        // Just to get rid of the time part
        departure = Integer.parseInt(sdf.format(this.getDepartureDate()));

        if (departure < today) {
            throw new DepartureDateException("Departure must be today or later!");
        }

        if (this.getDestinationAirport() == null) {
            throw new AirportNotFoundException("Destination airport is mandatory!");
        }
        if (this.getOriginAirport() == null) {
            throw new AirportNotFoundException("Origin airport is mandatory!");
        }

        if (this.getOriginAirport().equals(this.getDestinationAirport())) {
            throw new AirportNotFoundException("Origin and destinarion airport must be different!");
        }

        if (this.getPassengers().size() == 0) {
            throw new PassengerException("At least one passenger is needed!");
        }

        return true;
    }

}
