package com.github.sqlsalesproject.sale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class GenerateTest {
    private LocalDate generatedDate;
    private Purchase generatedPurchase;

    @BeforeEach
    void setUp() {
        generatedDate = Generate.generateDate(2020, 8);
        generatedPurchase = Generate.generatePurchase(2020, 8);
    }

    @RepeatedTest(100000)
    @DisplayName("Outputs generated data for purchases just to check if everything is working properly.")
    void generatedPurchaseTest () {
        System.out.println("Generated Date: " + generatedDate.toString());
        System.out.println("Total Profit Made: " + generatedPurchase.getProfitMade());
        System.out.println("Total Sale Price: " + generatedPurchase.getTotalSalePrice());
        System.out.println("Total Production Cost: " + generatedPurchase.getTotalProductionCost());
    }

    @Test
    @DisplayName("Outputs generated data for purchase history just to check if everything is working properly.")
    void generatedPurchaseHistoryTest() {
        PurchaseHistory generatedPH = Generate.generatePurchaseHistory(2500, 5000, 2020,
                1, 500, 1600);
        System.out.println("\nPurchase History information: ");
        System.out.println("Production cost: " + generatedPH.getProductionCost());
        System.out.println("Sale price: " + generatedPH.getSalePrice());
        System.out.println("Profit: " + generatedPH.getProfit());
        System.out.println("Supply cost: " + generatedPH.getSupplyCost());
        System.out.println("Number of Purchases: " + generatedPH.getAllPurchases().size());
        for (Purchase purchase : generatedPH.getAllPurchases()) {
            System.out.println("\nPurchase info:");
            System.out.println(purchase.getDateAsString());
            System.out.println(purchase.getNumberChicken());
            System.out.println(purchase.getNumberHamburger());
        }
    }
}