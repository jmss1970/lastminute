package es.jms1970.lastminute.flightsearch.model;

import java.math.BigDecimal;

/**
 * ORM for pricing rules
 * 
 * @author jmss1970@gmail.com
 */
public class PricingRule {

    /** Minimun number of days */
    private Integer minimunDays;

    /** Maximun number of days */
    private Integer maximunDays;

    /** Percentage rate */
    private BigDecimal rate;

    /** Constructor */
    public PricingRule() {
        super();
    }

    /**
     * @return Minimun number of days
     */
    public Integer getMinimunDays() {
        return minimunDays;
    }

    /**
     * @param minimunDays Minimun number of days
     */
    public void setMinimunDays(Integer minimunDays) {
        this.minimunDays = minimunDays;
    }

    /**
     * @return Maximun number of days
     */
    public Integer getMaximunDays() {
        return maximunDays;
    }

    /**
     * @param maximunDays Maximun number of days
     */
    public void setMaximunDays(Integer maximunDays) {
        this.maximunDays = maximunDays;
    }

    /**
     * @return Percentage rate
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * @param rate Percentage rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "PricingRule [minimunDays=" + minimunDays + ", maximunDays=" + maximunDays + ", rate=" + rate.toPlainString() + "]";
    }

}
