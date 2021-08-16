package com.github.sqlsalesproject.sale;

import com.github.sqlsalesproject.tools.PropertyReader;

/** Sets up products with a toString method, sales price, and production cost */
public enum Product {
    CHICKEN_SANDWICH (PropertyReader.fetchProductionCost("chickensandwich"), PropertyReader.fetchSalePrice("chickensandwich")) {
        @Override
        public String toString () {
            return "Chicken Sandwich";
        }
    },
    CHICKEN_STRIPS (PropertyReader.fetchProductionCost("chickenstrips"), PropertyReader.fetchSalePrice("chickenstrips")) {
        @Override
        public String toString () {
            return "Chicken Strips";
        }
    },
    HAMBURGER(PropertyReader.fetchProductionCost("hamburger"), PropertyReader.fetchSalePrice("hamburger")) {
        @Override
        public String toString () {
            return "Hamburger";
        }
    };

    /**Constructs the enum with a sale price and production cost*/
    private final double costToProduce;
    private final double salePrice;
    Product (double costToProduce, double salePrice)
    {
        this.costToProduce = costToProduce;
        this.salePrice = salePrice;
    }
    /** Returns product's production cost */
    double getCostToProduce() {
        return costToProduce;
    }
    /** Returns product's sale price */
    double getSalePrice() {
        return salePrice;
    }
}
