package com.github.sqlsalesproject.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    @DisplayName("Ensures all sale prices and production costs are correct for all products")
    void values() {
        //Test Prices for Chicken Strips
        assertEquals(12.99, Product.CHICKEN_STRIPS.getSalePrice());
        assertEquals(5, Product.CHICKEN_STRIPS.getCostToProduce());
        //Test Prices for Chicken Sandwich
        assertEquals(10.99, Product.CHICKEN_SANDWICH.getSalePrice());
        assertEquals(5, Product.CHICKEN_SANDWICH.getCostToProduce());
        //Test Prices for Hamburgers
        assertEquals(10.99, Product.HAMBURGER.getSalePrice());
        assertEquals(4, Product.HAMBURGER.getCostToProduce());
    }
}