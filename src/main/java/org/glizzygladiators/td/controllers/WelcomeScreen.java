package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import org.glizzygladiators.td.TDApp;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeScreen implements Initializable {

    @FXML
    private StackPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {        
        root.setStyle("-fx-background-image: url('" + TDApp.getResourcePath("images/home.jpg")
                + "');"
                + "-fx-background-repeat: stretch; "
                + "-fx-background-size: cover; "
                + "-fx-background-position: center;");
    }

    /**
     * Starts the game and takes the user to the initial configuration screen.
     */
    @FXML
    public void startClicked() {
        Scene scene = root.getScene();
        Parent root = TDApp.getParent("scenes/InitialConfig.fxml");
        TDApp.navigateToRoot(scene, root);
    }

    /**
     * Exits the game.
     */
    @FXML
    public void quitClicked() {
        // TODO maybe add a dialog to ask if the user is sure they want to quit
        System.exit(0);
    }
}

