module com.example.voidfile {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.voidfile to javafx.fxml;
    exports com.example.voidfile;
}