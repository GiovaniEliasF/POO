module com.doceria.doceria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.doceria to javafx.fxml;
    exports com.doceria;
    exports com.doceria.controller;
    opens com.doceria.controller to javafx.fxml;
}