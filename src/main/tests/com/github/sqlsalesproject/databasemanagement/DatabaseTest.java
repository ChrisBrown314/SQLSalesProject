package com.github.sqlsalesproject.databasemanagement;

import com.github.sqlsalesproject.sale.Generate;
import com.github.sqlsalesproject.sale.Purchase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {


    @Test
    @DisplayName("Tests if writing works")
    void writePurchaseTest() {
        Purchase purchase = Generate.purchase(2020,8);
        try {
            Database.getDatabase().writePurchase(purchase);
            System.out.println(purchase.getDate());
            System.out.println(purchase.getNumberSandwich());
            System.out.println(purchase.getNumberStrip());
            System.out.println(purchase.getNumberHamburger());
        } catch (SQLException e) {
            System.err.println("ERR: Problem when writing to database!");
        }
    }

}