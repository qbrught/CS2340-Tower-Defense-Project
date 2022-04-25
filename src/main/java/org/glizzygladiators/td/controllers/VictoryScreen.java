package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.visualizers.GameInstanceDriver;

import java.net.URL;
import java.util.ResourceBundle;

public class VictoryScreen implements Initializable{
    @FXML
    private StackPane root;

    private GameInstanceDriver game;
    @FXML
    private Label score;
    @FXML
    private Label spent;
    @FXML
    private Label numTowers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = GameInstanceDriver.getDriver();
        root.setStyle("-fx-background-image: url('" + TDApp.getResourcePath("images/VictoryObama.jpeg")
                + "');"
                + "-fx-background-repeat: stretch; "
                + "-fx-background-size: cover; "
                + "-fx-background-position: center;");
        score.textProperty().set(Integer.toString(game.getGame().getScore()));
        spent.textProperty().set(Integer.toString(game.getGame().getMoneySpent()));
        numTowers.textProperty().set(Integer.toString(game.getGame().getTowers().size()));
    }

    /**
     * Restarts the game and takes the user to the Welcome screen.
     */
    @FXML
    public void startClicked() {
        Scene scene = root.getScene();
        Parent root = TDApp.getParent("scenes/WelcomeScreen.fxml");
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
