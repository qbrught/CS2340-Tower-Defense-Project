module org.glizzygladiators.td {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.apache.logging.log4j;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens org.glizzygladiators.td to javafx.fxml;
    exports org.glizzygladiators.td;

    opens org.glizzygladiators.td.controllers to javafx.fxml;
    exports org.glizzygladiators.td.controllers;

    opens org.glizzygladiators.td.images;
    exports org.glizzygladiators.td.game;
    opens org.glizzygladiators.td.game to javafx.fxml;
}
