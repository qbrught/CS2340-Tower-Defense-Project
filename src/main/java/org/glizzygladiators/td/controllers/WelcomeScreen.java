package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.TDScenes;

public class WelcomeScreen{

    @FXML
    public void StartClicked(MouseEvent mouseEvent) {
        TDApp.navigateToScene(TDScenes.InitialConfig);
    }

    @FXML
    public void QuitClicked(MouseEvent mouseEvent) {
        // TODO maybe add a dialog to ask if the user is sure they want to quit
        System.exit(0);
    }
}

