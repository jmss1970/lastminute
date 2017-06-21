package es.jms1970.lastminute.flightsearch.dao.loader;

/**
 * Define the parser methods
 * 
 * @author jmss1970@gmail.com
 * @param <T> Class of the POJO container
 */
public interface DataParser<T> {

    /**
     * Parse the lina and return a new instance of T
     * 
     * @param line Data line
     * @return T instance
     */
    T parse(String line);

    /**
     * Validates if the line is good to be parsed
     * 
     * @param line Data line
     * @return <TRUE> if the line can be parsed, otherwise <FALSE>
     */
    boolean isValid(String line);

}
