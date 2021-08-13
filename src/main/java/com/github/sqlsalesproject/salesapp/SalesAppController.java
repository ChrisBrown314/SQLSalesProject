package com.github.sqlsalesproject.salesapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SalesAppController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}