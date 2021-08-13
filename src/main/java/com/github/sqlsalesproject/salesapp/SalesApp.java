package com.github.sqlsalesproject.salesapp;

import com.github.sqlsalesproject.databasemanagement.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SalesApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //TODO fix fxml issues
    }

    public static void main(String[] args) {
        Database.getDatabase();
        //launch();
    }
}