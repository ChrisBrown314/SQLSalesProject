package com.github.sqlsalesproject.sale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseTest {

    @Test
    @DisplayName("Ensures the date of purchase is working correctly")
    public void testDateReturn() {
        //Simple test for date that would reasonably occur
        Purchase test1 = new Purchase("8:20 PM 08-20-2002", new ArrayList<>());
        assertEquals("8:20 PM 08-20-2002",test1.getDateAsString());
        //Wanted to make sure multi-digit hours still worked with only 1 h in pattern string
        Purchase test2 = new Purchase("12:20 AM 12-01-2021", new ArrayList<>());
        assertEquals("12:20 AM 12-01-2021", test2.getDateAsString());
        //Was curious how using numbers outside of hour and month range would affect output
        Purchase test3 = new Purchase("14:10 AM 13-10-2028", new ArrayList<>());
        assertEquals("2:10 PM 01-10-2029",test3.getDateAsString());
    }
    @Test
    @DisplayName("Calculation Methods test 1 - Single Product Type")
    public void testOneCalculationMethods () {
        //Initialize ArrayList with two hamburgers
        ArrayList<Product> testProductList = new ArrayList<>();
        testProductList.add(Product.HAMBURGER);
        testProductList.add(Product.HAMBURGER);
        Purchase testPurchase = new Purchase("10:00 AM 1-1-2021", testProductList);
        assertEquals(21.98, testPurchase.getTotalSalePrice(), .001);
        assertEquals(8, testPurchase.getTotalProductionCost(), .001);
        assertEquals(13.98, testPurchase.getProfitMade(), .001);
    }
    @Test
    @DisplayName("Calculation Methods test 2 - Multiple Product Types")
    public void testTwoCalculationMethods () {
        //Initialize ArrayList with one of all product
        ArrayList<Product> testProductList = new ArrayList<>();
        testProductList.add(Product.HAMBURGER);
        testProductList.add(Product.CHICKEN_SANDWICH);
        testProductList.add(Product.CHICKEN_STRIPS);
        Purchase testPurchase = new Purchase("10:00 AM 1-1-2021", testProductList);
        assertEquals(34.97, testPurchase.getTotalSalePrice(), .001);
        assertEquals(14, testPurchase.getTotalProductionCost(), .001);
        assertEquals(20.97, testPurchase.getProfitMade(), .001);
    }
    @Test
    @DisplayName("Calculation Methods test 3 - Empty Product List")
    public void testThreeCalculationMethods () {
        Purchase testPurchase = new Purchase("10:00 AM 1-1-2021", new ArrayList<>());
        assertEquals(0.0, testPurchase.getTotalSalePrice(), .001);
        assertEquals(0.0, testPurchase.getTotalProductionCost(), .001);
        assertEquals(0.0, testPurchase.getProfitMade(), .001);
    }
    @Test
    @DisplayName("Calculation Methods test 4 - Large Product List")
    public void testFourCalculationMethods () {
        ArrayList<Product> testProductList = new ArrayList<>();
        for (int count = 0; count < 100; count++) {
            testProductList.add(Product.HAMBURGER);
        }
        Purchase testPurchase = new Purchase("10:00 AM 1-1-2021", testProductList);
        assertEquals(1099.0, testPurchase.getTotalSalePrice(), .001);
        assertEquals(400.0, testPurchase.getTotalProductionCost(), .001);
        assertEquals(699.0, testPurchase.getProfitMade(), .001);
    }
}