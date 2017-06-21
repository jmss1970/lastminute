package es.jms1970.lastminute.flightsearch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import es.jms1970.lastminute.flightsearch.bean.PassengerType;
import es.jms1970.lastminute.flightsearch.model.PricingRule;

/**
 * Utility class used to work out the prices
 * 
 * @author jmss1970@gmail.com
 */
public class PriceCalculator {

    /**
     * Used to calculate percentages
     */
    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100.00");
    /**
     * Standard discount for children. It should be in a repostory
     */
    public static final BigDecimal DISCOUNT = new BigDecimal("33.00");

    private PriceCalculator() {
        super();
    }

    /**
     * Calculate the full price of a flight, based on the passenger list, the flight base price and the pricing rule
     * 
     * @param passengers List of passengers. Can't be empty
     * @param basePrice Flight base price
     * @param rule Pricing rule to apply
     * @param infantPrice Fixed price for infants.
     * @return Full price of the flight
     */
    public static BigDecimal getFullPrice(List<PassengerType> passengers, BigDecimal basePrice, PricingRule rule, BigDecimal infantPrice) {

        BigDecimal fullPrice = BigDecimal.ZERO;

        for (PassengerType passenger : passengers) {
            // Just adds the price for each passenger
            fullPrice = fullPrice.add(getPrice(passenger, basePrice, rule, infantPrice));
        }

        return fullPrice;

    }

    /**
     * Calculate the price of a flight for a passenger, based on the flight base price and the pricing rule
     * 
     * @param passenge Type of passenger
     * @param basePrice Flight base price
     * @param rule Pricing rule to apply
     * @param infantPrice Fixed price for infants.
     * @return Price for the passenger
     */
    public static BigDecimal getPrice(PassengerType passenger, BigDecimal basePrice, PricingRule rule, BigDecimal infantPrice) {

        BigDecimal price = BigDecimal.ZERO;

        switch (passenger) {
        case ADULT:
            price = basePrice.multiply(rule.getRate()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
            break;
        case CHILD:
            BigDecimal tmp = basePrice.multiply(rule.getRate()).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
            BigDecimal discount = tmp.multiply(DISCOUNT).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
            price = tmp.subtract(discount);
            break;
        case INFANT:
            price = infantPrice;
        }
        return price;
    }
}
