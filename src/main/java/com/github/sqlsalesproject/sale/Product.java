package com.github.sqlsalesproject.sale;

import static com.github.sqlsalesproject.tools.PropertyReader.fetchProductionCost;
import static com.github.sqlsalesproject.tools.PropertyReader.fetchSalePrice;

/** Sets up products with a toString method, sales price, and production cost */
public enum Product {
    CHICKEN_SANDWICH (fetchProductionCost("chickensandwich"), fetchSalePrice("chickensandwich"), "chicken sandwich"),
    CHICKEN_STRIPS (fetchProductionCost("chickenstrips"), fetchSalePrice("chickenstrips"),"chicken strips"),
    HAMBURGER( fetchProductionCost("hamburger"), fetchSalePrice("hamburger"),"hamburger");

    /**Constructs the enum with a sale price, production cost, and type*/
    private final double COST_TO_PRODUCE;
    private final double SALE_PRICE;
    private final String TYPE;
    Product ( double costToProduce, double salePrice, String type)
    {
        COST_TO_PRODUCE = costToProduce;
        SALE_PRICE = salePrice;
        TYPE = type;
    }
    /** Returns product's production cost */
    double getCostToProduce() {
        return COST_TO_PRODUCE;
    }
    /** Returns product's sale price */
    double getSalePrice() {
        return SALE_PRICE;
    }
    /** toString method */
    @Override
    public String toString() {
        return TYPE;
    }
}
