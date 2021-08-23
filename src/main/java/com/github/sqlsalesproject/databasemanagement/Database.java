package com.github.sqlsalesproject.databasemanagement;

import com.github.sqlsalesproject.sale.Product;
import com.github.sqlsalesproject.sale.Purchase;
import com.github.sqlsalesproject.sale.PurchaseHistory;
import com.github.sqlsalesproject.tools.PurchaseDoesNotExistException;

import java.sql.*;
import java.util.ArrayList;


public class Database {
    //Program requires mysql server set up on port 3306 with a user having this username and
    //password and a salesproject schema
    private static final String CONNECTION_INFO = "jdbc:mysql://localhost:3306/salesproject";
    private static final String DB_USERNAME = "salesproject";
    private static final String DB_PASSWORD = "sR3G>[d2ZWqF6p54";
    private static Connection Connection;


    //Singleton Implementation//
    private static Database databaseInstance = null;
    /** Connects to Database upon instantiation */
    private Database() {
        try {
            Connection = DriverManager.getConnection(CONNECTION_INFO,DB_USERNAME, DB_PASSWORD);
            //Check if table exists for purchase data and if not, creates it
            if (!purchaseTableExists()) {
                createPurchaseTable();
            }
            if (!historyTableExists()) {
                createHistoryTable();
            }
        } catch (SQLException sqlException) {
            //TODO logging and better error handling
            System.err.println("ERR: Failed to connect to SQL database. Quitting!");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }
    }
    /** Returns Singleton instance of the Database class */
    public static Database getDatabase() {
        if(databaseInstance == null) {
            databaseInstance = new Database();
        }
        return databaseInstance;
    }


    //Table Handling//
    /** Returns true if the purchase table exists */
    private boolean purchaseTableExists() throws SQLException {
        Statement statement = Connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT EXISTS( SELECT * FROM " +
                "information_schema.TABLES WHERE TABLE_NAME = 'purchase');");
        resultSet.next(); //Have to do this prior to reading the result set, or the program errors
        boolean exists = (resultSet.getInt(1) == 1);
        resultSet.close();
        statement.close();
        return exists;
    }
    /** Creates the purchase table in the database */
    private void createPurchaseTable() throws SQLException {
        Statement statement = Connection.createStatement();
        statement.executeUpdate("CREATE TABLE purchase ( date TINYTEXT NOT NULL, month INT NOT NULL," +
                " burger INT NOT NULL, sandwich INT NOT NULL, strip INT NOT NULL);");
        statement.close();

    }
    /** Checks if the history table exists */
    private boolean historyTableExists() throws SQLException {
        Statement statement = Connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT EXISTS( SELECT * FROM " +
                "information_schema.TABLES WHERE TABLE_NAME = 'history');");
        resultSet.next(); //Have to do this prior to reading the result set, or the program errors
        boolean exists = (resultSet.getInt(1) == 1);
        resultSet.close();
        statement.close();
        return exists;
    }
    /** Creates the history table in the database*/
    private void createHistoryTable() throws SQLException {
        Statement statement = Connection.createStatement();
        statement.executeUpdate("CREATE TABLE history ( month INT NOT NULL," +
                "year INT NOT NULL, supplyCost DOUBLE NOT NULL);");
        statement.close();

    }
    /** Deletes all data from the database*/
    public void clearTables() throws SQLException {
        Statement statement = Connection.createStatement();
        statement.executeUpdate("DELETE FROM purchase");
        statement.executeUpdate("DELETE FROM history");
        statement.close();
    }

    // Writing to Database //
    /** Writes a purchase to the purchase table in the database */
    private void writePurchase(Purchase purchase) throws SQLException {
        Statement statement = Connection.createStatement();
        statement.executeUpdate("INSERT INTO purchase VALUES ('" +
                purchase.getDateAsString() + "', " +
                purchase.getDate().getMonthValue() + ", " +
                purchase.getNumberHamburger()+ ", " +
                purchase.getNumberSandwich()+ ", " +
                purchase.getNumberStrip() + ");");
        statement.close();
    }
    /** Writes a purchase history to the database */
    public void writePurchaseHistory (PurchaseHistory purchaseHistory) throws SQLException {
        Statement statement = Connection.createStatement();
        statement.executeUpdate("INSERT INTO history VALUES (" +
                purchaseHistory.getMonth() + ", " +
                purchaseHistory.getYear() + ", " +
                purchaseHistory.getSupplyCost() +");");
        for (Purchase purchase : purchaseHistory.getAllPurchases()) {
            writePurchase(purchase);
        }
        statement.close();
    }


