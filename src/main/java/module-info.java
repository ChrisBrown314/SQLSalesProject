module com.github.sqlsalesproject.sqlsalesproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires dsiutils;


    exports com.github.sqlsalesproject.databasemanagement;
    opens com.github.sqlsalesproject.databasemanagement to javafx.fxml;
    exports com.github.sqlsalesproject.salesapp;
    opens com.github.sqlsalesproject.salesapp to javafx.fxml;
    exports com.github.sqlsalesproject.fxmlcontrollers;
    opens com.github.sqlsalesproject.fxmlcontrollers to javafx.fxml;
    exports com.github.sqlsalesproject.sale;
    opens com.github.sqlsalesproject.sale to javafx.fxml;
}