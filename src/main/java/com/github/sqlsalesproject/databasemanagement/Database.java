package com.github.sqlsalesproject.databasemanagement;

import com.github.sqlsalesproject.sale.Product;
import com.github.sqlsalesproject.sale.Purchase;
import com.github.sqlsalesproject.sale.PurchaseHistory;
import com.github.sqlsalesproject.tools.PurchaseDoesNotExistException;

import java.sql.*;
import java.util.ArrayList;


public class Database {
    //Program requires mysql server set up on port 3306 with a user having this username and
    //password and a salesproject schema1
    private static final String CONNECTION_INFO = "jdbc:mysql://localhost:3306/salesproject";
    private static final String DB_USERNAME = "salesproject";
    private static final String DB_PASSWORD = "sR3G>[d2ZWqF6p54";
    private static Connection Connection;
    private static Statement Statement;

    //Singleton Implementation//
    private static Database databaseInstance = null;
    /** Connects to Database upon instantiation */
    private Database() {
        try {
            Connection = DriverManager.getConnection(CONNECTION_INFO,DB_USERNAME, DB_PASSWORD);
            Statement = Connection.createStatement();
            //Check if table exists for purchase data and if not, creates it
            if (!purchaseTableExists()) {
                createPurchaseTable();
            }
            if (!historyTableExists()) {
                createHistoryTable();
            }
        } catch (SQLException sql_exception) {
            //TODO logging and better error handling
            System.err.println("ERR: Failed to connect to SQL database. Quitting!");
            System.err.println(sql_exception.getMessage());
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
    public static void close(){
        try {
            Connection.close();
            Statement.close();
        } catch (SQLException sql_exception) {
            System.err.println("ERR: Failed to close database resources!");
            System.err.println(sql_exception.getMessage());
        }
    }

    //Table Handling//
    /** Returns true if the purchase table exists */
    private boolean purchaseTableExists() throws SQLException {
        ResultSet resultSet = Statement.executeQuery("SELECT EXISTS( SELECT * FROM " +
                "information_schema.TABLES WHERE TABLE_NAME = 'purchase');");
        resultSet.next(); //Have to do this prior to reading the result set, or the program errors
        boolean exists = (resultSet.getInt(1) == 1);
        resultSet.close();
        return exists;
    }
    private void createPurchaseTable() throws SQLException {
        Statement.executeUpdate("CREATE TABLE purchase ( date TINYTEXT NOT NULL, month INT NOT NULL," +
                " burger INT NOT NULL, sandwich INT NOT NULL, strip INT NOT NULL);");

    }
    private boolean historyTableExists() throws SQLException {
        ResultSet resultSet = Statement.executeQuery("SELECT EXISTS( SELECT * FROM " +
                "information_schema.TABLES WHERE TABLE_NAME = 'history');");
        resultSet.next(); //Have to do this prior to reading the result set, or the program errors
        boolean exists = (resultSet.getInt(1) == 1);
        resultSet.close();
        return exists;
    }
    private void createHistoryTable() throws SQLException {
        Statement.executeUpdate("CREATE TABLE history ( month INT NOT NULL," +
                " burger INT NOT NULL, chicken INT NOT NULL);");

    }
    public void clearTables() throws SQLException {
        Statement.executeUpdate("DELETE FROM purchase");
        Statement.executeUpdate("DELETE FROM history");
    }

    // Writing to Database //
    private void writePurchase(Purchase purchase) throws SQLException {
        Statement.executeUpdate("INSERT INTO purchase VALUES ('" +
                purchase.getDateAsString() + "', " +
                purchase.getDate().getMonthValue() + ", " +
                purchase.getNumberHamburger()+ ", " +
                purchase.getNumberSandwich()+ ", " +
                purchase.getNumberStrip() + ");");
    }
    public void writePurchaseHistory (PurchaseHistory purchaseHistory) throws SQLException {
        Statement.executeUpdate("INSERT INTO history VALUES (" +
                purchaseHistory.getMonth() + ", " +
                purchaseHistory.getSupplyBurger() + ", " +
                purchaseHistory.getSupplyChicken() + ");");
        for (Purchase purchase : purchaseHistory.getAllPurchases()) {
            writePurchase(purchase);
        }
    }

    //TODO - Refactor and cleanup, also comment everything; maybe rename everything to make more sense

    // Fetching Purchase History //
    public PurchaseHistory fetchPurchaseHistory(int month) throws PurchaseDoesNotExistException{
        try {
            if (historyExists(month)) {
                ResultSet historyInformation = Statement.executeQuery("SELECT * FROM " +
                        "history WHERE month = " + month);
                historyInformation.next();
                int burgerSupply = historyInformation.getInt(2);
                int chickenSupply = historyInformation.getInt(3);
                ArrayList<Purchase> purchaseList = new ArrayList<>();
                historyInformation.close();
                ResultSet purchaseInformation = Statement.executeQuery("SELECT * FROM " +
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
                    for (Product product : productList) {System.out.println(product.toString());}
                    purchaseList.add(new Purchase(purchaseDate, productList));
                }
                for (Purchase p : purchaseList) {System.out.println(p.getProfitMade());}
                purchaseInformation.close();
                return new PurchaseHistory(burgerSupply, chickenSupply, month, purchaseList);
            } else {
                System.err.println("ERR: Tried to fetch purchase history data that did not exist!");
                throw new PurchaseDoesNotExistException("Purchase History doesn't exist!");
            }
        } catch (SQLException sql_exception) {
            System.err.println("ERR: SQL Exception while fetching purchase history!");
            System.err.println(sql_exception.getMessage());
        }
        return null;
    }
    private boolean historyExists(int month) throws SQLException {
        ResultSet resultSet = Statement.executeQuery("SELECT EXISTS( SELECT * FROM " +
                "history WHERE month = "+ month +");");
        resultSet.next(); //Have to do this prior to reading the result set, or the program errors
        boolean exists = (resultSet.getInt(1) == 1);
        resultSet.close();
        return exists;
    }
}
