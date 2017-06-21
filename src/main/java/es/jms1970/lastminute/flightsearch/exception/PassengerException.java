package es.jms1970.lastminute.flightsearch.exception;

@SuppressWarnings("serial")
public class PassengerException extends FlightSearchException {

    public PassengerException() {
        super();
    }

    public PassengerException(String message) {
        super(message);
    }

    public PassengerException(Throwable cause) {
        super(cause);
    }

    public PassengerException(String message, Throwable cause) {
        super(message, cause);
    }

}
