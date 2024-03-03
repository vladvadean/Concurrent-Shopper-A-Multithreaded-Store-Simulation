module com.example.tema2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.tema2 to javafx.fxml;
}