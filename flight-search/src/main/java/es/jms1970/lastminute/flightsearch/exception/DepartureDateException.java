package es.jms1970.lastminute.flightsearch.exception;

@SuppressWarnings("serial")
public class DepartureDateException extends FlightSearchException {

    public DepartureDateException() {
        super();
    }

    public DepartureDateException(String message) {
        super(message);
    }

    public DepartureDateException(Throwable cause) {
        super(cause);
    }

    public DepartureDateException(String message, Throwable cause) {
        super(message, cause);
    }

}
