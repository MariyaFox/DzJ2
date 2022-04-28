module com.example.dz34 {
    requires javafx.controls;
    requires javafx.fxml;
            
                        
    opens com.example.dz34 to javafx.fxml;
    exports com.example.dz34;
}