package com.github.sqlsalesproject.sale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Purchase {
    //TODO - Calculate Production and Sale Cost, error checking for empty arraylist object
    //TODO - Also calculate profit made
    private Date dateOfPurchase; //In the format of hour:minute AM/PM month-day-year
    private ArrayList<Product> productsPurchased;

    /** Constructs a purchase with given date and product list */ //TODO - Unit Tests to make sure date is working
    public Purchase(String dateOfPurchase, ArrayList<Product> productsPurchased) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a MM-dd-yyyy"); //hour:minute AM/PM month-day-year
        try {
            this.dateOfPurchase = dateFormatter.parse(dateOfPurchase);
        } catch (ParseException e) {
            System.err.println("ERR: Failed to parse Date in purchase, setting date to now!");
            this.dateOfPurchase = new Date();
        }
        this.productsPurchased = productsPurchased;
    }

    /** Returns the date and time of the purchase */
    public Date getDate() {
        return dateOfPurchase;
    }
    /** Returns the date of purchase as a string */
    public String getDateAsString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a MM-dd-yyyy");
        return dateFormatter.format(dateOfPurchase);
    }
}
