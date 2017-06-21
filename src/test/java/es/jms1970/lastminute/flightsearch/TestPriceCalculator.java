package es.jms1970.lastminute.flightsearch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import es.jms1970.lastminute.flightsearch.bean.PassengerType;
import es.jms1970.lastminute.flightsearch.model.PricingRule;
import junit.framework.Assert;

/**
 * Test the price calculator
 * 
 * @author jmss1970@gmail.com
 */
public class TestPriceCalculator {

    private List<PassengerType> passengers = new ArrayList<PassengerType>();
    private BigDecimal basePrice = new BigDecimal("100.00"), infantPrice = new BigDecimal("25");
    private PricingRule mockRule = new PricingRule();

    @Test
    public void testOneAdult() {

        BigDecimal expectedPrice = null, fullPrice = null;
        this.mockRule.setRate(new BigDecimal("120"));

        expectedPrice = getExpectedPrice(this.basePrice, this.mockRule.getRate(), PassengerType.ADULT);

        this.passengers.clear();
        this.passengers.add(PassengerType.ADULT);
        fullPrice = PriceCalculator.getFullPrice(this.passengers, this.basePrice, this.mockRule, this.infantPrice);
        Assert.assertEquals(fullPrice, expectedPrice);

    }

    @Test
    public void testOneChild() {

        BigDecimal expectedPrice = null, fullPrice = null;
        this.mockRule.setRate(new BigDecimal("75"));

        expectedPrice = getExpectedPrice(this.basePrice, this.mockRule.getRate(), PassengerType.CHILD);

        this.passengers.clear();
        this.passengers.add(PassengerType.CHILD);
        fullPrice = PriceCalculator.getFullPrice(this.passengers, this.basePrice, this.mockRule, this.infantPrice);
        Assert.assertEquals(fullPrice, expectedPrice);

    }

    @Test
    public void testOneInfant() {

        BigDecimal expectedPrice = null, fullPrice = null;
        this.mockRule.setRate(new BigDecimal("37"));

        expectedPrice = this.infantPrice;

        this.passengers.clear();
        this.passengers.add(PassengerType.INFANT);
        fullPrice = PriceCalculator.getFullPrice(this.passengers, this.basePrice, this.mockRule, this.infantPrice);
        Assert.assertEquals(fullPrice, expectedPrice);

    }

    @Test
    public void testAdults() {

        BigDecimal expectedPrice = null, fullPrice = null;
        Random random = new Random();
        int numberOfAdults = random.nextInt(10) + 1;

        this.passengers.clear();
        for (int i = 0; i < numberOfAdults; i++) {
            this.passengers.add(PassengerType.ADULT);
        }

        this.mockRule.setRate(new BigDecimal("50"));

        expectedPrice = getExpectedPrice(this.basePrice, this.mockRule.getRate(), PassengerType.ADULT).multiply(BigDecimal.valueOf(numberOfAdults));

        fullPrice = PriceCalculator.getFullPrice(this.passengers, this.basePrice, this.mockRule, this.infantPrice);
        Assert.assertEquals(fullPrice, expectedPrice);

    }

    @Test
    public void testChildren() {

        BigDecimal expectedPrice = null, fullPrice = null;
        Random random = new Random();
        int numberOfChildren = random.nextInt(10) + 1;

        this.passengers.clear();
        for (int i = 0; i < numberOfChildren; i++) {
            this.passengers.add(PassengerType.CHILD);
        }

        this.mockRule.setRate(new BigDecimal("150"));

        expectedPrice = getExpectedPrice(this.basePrice, this.mockRule.getRate(), PassengerType.CHILD).multiply(BigDecimal.valueOf(numberOfChildren));

        fullPrice = PriceCalculator.getFullPrice(this.passengers, this.basePrice, this.mockRule, this.infantPrice);
        Assert.assertEquals(fullPrice, expectedPrice);

    }

    @Test
    public void testInfants() {

        BigDecimal expectedPrice = null, fullPrice = null;
        Random random = new Random();
        int numberOfInfants = random.nextInt(10) + 1;

        this.passengers.clear();
        for (int i = 0; i < numberOfInfants; i++) {
            this.passengers.add(PassengerType.INFANT);
        }

        this.mockRule.setRate(new BigDecimal("150"));

        expectedPrice = this.infantPrice.multiply(BigDecimal.valueOf(numberOfInfants));

        fullPrice = PriceCalculator.getFullPrice(this.passengers, this.basePrice, this.mockRule, this.infantPrice);
        Assert.assertEquals(fullPrice, expectedPrice);

    }

    @Test
    public void testBigFamily() {

        BigDecimal expectedPrice = null, fullPrice = null;

        this.passengers.clear();
        // 2 Adults
        this.passengers.add(PassengerType.ADULT);
        this.passengers.add(PassengerType.ADULT);
        // 2 Children
        this.passengers.add(PassengerType.CHILD);
        this.passengers.add(PassengerType.CHILD);
        // 2 Infants
        this.passengers.add(PassengerType.INFANT);
        this.passengers.add(PassengerType.INFANT);

        this.mockRule.setRate(new BigDecimal("120"));

        // Adults
        expectedPrice = getExpectedPrice(this.basePrice, this.mockRule.getRate(), PassengerType.ADULT).multiply(BigDecimal.valueOf(2));
        expectedPrice = expectedPrice
                .add(getExpectedPrice(this.basePrice, this.mockRule.getRate(), PassengerType.CHILD).multiply(BigDecimal.valueOf(2)));
        expectedPrice = expectedPrice.add(this.infantPrice.multiply(BigDecimal.valueOf(2)));

        fullPrice = PriceCalculator.getFullPrice(this.passengers, this.basePrice, this.mockRule, this.infantPrice);
        Assert.assertEquals(fullPrice, expectedPrice);

    }

    private BigDecimal getExpectedPrice(BigDecimal base, BigDecimal percentaje, PassengerType passenger) {

        BigDecimal price = null;

        switch (passenger) {
        case ADULT:
            price = base.multiply(percentaje).divide(PriceCalculator.ONE_HUNDRED, 2, RoundingMode.HALF_UP);
            break;
        case CHILD:
            BigDecimal tmp = base.multiply(percentaje).divide(PriceCalculator.ONE_HUNDRED, 2, RoundingMode.HALF_UP);
            BigDecimal discount = tmp.multiply(PriceCalculator.DISCOUNT).divide(PriceCalculator.ONE_HUNDRED, 2, RoundingMode.HALF_UP);
            price = tmp.subtract(discount);
            break;
        case INFANT:
            price = base;
        }
        return price;

    }

}
