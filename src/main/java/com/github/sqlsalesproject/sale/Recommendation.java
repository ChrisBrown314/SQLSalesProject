package com.github.sqlsalesproject.sale;

import java.util.ArrayList;

public class Recommendation {
    private final int YEAR;
    private final int MONTH;
    private int recommendedBurger;
    private int recommendedChicken;
    private final PurchaseHistory historyToAnalyze;


    public Recommendation(int year, int month, PurchaseHistory purchaseHistory) {
        YEAR = year;
        MONTH = month;
        historyToAnalyze = purchaseHistory;
        calculateRecommendations();
    }

    //TODO - Refactor this!
    private void calculateRecommendations() {
        double halfSalePrice = historyToAnalyze.getSalePrice()/2.0;
        double ratio = historyToAnalyze.getAllBurgers()/historyToAnalyze.getAllChicken();
        boolean loop = true;
        int loopCount = 0;
        while (loop) {
            double chickenStripCost = loopCount*Product.getCostToProduce("Chicken Strips");
            double burgerCost = (ratio*loopCount)*Product.getCostToProduce("Hamburger");
            if ((burgerCost+chickenStripCost)<halfSalePrice) {
                loopCount++;
            } else {
                recommendedBurger = (int)(ratio*loopCount);
                recommendedChicken = loopCount;
                loop = false;
            }
        }
    }

    public Integer getMonth() {
        return MONTH;
    }

    public Double getExpectedProfit() {
        double costToProduce = getExpectedProductionCost();
        double salePrice = getExpectedSalePrice();
        return salePrice-costToProduce;
    }
    public Double getExpectedSalePrice() {
        double burgerSales = getRecommendedBurgers()*Product.getSalePrice("Hamburger");
        double sandwichSales = (getRecommendedChicken()/2.0)*Product.getSalePrice("Chicken Sandwich");
        double stripSales = (getRecommendedChicken()/2.0)*Product.getSalePrice("Chicken Strips");
        return burgerSales+sandwichSales+stripSales;
    }
    public Double getExpectedProductionCost () {
        return (getRecommendedBurgers()*Product.getCostToProduce("Hamburger"))+(getRecommendedChicken()*Product.getCostToProduce("Chicken Sandwich"));
    }

    public Integer getRecommendedBurgers () {
        return recommendedBurger;
    }
    public Integer getRecommendedChicken () {
        return recommendedChicken;
    }
}
