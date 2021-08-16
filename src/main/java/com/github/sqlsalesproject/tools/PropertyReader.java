package com.github.sqlsalesproject.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**Returns data from property files */
public class PropertyReader {

    /**Fetches the sale price of a product from the prices.properties file*/
    public static double fetchProductionCost(String product) {
        double productionCost = 0.0;
        try {
            //Getting Properties object set up
            InputStream inputStream = PropertyReader.class.getResourceAsStream("/config/prices.properties");
            Properties saleProperties = new Properties();
            saleProperties.load(inputStream);
            //Fetching Production Cost
            switch (product.toLowerCase()) {
                case "hamburger" -> productionCost = Double.parseDouble(saleProperties.getProperty("hamburgerCostToProduce"));
                case "chickenstrips" -> productionCost = Double.parseDouble(saleProperties.getProperty("chickenStripsCostToProduce"));
                case "chickensandwich" -> productionCost = Double.parseDouble(saleProperties.getProperty("chickenSandwichCostToProduce"));
                default -> System.err.println("ERR: Invalid product given for fetching production cost. Returning 0.0!");
            }
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
        return productionCost;
    }
    /**Fetches the sale price of a product from the prices.properties file*/
    public static double fetchSalePrice(String product) {
        double salesPrice = 0.0;
        try {
            //Getting Properties object set up
            InputStream inputStream = PropertyReader.class.getResourceAsStream("/config/prices.properties");
            Properties saleProperties = new Properties();
            saleProperties.load(inputStream);
            //Fetching Sales Price
            switch (product.toLowerCase()) {
                case "hamburger" -> salesPrice = Double.parseDouble(saleProperties.getProperty("hamburgerSalePrice"));
                case "chickenstrips" -> salesPrice = Double.parseDouble(saleProperties.getProperty("chickenStripsSalePrice"));
                case "chickensandwich" -> salesPrice = Double.parseDouble(saleProperties.getProperty("chickenSandwichSalePrice"));
                default -> System.err.println("ERR: Invalid product given for fetching sales price. Returning 0.0!");
            }
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