    // Fetching Purchase History //
    /**Fetch the Purchase History for a given month from the database*/
    public PurchaseHistory fetchPurchaseHistory(int month, int year) throws PurchaseDoesNotExistException{
        if (historyDataExists(month)) {
            ArrayList<Purchase> purchaseList = fetchPurchaseData(month);
            double supplyCost = fetchSupplyCost(month);
            return new PurchaseHistory(supplyCost, month, year, purchaseList);
        } else {
            System.err.println("ERR: Tried to fetch purchase history data that did not exist!");
            throw new PurchaseDoesNotExistException("Purchase History doesn't exist!");
        }
    }
    /** Checks if the given Purchase History Data exists in the database */
    private boolean historyDataExists(int month) {
        try {
            Statement statement = Connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT EXISTS( SELECT * FROM " +
                    "history WHERE month = "+ month +");");
            resultSet.next(); //Have to do this prior to reading the result set, or the program errors
            boolean exists = (resultSet.getInt(1) == 1);
            resultSet.close();
            statement.close();
            return exists;
        } catch (SQLException sqlException) {
            System.err.println("ERR: Failure to check existence of purchase history! Returning false!");
            System.err.println(sqlException.getMessage());
            return false;
        }
    }
    /** Fetches the purchase history's supply cost from the history table*/
    private double fetchSupplyCost (int month) {
        try {
            double supplyCost = 0.0;
            Statement statement = Connection.createStatement();
            ResultSet historyInformation = statement.executeQuery("SELECT * FROM " +
                    "history WHERE month = " + month);
            historyInformation.next();
            supplyCost = historyInformation.getDouble(3);
            historyInformation.close();
            statement.close();
            return  supplyCost;
        } catch (SQLException sqlException) {
            System.err.println("ERR: Failed to fetch supply cost! Returning 0.0!");
            System.err.println(sqlException.getMessage());
            return 0.0;
        }
    }
    /** Fetches a list of purchases from a given month */
    private ArrayList<Purchase> fetchPurchaseData(int month) {
        ArrayList<Purchase> purchaseList = new ArrayList<>();
        try {
            Statement statement = Connection.createStatement();
            ResultSet purchaseInformation = statement.executeQuery("SELECT * FROM " +
                    "purchase WHERE month = " + month);
            while (purchaseInformation.next()) {
                String purchaseDate = purchaseInformation.getString(1);
                int burger = purchaseInformation.getInt(3);
                int strip = purchaseInformation.getInt(4);
                int sandwich = purchaseInformation.getInt(5);
                ArrayList<Product> productList = new ArrayList<>();
                while (burger != 0) {
                    productList.add(Product.HAMBURGER);
                    burger--;
                }
                while (strip != 0) {
                    productList.add(Product.CHICKEN_STRIPS);
                    strip--;
                }
                while (sandwich != 0) {
                    productList.add(Product.CHICKEN_SANDWICH);
                    sandwich--;
                }
                purchaseList.add(new Purchase(purchaseDate, productList));
            }
            purchaseInformation.close();
            statement.close();
        } catch (SQLException sqlException) {
            System.err.println("ERR: Failed to fetch purchase data!");
            System.err.println(sqlException.getMessage());
        }
        return purchaseList;
    }

    public ArrayList<Integer> getAllYears() {
        try {
            Statement statement = Connection.createStatement();
            ResultSet yearInfo = statement.executeQuery("SELECT DISTINCT year FROM history");
            ArrayList<Integer> yearList = new ArrayList<>();
            while (yearInfo.next()) {
                yearList.add(yearInfo.getInt(1));
            }
            yearInfo.close();
            statement.close();
            return yearList;
        } catch (SQLException sqlException) {
            System.err.println("ERR: Failed to get list of years! Returning empty array list!");
            System.err.println(sqlException.getMessage());
        }
        return new ArrayList<>();
    }
}
