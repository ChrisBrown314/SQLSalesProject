package com.github.sqlsalesproject.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**Returns data from various property files used throughout the program */
public class PropertyReader {

    /**Fetches the sale price of a file from the prices.properties file*/
    public static double fetchSalePrice(String product) {
        double salesPrice = 0.0;
        try {
            //Getting Properties object set up
            InputStream inputStream = PropertyReader.class.getResourceAsStream("/config/prices.properties");
            Properties saleProperties = new Properties();
            saleProperties.load(inputStream);
            //Fetching Sales Price
            switch(product.toLowerCase()) {
                case "hamburger":
                    salesPrice = Double.parseDouble(saleProperties.getProperty("hamburgerSalePrice"));
                    break;
                case "chickenstrips":
                    salesPrice = Double.parseDouble(saleProperties.getProperty("chickenStripsSalePrice"));
                    break;
                case "chickensandwich":
                    salesPrice = Double.parseDouble(saleProperties.getProperty("chickenSandwichSalePrice"));
                    break;
                default:
                    break;
            }
            //Closing Stream
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERR: FileNotFoundException occurred in property reader. Quitting!");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("ERR: IOException occurred in property reader. Quitting!");
            System.exit(-1);
        }
        return salesPrice;
    }
}
