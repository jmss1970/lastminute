package es.jms1970.lastminute.flightsearch.exception;

@SuppressWarnings("serial")
public class AirportNotFoundException extends FlightSearchException {

    public AirportNotFoundException() {
        super();
    }

    public AirportNotFoundException(String message) {
        super(message);
    }

    public AirportNotFoundException(Throwable cause) {
        super(cause);
    }

    public AirportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
