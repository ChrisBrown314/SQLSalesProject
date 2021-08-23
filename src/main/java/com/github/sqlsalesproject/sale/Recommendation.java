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

    private void calculateRecommendations() {
        double halfSalePrice = historyToAnalyze.getSalePrice()/2.0;
        double ratio = historyToAnalyze.getAllBurgers()/historyToAnalyze.getAllChicken();
        boolean loop = true;
        int loopCount = 0;
        while (loop) {
            double chickenCost = loopCount*Product.CHICKEN_STRIPS.getCostToProduce();
            double burgerCost = (ratio*loopCount)*Product.HAMBURGER.getCostToProduce();
            if ((burgerCost+chickenCost)<halfSalePrice) {
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
        double burgerSales = getRecommendedBurgers()*Product.HAMBURGER.getSalePrice();
        double sandwichSales = (getRecommendedChicken()/2.0)*Product.CHICKEN_SANDWICH.getSalePrice();
        double stripSales = (getRecommendedChicken()/2.0)*Product.CHICKEN_STRIPS.getSalePrice();
        return burgerSales+sandwichSales+stripSales;
    }
    public Double getExpectedProductionCost () {
        return (getRecommendedBurgers()*Product.HAMBURGER.getCostToProduce())+(getRecommendedChicken()*Product.CHICKEN_SANDWICH.getCostToProduce());
    }

    public Integer getRecommendedBurgers () {
        return recommendedBurger;
    }
    public Integer getRecommendedChicken () {
        return recommendedChicken;
    }
}
