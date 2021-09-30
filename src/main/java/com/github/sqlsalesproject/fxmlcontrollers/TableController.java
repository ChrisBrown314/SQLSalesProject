package com.github.sqlsalesproject.fxmlcontrollers;

import com.github.sqlsalesproject.sale.Purchase;
import com.github.sqlsalesproject.sale.PurchaseHistory;
import com.github.sqlsalesproject.sale.Recommendation;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Arrays;

/**Table Controller class.
 *
 *
 */
public class TableController {

    static void getPurchaseTable(ArrayList<PurchaseHistory> purchaseHistory, TableView<Purchase> purchaseTableView) {
        purchaseTableView.getColumns().clear();
        //Column Information
        TableColumn<Purchase, Integer> month = new TableColumn<>("Month");
        month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        TableColumn<Purchase, Integer> day = new TableColumn<>("Day");
        day.setCellValueFactory(new PropertyValueFactory<>("Day"));
        TableColumn<Purchase, Integer> year = new TableColumn<>("Year");
        year.setCellValueFactory(new PropertyValueFactory<>("Year"));
        TableColumn<Purchase, Double> profit = new TableColumn<>("Profit");
        profit.setCellValueFactory(new PropertyValueFactory<>("ProfitMade"));
        TableColumn<Purchase, Double> productionCost = new TableColumn<>("Production Cost");
        productionCost.setCellValueFactory(new PropertyValueFactory<>("TotalProductionCost"));
        TableColumn<Purchase, Double> salePrice = new TableColumn<>("Sale Price");
        salePrice.setCellValueFactory(new PropertyValueFactory<>("TotalSalePrice"));
        purchaseTableView.getColumns().addAll(Arrays.asList(month, day, year, profit, productionCost, salePrice));
        //Getting all possible purchases
        ArrayList<Purchase> purchases = new ArrayList<>();
        for (PurchaseHistory history : purchaseHistory) {
            purchases.addAll(history.getAllPurchases());
        }
        //Adding data to table
        purchaseTableView.setItems(FXCollections.observableList(purchases));
    }
    static void getHistoryTable(ArrayList<PurchaseHistory> purchaseHistory, TableView<PurchaseHistory> table) {
        table.getColumns().clear();
        TableColumn<PurchaseHistory, Integer> month = new TableColumn<>("Month");
        month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        TableColumn<PurchaseHistory, Integer> year = new TableColumn<>("Year");
        year.setCellValueFactory(new PropertyValueFactory<>("Year"));
        TableColumn<PurchaseHistory, Integer> numPurchase = new TableColumn<>("Purchases Made");
        numPurchase.setCellValueFactory(new PropertyValueFactory<>("NumPurchases"));
        TableColumn<PurchaseHistory, Double> profit = new TableColumn<>("Profit");
        profit.setCellValueFactory(new PropertyValueFactory<>("Profit"));
        TableColumn<PurchaseHistory, Double> productionCost = new TableColumn<>("Supply Cost");
        productionCost.setCellValueFactory(new PropertyValueFactory<>("SupplyCost"));
        TableColumn<PurchaseHistory, Double> salePrice = new TableColumn<>("Sale Price");
        salePrice.setCellValueFactory(new PropertyValueFactory<>("SalePrice"));
        table.getColumns().addAll(Arrays.asList(month, year, numPurchase, profit, productionCost, salePrice));
        table.setItems(FXCollections.observableList(purchaseHistory));
    }
    static void getRecommendedTable(ArrayList<PurchaseHistory> purchaseHistory, TableView<Recommendation> table) {
        table.getColumns().clear();
        ArrayList<Recommendation> recommendations = new ArrayList<>();
        for (PurchaseHistory historyToAnalyze : purchaseHistory) {
            int year = historyToAnalyze.getYear();
            int month = historyToAnalyze.getMonth();
            recommendations.add(new Recommendation(year, month, historyToAnalyze));
        }
        TableColumn<Recommendation, Integer> month = new TableColumn<>("Month");
        month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        TableColumn<Recommendation, Integer> burger = new TableColumn<>("Recommended Burgers");
        burger.setCellValueFactory(new PropertyValueFactory<>("RecommendedBurgers"));
        TableColumn<Recommendation, Integer> chicken = new TableColumn<>("Recommended Chicken");
        chicken.setCellValueFactory(new PropertyValueFactory<>("RecommendedChicken"));
        TableColumn<Recommendation, Double> expectedProfit = new TableColumn<>("Expected Profit");
        expectedProfit.setCellValueFactory(new PropertyValueFactory<>("ExpectedProfit"));
        TableColumn<Recommendation, Double> expectedProductionCost = new TableColumn<>("Expected Production Cost");
        expectedProductionCost.setCellValueFactory(new PropertyValueFactory<>("ExpectedProductionCost"));
        TableColumn<Recommendation, Double> expectedSalePrice = new TableColumn<>("Expected Sale Price");
        expectedSalePrice.setCellValueFactory(new PropertyValueFactory<>("ExpectedSalePrice"));
        table.getColumns().addAll(Arrays.asList(month, burger, chicken, expectedProfit, expectedProductionCost, expectedSalePrice));
        table.setItems(FXCollections.observableList(recommendations));
    }
}
