package com.github.sqlsalesproject.databasemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
    //Program requires mysql server set up on port 3306 with a user having this username and password
    private static final String CONNECTION_INFO = "jdbc:mysql://localhost:3306";
    private static final String DB_USERNAME = "salesproject";
    private static final String DB_PASSWORD = "sR3G>[d2ZWqF6p54";
    private static Connection Connection;

    //Singleton Implementation//
    private static Database databaseInstance = null;
    /** Connects to Database upon instantiation */
    private Database() {
        try {
            Connection = DriverManager.getConnection(CONNECTION_INFO,DB_USERNAME, DB_PASSWORD);
        } catch (SQLException sql_exception) {
            //TODO logging and better error handling
            System.err.println("ERR: Failed to connect to SQL database. Quitting!");
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
}
