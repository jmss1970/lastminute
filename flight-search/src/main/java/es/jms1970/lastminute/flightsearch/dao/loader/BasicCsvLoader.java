package es.jms1970.lastminute.flightsearch.dao.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import es.jms1970.lastminute.flightsearch.exception.FlightSearchException;

/**
 * Basic CSV loader. It reads the file and parse each line with the appropiate parser.
 * The parser return an instance of the POJO and stores it within the collection
 * 
 * @author jmss1970@gmail.com
 * @param <T> DataParser instance
 */
public abstract class BasicCsvLoader<T extends DataParser<?>> {

    /** Line parser */
    private T parser;

    public void setParser(T parser) {
        this.parser = parser;
    }

    /**
     * Load the file and stores the data in the collection
     * 
     * @param fileName Data file name
     * @param collection List for storing the data
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void loadFile(String fileName, List collection) {

        BufferedReader reader = null;
        InputStream is = null;
        try {
            if (fileName.startsWith(System.getProperty("file.separator"))) {
                is = BasicCsvLoader.class.getResourceAsStream(fileName);
            } else {
                is = BasicCsvLoader.class.getResourceAsStream(System.getProperty("file.separator") + fileName);
            }
            String line = null;
            reader = new BufferedReader(new InputStreamReader(is));

            while ((line = reader.readLine()) != null) {
                // Validates and parse the line, then a new object is stored
                if (this.parser.isValid(line)) {
                    collection.add(this.parser.parse(line));
                }
            }

        } catch (IOException e) {
            throw new FlightSearchException("Error loading data", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new FlightSearchException("IO Exception closing " + fileName, e);
                }
            }
        }

    }
}
