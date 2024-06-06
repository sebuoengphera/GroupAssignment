module com.example.ponggame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.ponggame to javafx.fxml;
    exports com.example.ponggame;
}