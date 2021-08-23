package com.github.sqlsalesproject.fxmlcontrollers;

import com.github.sqlsalesproject.sale.Purchase;
import com.github.sqlsalesproject.sale.PurchaseHistory;
import com.github.sqlsalesproject.sale.Recommendation;
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
        month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        TableColumn year = new TableColumn("Year");
        year.setCellValueFactory(new PropertyValueFactory<>("Year"));
        TableColumn numPurchase = new TableColumn("Purchases Made");
        numPurchase.setCellValueFactory(new PropertyValueFactory<>("NumPurchases"));
        TableColumn profit = new TableColumn("Profit");
        profit.setCellValueFactory(new PropertyValueFactory<>("Profit"));
        TableColumn productionCost = new TableColumn("Supply Cost");
        productionCost.setCellValueFactory(new PropertyValueFactory<>("SupplyCost"));
        TableColumn salePrice = new TableColumn("Sale Price");
        salePrice.setCellValueFactory(new PropertyValueFactory<>("SalePrice"));
        table.getColumns().addAll(month, year, numPurchase, profit, productionCost, salePrice);
        table.setItems(FXCollections.observableList(purchaseHistory));
    }
    static void getRecommendedTable(ArrayList<PurchaseHistory> purchaseHistory, TableView table) {
        table.getColumns().clear();
        ArrayList<Recommendation> recommendations = new ArrayList<>();
        for (PurchaseHistory historyToAnalyze : purchaseHistory) {
            int year = historyToAnalyze.getYear();
            int month = historyToAnalyze.getMonth();
            recommendations.add(new Recommendation(year, month, historyToAnalyze));
        }
        TableColumn month = new TableColumn("Month");
        month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        TableColumn burger = new TableColumn("Recommended Burgers");
        burger.setCellValueFactory(new PropertyValueFactory<>("RecommendedBurgers"));
        TableColumn chicken = new TableColumn("Recommended Chicken");
        chicken.setCellValueFactory(new PropertyValueFactory<>("RecommendedChicken"));
        TableColumn expectedProfit = new TableColumn("Expected Profit");
        expectedProfit.setCellValueFactory(new PropertyValueFactory<>("ExpectedProfit"));
        TableColumn expectedProductionCost = new TableColumn("Expected Production Cost");
        expectedProductionCost.setCellValueFactory(new PropertyValueFactory<>("ExpectedProductionCost"));
        TableColumn expectedSalePrice = new TableColumn("Expected Sale Price");
        expectedSalePrice.setCellValueFactory(new PropertyValueFactory<>("ExpectedSalePrice"));
        table.getColumns().addAll(month, burger, chicken, expectedProfit, expectedProductionCost, expectedSalePrice);
        table.setItems(FXCollections.observableList(recommendations));
    }
}
