module com.example.devoir {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.fasterxml.jackson.databind;

    opens com.example.devoir to javafx.fxml;
    exports com.example.devoir;
    exports com.example.devoir.model;
    opens com.example.devoir.model to javafx.fxml;
}