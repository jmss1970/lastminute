package es.jms1970.lastminute.flightsearch.model;

/**
 * ORM for airtports data
 * 
 * @author jmss1970@gmail.com
 */
public class Airport {

    /** IATA Code */
    private String iataCode;

    /** City */
    private String city;

    public Airport() {
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
     * @return City
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city City
     */
    public void setCity(String city) {
        this.city = city != null ? city.trim() : city;
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

        Airport other = null;

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        try {
            other = (Airport) obj;
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
        return "Airport [iataCode=" + iataCode + ", city=" + city + "]";
    }

    /**
     * @return Hash value for quick searches
     */
    public String getSearchValue() {
        return iataCode + ";" + city;
    }

}
