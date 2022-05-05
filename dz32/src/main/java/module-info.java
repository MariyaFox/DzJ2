module com.example.dz32 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.dz32 to javafx.fxml;
    exports com.example.dz32;
    exports com.example.dz32.client;
    opens com.example.dz32.client to javafx.fxml;
}