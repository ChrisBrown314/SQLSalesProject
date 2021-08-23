package com.github.sqlsalesproject.fxmlcontrollers;

import com.github.sqlsalesproject.sale.Purchase;
import com.github.sqlsalesproject.sale.PurchaseHistory;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class TableController {

    static void getPurchaseTable(ArrayList<PurchaseHistory> purchaseHistory, TableView table) {
        table.getColumns().clear();
        //Column Information
        TableColumn month = new TableColumn("Month");
        month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        TableColumn day = new TableColumn("Day");
        day.setCellValueFactory(new PropertyValueFactory<>("Day"));
        TableColumn year = new TableColumn("Year");
        year.setCellValueFactory(new PropertyValueFactory<>("Year"));
        TableColumn profit = new TableColumn("Profit");
        profit.setCellValueFactory(new PropertyValueFactory<Purchase, Double>("ProfitMade"));
        TableColumn productionCost = new TableColumn("Production Cost");
        productionCost.setCellValueFactory(new PropertyValueFactory<>("TotalProductionCost"));
        TableColumn salePrice = new TableColumn("Sale Price");
        salePrice.setCellValueFactory(new PropertyValueFactory<>("TotalSalePrice"));
        table.getColumns().addAll(month, day, year, profit, productionCost, salePrice);
        //Getting all possible purchases
        ArrayList<Purchase> purchases = new ArrayList<>();
        for (PurchaseHistory history : purchaseHistory) {
            purchases.addAll(history.getAllPurchases());
        }
        //Adding data to table
        table.setItems(FXCollections.observableList(purchases));
    }
    static void getHistoryTable(ArrayList<PurchaseHistory> purchaseHistory, TableView table) {
        table.getColumns().clear();
        TableColumn month = new TableColumn("Month");
        TableColumn year = new TableColumn("Year");
        TableColumn numPurchase = new TableColumn("Purchases Made");
        TableColumn profit = new TableColumn("Profit");
        TableColumn productionCost = new TableColumn("Production Cost");
        TableColumn salePrice = new TableColumn("Sale Price");
        table.getColumns().addAll(month, year, profit, productionCost, salePrice);
    }
    static void getRecommendedTable(ArrayList<PurchaseHistory> purchaseHistory, TableView table) {
        table.getColumns().clear();
        TableColumn month = new TableColumn("Month");
        TableColumn burger = new TableColumn("Recommended Burgers");
        TableColumn chicken = new TableColumn("Recommended Chicken");
        TableColumn expectedProfit = new TableColumn("Expected Profit");
        TableColumn expectedProductionCost = new TableColumn("Expected Production Cost");
        TableColumn expectedSalePrice = new TableColumn("Expected Sale Price");
        table.getColumns().addAll(month, burger, chicken, expectedProfit, expectedProductionCost, expectedSalePrice);
    }
}
