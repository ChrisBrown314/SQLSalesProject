package com.github.sqlsalesproject.sale;

import java.util.HashMap;

/** Serves as a way to keep track of supplies and their cost */
public class Supply {
    private static final HashMap<String, Supply> listOfSupply = new HashMap<>();
    private final Double supplyCost;  // The cost of the supply
    private final Integer numberOfUnits;  // The number of units per supply - EX: cups per bag of flower
    private final Double unitCost;  // The cost per unit

    public Supply(String supplyName, Double supplyCost, Integer numberOfUnits) {
        this.supplyCost = supplyCost;
        this.numberOfUnits = numberOfUnits;
        this.unitCost = supplyCost/numberOfUnits;
        listOfSupply.putIfAbsent(supplyName, this);
    }

    public Double getCost(Double unitReq) {
        return unitCost * unitReq;
    }

    public Double getCost() {
        return supplyCost;
    }

    public Integer getNumberOfUnits() {
        return numberOfUnits;
    }

    public static HashMap<String, Supply> getAllSupply () {
        return listOfSupply;
    }

}
