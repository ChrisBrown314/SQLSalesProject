package com.github.sqlsalesproject.databasemanagement;

import com.github.sqlsalesproject.sale.Purchase;

import java.sql.*;


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
            if (!exists()) {
                createTable();
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
    private boolean exists() throws SQLException {
        ResultSet resultSet = Statement.executeQuery("SELECT EXISTS( SELECT * FROM " +
                "information_schema.TABLES WHERE TABLE_NAME = 'purchase');");
        resultSet.next(); //Have to do this prior to reading the result set, or the program errors
        boolean exists = (resultSet.getInt(1) == 1);
        resultSet.close();
        return exists;
    }
    private void createTable() throws SQLException {
        Statement.executeUpdate("CREATE TABLE purchase ( date TINYTEXT NOT NULL, month INT NOT NULL," +
                " burger INT NOT NULL, sandwich INT NOT NULL, strip INT NOT NULL);");
    }
    public void clearTable() throws SQLException {
        Statement.executeUpdate("DELETE FROM purchase");
    }

    // Writing to Database //
    public void writePurchase(Purchase purchase) throws SQLException {
        Statement.executeUpdate("INSERT INTO purchase VALUES ('" +
                purchase.getDateAsString() + "', " +
                purchase.getDate().getMonthValue() + ", " +
                purchase.getNumberHamburger()+ ", " +
                purchase.getNumberSandwich()+ ", " +
                purchase.getNumberStrip() + ");");
    }
}
