package com.github.sqlsalesproject.sale;

import java.util.ArrayList;
import java.util.Random;

/** Generates objects with random values for testing purposes */
public class Generate {
    //TODO - cleanup code some
    //TODO - generate purchase history
    private static final Random RANDOM = new Random();

    /** Generates a date string given a year */
    static String date (String year) {
        //In form hour:minute AM/PM month-day-year
        //PM times only to prevent somewhat odd purchase times (like 2AM)
        String hour = "" + (RANDOM.nextInt(12)+1); //1-12 hours
        String minute = "" + (RANDOM.nextInt(60)); //0-59 minutes
        String month = "" + (RANDOM.nextInt(12)+1); //1-12 months
        String day = "" + (RANDOM.nextInt(31)+1); //1-31 days
        return hour + ":" + minute + " PM " + month + "-" + day + "-" + year;
    }

    /** Generates a single purchase given a year */
    public static Purchase purchase (String year) {
        //Generate purchase date randomly
        String dateInfo = Generate.date(year) ;
        //Generate Products randomly
        ArrayList<Product> productList = new ArrayList<>();
        int productCount = RANDOM.nextInt(7) + 1; // +1 ensures that there is at least 1 product
        for (int count = 0; count < productCount; count++) {
            int product = RANDOM.nextInt(3);
            switch (product) {
                case 0 -> productList.add(Product.CHICKEN_SANDWICH);
                case 1 -> productList.add(Product.CHICKEN_STRIPS);
                case 2 -> productList.add(Product.HAMBURGER);
            }
        }
        return new Purchase(dateInfo, productList);
    }


}
