package com.github.sqlsalesproject.salesapp;

import com.github.sqlsalesproject.databasemanagement.Database;
import com.github.sqlsalesproject.sale.Product;
import com.github.sqlsalesproject.sale.Supply;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/** Launches the app and sets up JavaFX */
public class SalesApp extends Application {
//TODO - cleanup javadoc comments with all necessary details
//TODO - make sure line width does not exceed 100

    /** Loads fxml scene, sets window properties, and shows the window */
    @Override
    public void start(Stage stage) throws IOException {
        //TODO Comments better?
        //TODO clean up, maybe split up and create a separate scene loader class
        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("/fxmlResources/sales-app.fxml"));
        Scene scene = new Scene(sceneLoader.load());
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/AppIcon.png"))));
        stage.setTitle("SQL Sales Project");
        stage.resizableProperty().set(false);
        stage.show();
    }

    /** Connects to Database and launches the application */
    public static void main(String[] args) {
        //Initialize Products
        Product hamburger = new Product("Hamburger", 10.99);
        Product chickenStrip = new Product("Chicken Strips", 12.99);
        Product chickenSandwich = new Product("Chicken Sandwich", 10.99);
        hamburger.addSupply(new Supply("Burger", 6.00, 1), .25);
        chickenSandwich.addSupply(new Supply("Chicken", 10.0, 10), 1.0);
        chickenSandwich.addSupply(new Supply("Chicken", 10.0, 10), 3.0);

        Database.getDatabase();
        launch();
    }
}