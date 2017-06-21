package es.jms1970.lastminute.flightsearch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.jms1970.lastminute.flightsearch.exception.FlightSearchException;

/**
 * Utility class for dates calculation
 * 
 * @author jmss1970@gmail.com
 */
public class DateUtility {

    /**
     * Number of seconds in a full day
     */
    static final long SECONDS_PER_DAY = 24 * 60 * 60 * 1000;
    /** Format to eliminate the time part of a date */
    static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMdd");

    private DateUtility() {
        super();
    }

    /**
     * Return one future date and today
     * 
     * @param date Future date
     * @return Number of days from now to the date
     */
    public static Integer getNumberOfDays(Date date) {

        Date today = new Date(System.currentTimeMillis());
        Date futureDate = null;

        // This code try to get rid of the time part to avoid bad calculations
        // If no time is involved, the difference between dates have to be
        // multiple of SECONDS_PER_DAY
        try {
            today = FORMAT.parse(FORMAT.format(today));
            futureDate = FORMAT.parse(FORMAT.format(date));
        } catch (ParseException e) {
            throw new FlightSearchException("Date format error", e);
        }

        long dateDiff = futureDate.getTime() - today.getTime();

        return Integer.valueOf(Long.valueOf(dateDiff / SECONDS_PER_DAY).toString());

    }

}
