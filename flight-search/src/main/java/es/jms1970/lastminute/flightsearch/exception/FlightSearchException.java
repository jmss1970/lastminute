package es.jms1970.lastminute.flightsearch.exception;

@SuppressWarnings("serial")
public class FlightSearchException extends RuntimeException {

    public FlightSearchException() {
        super();
    }

    public FlightSearchException(String message) {
        super(message);
    }

    public FlightSearchException(Throwable cause) {
        super(cause);
    }

    public FlightSearchException(String message, Throwable cause) {
        super(message, cause);
    }

}
