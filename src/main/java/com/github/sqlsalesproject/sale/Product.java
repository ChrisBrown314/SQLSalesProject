package com.github.sqlsalesproject.sale;

import com.github.sqlsalesproject.tools.PropertyReader;

/** Sets up all possible products, as well their toString method, sales price, and cost to produce */
public enum Product {
    //TODO - make this modular/add configuration menu
    CHICKEN_SANDWICH (5, PropertyReader.fetchSalePrice("chickensandwich")) {
        @Override
        public String toString () {
            return "Catfish";
        }
    },
    CHICKEN_STRIPS (5, PropertyReader.fetchSalePrice("chickenstrips")) {
        @Override
        public String toString () {
            return "Chicken Strips";
        }
    },
    HAMBURGER (4, PropertyReader.fetchSalePrice("hamburger")) {
        @Override
        public String toString () {
            return "Hamburger";
        }
    };

    /**Constructs the enum with a price value*/
    private final double costToProduce;
    private final double salePrice;
    Product (double costToProduce, double salePrice)
    {
        this.costToProduce = costToProduce;
        this.salePrice = salePrice;
    }
    public double getCostToProduce() {
        return costToProduce;
    }
    public double getSalePrice() {
        return salePrice;
    }
}
