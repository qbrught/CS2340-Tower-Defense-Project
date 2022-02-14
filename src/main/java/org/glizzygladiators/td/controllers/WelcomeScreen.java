package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import org.glizzygladiators.td.TDApp;

public class WelcomeScreen{

    @FXML
    public void StartClicked(MouseEvent mouseEvent) {
        System.out.println("Start button clicked");
    }

    @FXML
    public void QuitClicked(MouseEvent mouseEvent) {
        // TODO maybe add a dialog to ask if the user is sure they want to quit
        System.exit(0);
    }
}

