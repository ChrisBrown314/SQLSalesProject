package com.github.sqlsalesproject.sale;

import java.util.HashMap;
import java.util.Map;

/** Tracks supplies used. Used for the Generator class. */
public class SupplyTracker {
    // Holds all the supplies and their availability (in units)
    private static final HashMap<Supply, Double> supplyList = new HashMap<>();
    private Double supplyCost;

    public SupplyTracker (Integer suppliesPurchased) {
        supplyCost = 0.0;
        // Adds supplies to the supply list and update the supplyCost
        for(Map.Entry<String, Supply> entry : Supply.getAllSupply().entrySet()) {
            supplyList.put(entry.getValue(), (double) suppliesPurchased * entry.getValue().getNumberOfUnits());
            supplyCost += entry.getValue().getCost() * suppliesPurchased;
        }
    }

    public void countSuppliesUsed(Product productToAdd, Integer numProduct) {
        for (Map.Entry<Supply, Double> entry : productToAdd.getSuppliesRequired().entrySet()) {
            //Updates the available supplies
            System.out.println(entry.getKey());
            double supplyAvailable = supplyList.get(entry.getKey());
            supplyList.replace(entry.getKey(), (supplyAvailable - (entry.getValue() * numProduct)));
        }
    }

    public boolean suppliesExceeded() {
        for (Double s : supplyList.values()) {
            if (s <= 0) {
                return true;
            }
        }
        return false;
    }

    public Double getSupplyCost() {
        return supplyCost;
    }
}
