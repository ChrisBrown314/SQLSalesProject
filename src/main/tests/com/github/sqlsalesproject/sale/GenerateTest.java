package com.github.sqlsalesproject.sale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.time.LocalDate;

class GenerateTest {
    private LocalDate generatedDate;
    private Purchase generatedPurchase;

    @BeforeEach
    void setUp() {
        generatedDate = Generate.date(2020);
        generatedPurchase = Generate.purchase(2020);
    }

    @RepeatedTest(100)
    @DisplayName("Outputs generated data just to check if everything is working properly.")
    void generatedTest () {
        System.out.println("Generated Date: " + generatedDate.toString());
        System.out.println("Total Profit Made: " + generatedPurchase.getProfitMade());
        System.out.println("Total Sale Price: " + generatedPurchase.getTotalSalePrice());
        System.out.println("Total Production Cost: " + generatedPurchase.getTotalProductionCost());
    }
}