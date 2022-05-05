module com.example.dz5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dz5 to javafx.fxml;
    exports com.example.dz5;
}