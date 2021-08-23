package com.github.sqlsalesproject.databasemanagement;

import com.github.sqlsalesproject.sale.Generate;
import com.github.sqlsalesproject.sale.PurchaseHistory;
import com.github.sqlsalesproject.tools.PurchaseDoesNotExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {


    @Test
    @DisplayName("Tests if writing works")
    void writePurchaseTest() {
        PurchaseHistory purchaseHistory;
        for (int month = 1; month < 13; month++) {
            purchaseHistory = Generate.purchaseHistory(2500, 2500, 2020, month);
            try {
                Database.getDatabase().writePurchaseHistory(purchaseHistory);
            } catch (Exception e) {
                System.err.println("ERR: Problem when writing to database!");
            }
        }
    }

    @Test
    @DisplayName("Tests if fetching works")
    void fetchTest() {
        try {
            PurchaseHistory purchaseHistory = Database.getDatabase().fetchPurchaseHistory(1);
            System.out.println("Month: "+purchaseHistory.getMonth());
            System.out.println("Profit: "+purchaseHistory.getProfit());
            System.out.println("Sale Cost: "+purchaseHistory.getSalePrice());
            System.out.println("Supply Cost: "+purchaseHistory.getSupplyCost());
        } catch (Exception e) {
            System.err.println("ERR: The purchase history does not exist!");
        }
    }
}