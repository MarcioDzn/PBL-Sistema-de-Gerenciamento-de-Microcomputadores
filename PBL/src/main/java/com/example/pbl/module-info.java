module com.example.pbl {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pbl to javafx.fxml;
    exports com.example.pbl;
}