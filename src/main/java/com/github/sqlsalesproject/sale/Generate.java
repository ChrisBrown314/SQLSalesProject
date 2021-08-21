package com.github.sqlsalesproject.sale;

import com.github.sqlsalesproject.tools.OverSupplyLimitException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/** Generates objects with random values for testing purposes */
public class Generate {
    /** Random object for use in generating random values */
    private static final Random RANDOM = new Random();

    //Date Generation//
    /** Generates a date given a year and month */
    static LocalDate date (int year, int month) {
        //Ensures there aren't any errors with incorrect day month combinations
        int day = switch (month) {
            case 2 -> RANDOM.nextInt(27) + 1;
            case 4, 5, 9, 11 -> RANDOM.nextInt(29) + 1;
            default -> RANDOM.nextInt(30) + 1;
        };
        return LocalDate.of(year, month, day);
    }

    //Purchase Generation//
    /** Generates a single purchase given a year and month*/
    public static Purchase purchase (int year, int month) {
        //Generate purchase date randomly
        LocalDate dateInfo = Generate.date(year, month) ;
        //Generate Products randomly
        ArrayList<Product> productList = new ArrayList<>();
        int productCount = RANDOM.nextInt(3) + 1; // +1 ensures that there is at least 1 product
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
    /** Generates a purchase history given supply limits and date information */
    public static PurchaseHistory purchaseHistory(int supplyBurger, int supplyChicken, int year, int month) {
        PurchaseHistory generatedPH = new PurchaseHistory(supplyBurger, supplyChicken);
        boolean loop = true;
        int productCount = 0;
        int randomCondition = RANDOM.nextInt(1600)+500;
        while (productCount < randomCondition && loop) {
            try {
                generatedPH.add(Generate.purchase(year, month));
                productCount++;
            } catch (OverSupplyLimitException e) {
                loop = false;
            }
        }
        return generatedPH;
    }
}
