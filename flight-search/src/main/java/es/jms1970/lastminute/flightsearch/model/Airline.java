package es.jms1970.lastminute.flightsearch.model;

import java.math.BigDecimal;

/**
 * ORM for airlines data
 * 
 * @author jmss1970@gmail.com
 */
public class Airline {

    /** IATA Code */
    private String iataCode;

    /** Airline name */
    private String name;

    /** Infant fixed price */
    private BigDecimal infantPrice;

    /** Currency */
    private String currency;

    public Airline() {
        super();
    }

    /**
     * @return IATA Code
     */
    public String getIataCode() {
        return iataCode;
    }

    /**
     * @param iataCode IATA Code
     */
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode != null ? iataCode.trim().toUpperCase() : iataCode;
    }

    /**
     * @return Airline name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Airline name
     */
    public void setName(String name) {
        this.name = name != null ? name.trim() : name;
    }

    /**
     * @return. Infant fixed price
     */
    public BigDecimal getInfantPrice() {
        return infantPrice;
    }

    /**
     * @param infantPrice Infant fixed price
     */
    public void setInfantPrice(BigDecimal infantPrice) {
        this.infantPrice = infantPrice;
    }

    /**
     * @return Currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param name Currency
     */
    public void setCurrency(String currency) {
        this.currency = currency != null ? currency.trim() : currency;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((iataCode == null) ? 0 : iataCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        Airline other = null;

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        try {
            other = (Airline) obj;
        } catch (Exception e) {
            return false;
        }

        if (iataCode == null) {
            if (other.iataCode != null) {
                return false;
            }
        } else if (!iataCode.equals(other.iataCode)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Airline [iataCode=" + iataCode + ", name=" + name + "]";
    }

    /**
     * @return Hash value for quick searches
     */
    public String getSearchValue() {
        return iataCode + ";" + name;
    }

}
