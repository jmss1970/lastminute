package es.jms1970.lastminute.flightsearch;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import es.jms1970.lastminute.flightsearch.bean.PassengerType;
import es.jms1970.lastminute.flightsearch.bean.SearchRequest;
import es.jms1970.lastminute.flightsearch.bean.SearchResponse;
import es.jms1970.lastminute.flightsearch.dao.AirportDao;
import es.jms1970.lastminute.flightsearch.dao.AirportDaoMethods;
import es.jms1970.lastminute.flightsearch.dao.loader.AirportLoader;
import es.jms1970.lastminute.flightsearch.dao.loader.FlightLoader;
import es.jms1970.lastminute.flightsearch.exception.AirportNotFoundException;
import es.jms1970.lastminute.flightsearch.exception.DepartureDateException;
import es.jms1970.lastminute.flightsearch.exception.FlightSearchException;
import es.jms1970.lastminute.flightsearch.exception.PassengerException;
import es.jms1970.lastminute.flightsearch.model.Airport;
import es.jms1970.lastminute.flightsearch.model.Flight;
import junit.framework.Assert;

/**
 * Fligh search test case
 * testSearchFlights goal: load all the flights and create a search request for each one. Try those searches
 * and proof that each search return exactly the available flights.
 * 
 * @author jmss1970@gmail.com
 */
public class TestFlightSearch {

    // List of valid request (different origin and destination airport) loaded from data file
    private List<SearchRequest> requests;
    // Map with origin and destination airpors as key and the number of valid flights as value. For checking the searches
    private Map<String, Integer> numberOfFlighsPerRequest = new HashMap<String, Integer>();
    // Just an instance
    private FlightSearcher searcher = new FlightSearcher();

    /**
     * Loads the map which collects the number of available flights (gruopped vy origin and destination, regardless
     * airline)
     * Loads the list of request with all the available flights.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        GregorianCalendar gc = new GregorianCalendar();
        String criteria = null;
        List<Flight> availableFlights = new ArrayList<Flight>();
        FlightLoader loader = new FlightLoader();
        Random random = new Random();
        int aNumber = 0, another = 0;

        // Load flights data from the file
        loader.loadFile(ConfigManager.getValue(ApplicationKeys.FLIGHTS_FILE), availableFlights);
        this.requests = new ArrayList<SearchRequest>();

        for (Flight f : availableFlights) {

            // Create a request for the flight
            SearchRequest request = new SearchRequest();

            // Set random number of days up to 60 
            aNumber = random.nextInt(61);
            gc.setTimeInMillis(System.currentTimeMillis());
            gc.add(GregorianCalendar.DATE, aNumber);

            request.setDepartureDate(gc.getTime());
            request.setDestinationAirport(f.getDestination());
            request.setOriginAirport(f.getOrigin());

            // Adds random passengers
            aNumber = random.nextInt(3) + 1;
            for (int i = 0; i <= aNumber; i++) {
                another = random.nextInt(3);
                for (PassengerType p : PassengerType.values()) {
                    if (p.ordinal() == another) {
                        request.addPassengers(p);
                    }
                }
            }

            // Avoids bad requests to get rid of validation exceptions
            if (request.isValid()) {

                requests.add(request);

                // Increase the flights counter for origin and destination airport
                criteria = f.getOrigin().getIataCode() + ";" + f.getDestination().getIataCode();

                if (!this.numberOfFlighsPerRequest.containsKey(criteria)) {
                    this.numberOfFlighsPerRequest.put(criteria, 0);
                }

                this.numberOfFlighsPerRequest.put(criteria, this.numberOfFlighsPerRequest.get(criteria) + 1);
            }
        }

    }

    @Test
    /**
     * Test the validation of the requests
     */
    public final void testValidation() {
        SearchRequest request = new SearchRequest();
        GregorianCalendar gc = new GregorianCalendar();
        AirportDaoMethods airportDao = new AirportDao();

        gc.add(GregorianCalendar.DATE, -1);

        request.setDepartureDate(gc.getTime());
        request.setDestinationAirport(airportDao.findByIataCode("BCN"));
        request.setOriginAirport(airportDao.findByIataCode("MAD"));
        request.addPassengers(PassengerType.ADULT);

        try {
            this.searcher.searchFlights(request);
            Assert.fail("Date previous than today must be controlled");
        } catch (DepartureDateException e) {
            System.out.println("Previous date raise and exception");
        }
        gc.add(GregorianCalendar.DATE, -10);
        try {
            this.searcher.searchFlights(request);
            Assert.fail("Date previous than today must be controlled");
        } catch (DepartureDateException e) {
            System.out.println("Previous date raise and exception");
        }

        request.setDepartureDate(gc.getTime());

        gc.add(GregorianCalendar.DATE, 30);
        request.setDepartureDate(gc.getTime());
        request.setDestinationAirport(null);
        request.setOriginAirport(airportDao.findByIataCode("MAD"));
        request.addPassengers(PassengerType.ADULT);

        try {
            this.searcher.searchFlights(request);
            Assert.fail("Destination airport must be controlled");
        } catch (AirportNotFoundException e) {

        }

        request.setDestinationAirport(airportDao.findByIataCode("MAD"));
        request.setOriginAirport(null);
        request.addPassengers(PassengerType.ADULT);
        try {
            this.searcher.searchFlights(request);
            Assert.fail("Origin airport must be controlled");
        } catch (AirportNotFoundException e) {

        }

        request.setDestinationAirport(airportDao.findByIataCode("MAD"));
        request.setOriginAirport(airportDao.findByIataCode("MAD"));
        request.addPassengers(PassengerType.ADULT);
        try {
            this.searcher.searchFlights(request);
            Assert.fail("Same airports must be controlled");
        } catch (AirportNotFoundException e) {

        }

        // New request
        request = new SearchRequest();
        request.setDepartureDate(gc.getTime());
        request.setDestinationAirport(airportDao.findByIataCode("BCN"));
        request.setOriginAirport(airportDao.findByIataCode("MAD"));

        try {
            this.searcher.searchFlights(request);
            Assert.fail("Lack of passengers must be controlled");
        } catch (PassengerException e) {

        }
    }

