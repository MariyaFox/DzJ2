module com.example.dz7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dz7 to javafx.fxml;
    exports com.example.dz7;
}