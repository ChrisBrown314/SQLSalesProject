package com.github.sqlsalesproject.sale;

import com.github.sqlsalesproject.tools.PropertyReader;

/** Sets up all possible products, as well their toString method, sales price, and cost to produce */
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

    /**Constructs the enum with a sale price and production cost value*/
    private final double costToProduce;
    private final double salePrice;
    Product (double costToProduce, double salePrice)
    {
        this.costToProduce = costToProduce;
        this.salePrice = salePrice;
    }
    /** Returns cost to produce the product */
    public double getCostToProduce() {
        return costToProduce;
    }
    /** Returns sale price of the product */
    public double getSalePrice() {
        return salePrice;
    }
}
