package com.github.sqlsalesproject.sale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseTest {

    @Test
    @DisplayName("Ensures the date of purchase is working correctly")
    public void testDateReturn() { //Date in form yyyy-MM-dd
        //Simple test for date that would reasonably occur
        Purchase test1 = new Purchase("2002-08-20", new ArrayList<>());
        assertEquals("2002-08-20",test1.getDateAsString());
        //Wanted to test if you could put a single digit in the different sections
        Purchase test4 = new Purchase("2002-01-01", new ArrayList<>());
        assertEquals("2002-01-01", test4.getDateAsString());
    }
    @Test
    @DisplayName("Calculation Methods test 1 - Single Product Type")
    public void testOneCalculationMethods () {
        //Initialize ArrayList with two hamburgers
        ArrayList<Product> testProductList = new ArrayList<>();
        testProductList.add(Product.getProduct("Hamburger"));
        testProductList.add(Product.getProduct("Hamburger"));
        Purchase testPurchase = new Purchase("2021-01-01", testProductList);
        assertEquals(21.98, testPurchase.getTotalSalePrice(), .001);
        assertEquals(8, testPurchase.getTotalProductionCost(), .001);
        assertEquals(13.98, testPurchase.getProfitMade(), .001);
    }
    @Test
    @DisplayName("Calculation Methods test 2 - Multiple Product Types")
    public void testTwoCalculationMethods () {
        //Initialize ArrayList with one of all product
        ArrayList<Product> testProductList = new ArrayList<>();
        testProductList.add(Product.getProduct("Hamburger"));
        testProductList.add(Product.getProduct("Chicken Sandwich"));
        testProductList.add(Product.getProduct("Chicken Strips"));
        Purchase testPurchase = new Purchase("2021-01-01", testProductList);
        assertEquals(34.97, testPurchase.getTotalSalePrice(), .001);
        assertEquals(14, testPurchase.getTotalProductionCost(), .001);
        assertEquals(20.97, testPurchase.getProfitMade(), .001);
    }
    @Test
    @DisplayName("Calculation Methods test 3 - Empty Product List")
    public void testThreeCalculationMethods () {
        Purchase testPurchase = new Purchase("2021-01-01", new ArrayList<>());
        assertEquals(0.0, testPurchase.getTotalSalePrice(), .001);
        assertEquals(0.0, testPurchase.getTotalProductionCost(), .001);
        assertEquals(0.0, testPurchase.getProfitMade(), .001);
    }
    @Test
    @DisplayName("Calculation Methods test 4 - Large Product List")
    public void testFourCalculationMethods () {
        ArrayList<Product> testProductList = new ArrayList<>();
        for (int count = 0; count < 100; count++) {
            testProductList.add(Product.getProduct("Hamburger"));
        }
        Purchase testPurchase = new Purchase("2021-01-01", testProductList);
        assertEquals(1099.0, testPurchase.getTotalSalePrice(), .001);
        assertEquals(400.0, testPurchase.getTotalProductionCost(), .001);
        assertEquals(699.0, testPurchase.getProfitMade(), .001);
    }
}