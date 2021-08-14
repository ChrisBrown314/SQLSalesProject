package com.github.sqlsalesproject.salesapp;

import com.github.sqlsalesproject.databasemanagement.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SalesApp extends Application {
    /** Loads fxml scene, sets window properties, and shows the window*/
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("/fxmlResources/sales-app.fxml"));
        Scene scene = new Scene(sceneLoader.load(), 320, 240);

        stage.setScene(scene);
        stage.setTitle("Sales Examination App");
        stage.show();
    }

    /** Connects to Database and launches the application*/
    public static void main(String[] args) {
        Database.getDatabase();
        launch();
    }
}