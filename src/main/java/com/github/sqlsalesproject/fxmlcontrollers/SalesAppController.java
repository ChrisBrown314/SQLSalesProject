package com.github.sqlsalesproject.fxmlcontrollers;

import com.github.sqlsalesproject.databasemanagement.Database;
import com.github.sqlsalesproject.sale.PurchaseHistory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalesAppController {
    public Button clearDataButton;
    public Button regenPurchasesButton;
    public TableView tableView;
    public ComboBox<String> viewDropDown;
    ArrayList<PurchaseHistory> storedHistory;

    @FXML
    public void initialize () {
        storedHistory = new ArrayList<>();
        Database.fetchData(storedHistory);
        TableController.getPurchaseTable(storedHistory, tableView);
        viewDropDown.getItems().add("View Purchases");
        viewDropDown.getItems().add("View Profit by Month");
        viewDropDown.getItems().add("View Recommended Product");
        viewDropDown.getSelectionModel().selectFirst();
    }

    public void viewChanged(ActionEvent actionEvent) {
        updateTable();
    }
    private void updateTable () {
        switch (viewDropDown.getValue()) {
            case "View Purchases" -> TableController.getPurchaseTable(storedHistory, tableView);
            case "View Profit by Month" -> TableController.getHistoryTable(storedHistory, tableView);
            case "View Recommended Product" -> TableController.getRecommendedTable(storedHistory, tableView);
        }
    }

    /** Clears database data when the clear button is pressed */
    public void clearDataAction(ActionEvent actionEvent) {
        try {
            storedHistory.clear();
            Database.getDatabase().clearTables();
            showInformationPopup("Clearing database");
        } catch (SQLException sqlException) {
            System.err.println("ERR: Failed to clear databases! Quitting!");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }
    }
    /** Regenerates data when regenerate button is pressed */
    public void regenDataAction(ActionEvent actionEvent) {
        Database.regenerateData(storedHistory);
        showInformationPopup("Regenerate database");
        updateTable();
    }

    /** Shows an information dialog showing that a given operation has been completed */
    private void showInformationPopup(String operation) {
        Alert InfoPopup = new Alert(Alert.AlertType.INFORMATION);
        InfoPopup.setTitle("Operation Information");
        InfoPopup.setContentText(operation + " operation has been completed!");
        InfoPopup.setHeaderText("Operation Complete");
        InfoPopup.show();
    }


}