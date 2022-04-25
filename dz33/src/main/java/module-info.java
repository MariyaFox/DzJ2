module com.example.dz33 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.dz33 to javafx.fxml;
    exports com.example.dz33;
}