    @Test
    /**
     * Proof the searches of available flights returns all the available flights
     */
    public final void testFlightSearch() {

        SearchResponse response = null;
        String criteria = null;
        Integer counter = 0;
        SearchReponseWriter writer = new SearchReponseWriter();

        for (SearchRequest request : this.requests) {
            response = this.searcher.searchFlights(request);

            if (response.getAvailableFlights().isEmpty()) {
                Assert.fail("Should be an available flight for the request!");
            }
            criteria = request.getOriginAirport().getIataCode() + ";" + request.getDestinationAirport().getIataCode();
            counter = this.numberOfFlighsPerRequest.get(criteria);

            if (response.getAvailableFlights().size() != counter) {
                Assert.fail("Search doesn't find all the available flights!");
            }

            writer.writeResponse(response, System.out);

        }

    }

    /**
     * Test a not found flight
     */
    @Test
    public final void testFlightNotFound() {

        SearchResponse response = null;
        SearchReponseWriter writer = new SearchReponseWriter();
        AirportDao airportDao = new AirportDao();

        SearchRequest request = new SearchRequest();

        request.setDepartureDate(new Date(System.currentTimeMillis()));
        request.setDestinationAirport(airportDao.findByIataCode("IST"));
        request.setOriginAirport(airportDao.findByIataCode("MAD"));
        request.addPassengers(PassengerType.ADULT);

        response = this.searcher.searchFlights(request);

        if (!response.getAvailableFlights().isEmpty()) {
            Assert.fail("Flight does not exist!");
        }
        writer.writeResponse(response, System.out);

    }

    /**
     * Tests all the combinations of airports and checks whether there is available flight or not.
     */
    @Test
    public final void testAllChances() {

        SearchResponse response = null;
        SearchReponseWriter writer = new SearchReponseWriter();
        AirportLoader aLoader = new AirportLoader();
        FlightLoader fLoader = new FlightLoader();
        List<Airport> origin = new ArrayList<Airport>();
        List<Airport> destination = new ArrayList<Airport>();
        List<Flight> availableFlights = new ArrayList<Flight>();
        Boolean found = Boolean.FALSE;

        fLoader.loadFile(ConfigManager.getValue(ApplicationKeys.FLIGHTS_FILE), availableFlights);

        aLoader.loadFile(ConfigManager.getValue(ApplicationKeys.AIRPORTS_FILE), origin);
        destination.addAll(origin);

        SearchRequest request = new SearchRequest();

        request.setDepartureDate(new Date(System.currentTimeMillis()));
        request.addPassengers(PassengerType.ADULT);

        for (Airport o : origin) {
            request.setOriginAirport(o);
            for (Airport d : destination) {
                request.setDestinationAirport(d);
                try {
                    response = this.searcher.searchFlights(request);
                    writer.writeResponse(response, System.out);

                    found = Boolean.FALSE;
                    for (Flight f : availableFlights) {
                        if (f.getOrigin().equals(o) && f.getDestination().equals(d)) {
                            found = Boolean.TRUE;
                            if (response.getAvailableFlights().isEmpty()) {
                                Assert.fail("Flight not found!");
                            }
                            break;
                        }
                    }

                    if (!found && !response.getAvailableFlights().isEmpty()) {
                        Assert.fail("Flight not available found!");
                    }

                } catch (FlightSearchException f) {
                    if (!(o.equals(d) && f instanceof AirportNotFoundException)) {
                        Assert.fail(f.getLocalizedMessage());
                    }
                } catch (Exception e) {
                    Assert.fail(e.getLocalizedMessage());
                }
            }
        }

    }

}
