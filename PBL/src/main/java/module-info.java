module com.example.pbl {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.pbl to javafx.fxml, javafx.graphics;
    opens com.example.pbl.controller to javafx.fxml, javafx.graphics;
    opens com.example.pbl.model to javafx.base;
    exports com.example.pbl;
}