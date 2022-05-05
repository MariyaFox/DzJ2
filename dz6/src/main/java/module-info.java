module com.example.dz6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.dz6 to javafx.fxml;
    exports com.example.dz6;
}