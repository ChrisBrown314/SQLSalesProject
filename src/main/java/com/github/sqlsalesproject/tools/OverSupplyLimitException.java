package com.github.sqlsalesproject.tools;

/** Exception thrown when a purchase is added to a purchase history that exceeds supply limits*/
public class OverSupplyLimitException extends Exception{
    public OverSupplyLimitException (String errMessage) {
        super(errMessage);
    }
}
