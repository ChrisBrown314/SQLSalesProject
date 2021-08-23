package com.github.sqlsalesproject.fxmlcontrollers;

import com.github.sqlsalesproject.databasemanagement.Database;
import com.github.sqlsalesproject.sale.Generate;
import com.github.sqlsalesproject.sale.PurchaseHistory;
import com.github.sqlsalesproject.tools.PurchaseDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalesAppController {
    public Button clearDataButton;
    public Button regenPurchasesButton;
    public TableView tableView;
    public ComboBox viewDropDown;
    ArrayList<PurchaseHistory> storedHistory;

    @FXML
    public void initialize () {
        storedHistory = new ArrayList<>();
        regenerateData();
        TableController.getPurchaseTable(storedHistory, tableView);
        viewDropDown.getItems().add("View Purchases");
        viewDropDown.getItems().add("View Profit by Month");
        viewDropDown.getItems().add("View Recommended Product");
    }

    public void viewChanged(ActionEvent actionEvent) {
        updateTable();
    }
    private void updateTable () {
        switch ((String) viewDropDown.getValue()) {
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
        regenerateData();
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

    /** Regenerates the data in the database and tables */
    private void regenerateData () {
        try {
            storedHistory.clear();
            Database.getDatabase().clearTables();
            for (int month = 1; month < 13; month++) {
                PurchaseHistory purchaseHistory = Generate.purchaseHistory(2500, 2500, 2020, month);
                Database.getDatabase().writePurchaseHistory(purchaseHistory);
            }
            for (int year : Database.getDatabase().getAllYears()) {
                for (int month = 1; month < 13; month++) {
                    storedHistory.add(Database.getDatabase().fetchPurchaseHistory(month, year)); //Possibly split off into own method
                }
            }

        }  catch (PurchaseDoesNotExistException | SQLException exception) {
            System.err.println("ERR: Failed to regenerate data! Quitting!");
            System.err.println(exception.getMessage());
            System.exit(-1);
        }

    }
}