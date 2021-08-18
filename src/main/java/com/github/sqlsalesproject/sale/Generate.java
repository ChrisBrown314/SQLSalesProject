package com.github.sqlsalesproject.sale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/** Generates objects with random values for testing purposes */
public class Generate {
    //TODO - cleanup code some
    //TODO - generate purchase history
    //TODO - Fix with new date info
    private static final Random RANDOM = new Random();

    /** Generates a date given a year */
    static LocalDate date (int year) {
        int month = RANDOM.nextInt(11)+1;
        //Ensures there aren't any errors with incorrect day month combinations
        int day = switch (month) {
            case 2 -> RANDOM.nextInt(27) + 1;
            case 4, 5, 9, 11 -> RANDOM.nextInt(29) + 1;
            default -> RANDOM.nextInt(30) + 1;
        };
        return LocalDate.of(year, month, day);
    }

    /** Generates a single purchase given a year */
    public static Purchase purchase (int year) {
        //Generate purchase date randomly
        LocalDate dateInfo = Generate.date(year) ;
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
