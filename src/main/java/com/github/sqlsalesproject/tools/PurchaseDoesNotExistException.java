package com.github.sqlsalesproject.tools;

public class PurchaseDoesNotExistException extends Exception{
    public PurchaseDoesNotExistException (String errMessage) {
        super(errMessage);
    }
}
