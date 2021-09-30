package com.github.sqlsalesproject.sale;

/** Serves as a way to keep track of supplies and their cost */
public class Supply {
    /** Amount of supplies available
     * First index holds the supply of burger
     * Second index holds supply of chicken */
    private final int[] SUPPLY;
    /** Supplies used */
    private final int[] SUPPLY_USED;

    public Supply (int[] supply) {
        this.SUPPLY = supply;
        SUPPLY_USED = new int[supply.length];
    }

    /** Returns true if supplies have run out */
    public boolean suppliesExceeded() {
        for (int count = 0; count < SUPPLY.length; count++) {
            if (SUPPLY_USED[count] > SUPPLY[count]) {
                return true;
            }
        }
        return false;
    }

    /** Marks Supplies as used */
    void countUsedSupplies(int burger, int chicken) {
        SUPPLY_USED[0] += burger;
        SUPPLY_USED[1] += chicken;
    }

    /** Returns the cost of the supplies bought */
    double getSupplyCost () {
        return (SUPPLY[0]*Product.HAMBURGER.getCostToProduce())
                +(SUPPLY[1]*Product.CHICKEN_SANDWICH.getCostToProduce());
    }
}
