module com.example.pbl {
    requires org.junit.jupiter.api;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.pbl to javafx.fxml;
    exports com.example.pbl;
}