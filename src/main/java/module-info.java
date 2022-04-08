module org.glizzygladiators.td {
    requires java.desktop;
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
    requires org.testfx.junit5;
    requires org.testfx;
    requires org.hamcrest;
    requires org.junit.jupiter.api;
    // requires org.junit.jupiter;

    opens org.glizzygladiators.td;
    exports org.glizzygladiators.td;

    opens org.glizzygladiators.td.controllers to javafx.fxml;
    exports org.glizzygladiators.td.controllers;

    opens org.glizzygladiators.td.images;

    exports org.glizzygladiators.td.entities;
    opens org.glizzygladiators.td.entities to javafx.fxml;
    exports org.glizzygladiators.td.entities.health;
    opens org.glizzygladiators.td.entities.health to javafx.fxml;
}
