package es.jms1970.lastminute.flightsearch;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import es.jms1970.lastminute.flightsearch.bean.PassengerType;
import es.jms1970.lastminute.flightsearch.bean.SearchRequest;
import es.jms1970.lastminute.flightsearch.bean.SearchResponse;
import es.jms1970.lastminute.flightsearch.model.Flight;
import es.jms1970.lastminute.flightsearch.model.PricingRule;

/**
 * Utility class for testing purposes.
 * I writes the search responses in an output
 * 
 * @author jmss1970@gmail.com
 */
public class SearchReponseWriter {

    /**
     * Date formatter
     */
    private SimpleDateFormat dateFormat;

    public SearchReponseWriter() {
        super();

        this.dateFormat = new SimpleDateFormat("yyyyMMdd");

    }

    /**
     * Writes the response to the output
     * 
     * @param response Response to write
     * @param out PrintStream output
     */
    public void writeResponse(SearchResponse response, PrintStream out) {

        out.println();
        out.println("-----------------------------------------------------------------------");
        out.println("-----                 Search response                             -----");
        out.println("-----------------------------------------------------------------------");

        if (response == null) {
            out.println("No response to write");
        } else if (response.getAvailableFlights().isEmpty()) {
            printRequestString(response.getRequest(), out);
            out.println("No flights available");
        } else {
            printRequestString(response.getRequest(), out);
            out.println("Flights:");
            for (Flight f : response.getAvailableFlights()) {
                printFlightString(f, response.getRequest(), response.getPricingRule(), out);
            }
        }
    }

    /**
     * Print de details of the fligh search request
     * 
     * @param request Flight search request
     * @param out PrintStream out
     */
    private void printRequestString(SearchRequest request, PrintStream out) {

        int adults = 0, children = 0, infants = 0;
        int numberOfDays = DateUtility.getNumberOfDays(request.getDepartureDate());

        out.println("From " + request.getOriginAirport().getCity() + "(" + request.getOriginAirport().getIataCode() + ") To "
                + request.getDestinationAirport().getCity() + "(" + request.getDestinationAirport().getIataCode() + ")");
        out.println("Departure date: " + this.dateFormat.format(request.getDepartureDate()) + ", " + numberOfDays
                + (numberOfDays > 1 ? " days" : " day") + " to departure time.");
        for (PassengerType passenger : request.getPassengers()) {
            switch (passenger) {
            case ADULT:
                adults++;
                break;
            case CHILD:
                children++;
                break;
            case INFANT:
                infants++;
                break;
            }
        }

        out.println("Passengers: ");
        if (adults > 0) {
            out.println("\tAdults: " + adults);
        }
        if (children > 0) {
            out.println("\tChildren: " + children);
        }
        if (infants > 0) {
            out.println("\tInfants: " + infants);
        }
    }

    /**
     * Print the details of the flight prices
     * 
     * @param flight Flight
     * @param request Search request (to access to the infant price of the airline)
     * @param pricingRule Pricing rule to calculate the prices
     * @param out PrintStream output
     */
    private void printFlightString(Flight flight, SearchRequest request, PricingRule pricingRule, PrintStream out) {

        BigDecimal fullPrice = PriceCalculator.getFullPrice(request.getPassengers(), flight.getBasePrice(), pricingRule,
                flight.getFlightNumber().getAirline().getInfantPrice());
        String msg = "\t* " + flight.getFlightNumber().getFlightNumber() + ", " + fullPrice.toPlainString() + " "
                + flight.getFlightNumber().getAirline().getCurrency();

        // All this block is something I don't very proud of. Must be recoded
        if (request.getPassengers().size() > 1) {
            String sa = null, sc = null, si = null;
            int adults = 0, children = 0, infants = 0;
            for (PassengerType p : request.getPassengers()) {
                switch (p) {
                case ADULT:
                    adults++;
                    break;
                case CHILD:
                    children++;
                    break;
                case INFANT:
                    infants++;
                    break;
                }
            }

            if (adults > 0) {
                sa = "(" + pricingRule.getRate().toPlainString() + "% of " + flight.getBasePrice().toPlainString() + ")";
                if (adults > 1) {
                    sa = adults + " * " + sa;
                }
            }
            if (children > 0) {
                sc = "67% of (" + pricingRule.getRate().toPlainString() + "% of " + flight.getBasePrice().toPlainString() + ")";
                if (children > 1) {
                    sc = children + " * " + sc;
                }
            }
            if (infants > 0) {
                si = flight.getFlightNumber().getAirline().getInfantPrice().toPlainString();
                if (infants > 1) {
                    si = infants + " * " + si;
                }
            }

            msg = msg + " (";
            if (sa != null) {
                msg = msg + " " + sa;
            }
            if (sc != null) {
                if (sa != null) {
                    msg = msg + " + " + sc;
                } else {
                    msg = msg + " " + sc;
                }
            }
            if (si != null) {
                if (sa != null || sc != null) {
                    msg = msg + " + " + si;
                } else {
                    msg = msg + " " + si;
                }
            }
            msg = msg + " )";
        }

        out.println(msg);
    }

}
