package com.github.sqlsalesproject.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {
    //TODO - toString test
    @Test
    @DisplayName("Ensures all Production Costs are correct for all products")
    void productionCost() {
        assertEquals(5, Product.CHICKEN_STRIPS.getCostToProduce());
        assertEquals(5, Product.CHICKEN_SANDWICH.getCostToProduce());
        assertEquals(4, Product.HAMBURGER.getCostToProduce());
    }
    @Test
    @DisplayName("Ensures all sale prices are correct for all products")
    void salesPrice() {
        assertEquals(12.99, Product.CHICKEN_STRIPS.getSalePrice());
        assertEquals(10.99, Product.CHICKEN_SANDWICH.getSalePrice());
        assertEquals(10.99, Product.HAMBURGER.getSalePrice());
    }


}