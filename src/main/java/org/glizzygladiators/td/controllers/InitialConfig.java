package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.glizzygladiators.td.game.GameDifficulty;
import org.glizzygladiators.td.game.GameInstance;
import org.glizzygladiators.td.TDApp;

import static org.glizzygladiators.td.game.GameDifficulty.*;

public class InitialConfig {

    @FXML private TextField playerName;
    @FXML private StackPane myStackPane;
    private GameDifficulty difficulty;

    /**
     * Handler to set the difficulty of the game.
     * @param mouseEvent
     */
    @FXML
    public void setDifficulty(MouseEvent mouseEvent) {
        switch (((Button) mouseEvent.getSource()).getText()) {
            case "Easy":
                difficulty = EASY;
                break;
            case "Medium":
                difficulty = MEDIUM;
                break;
            case "Hard":
                difficulty = HARD;
                break;
        }
    }

    /**
     * Completes the initialization of the game if the player has a valid
     * difficulty, name, and confirms the information before starting.
     */
    @FXML
    public void finishInit() {
        if (difficulty == null || playerName.getText() == null
                || playerName.getText().equals("") || playerName.getText().isBlank()
                || playerName.getText().length() > 20) {

            Dialog<String> dialog = new Dialog<>();

            dialog.setTitle("Error");
            if (playerName != null && playerName.getText().length() > 20) {
                dialog.setContentText("The character limit for names is 20!");
            } else {
                dialog.setContentText("You must select your difficulty and choose a valid name "
                        + "before proceeding!");
            }
            ButtonType closeDialog = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(closeDialog);
            dialog.getDialogPane().lookupButton(closeDialog).setId("ExitImproperUserSettingsButton");
            dialog.getDialogPane().lookup(".label").setId("UserSettingsWarningContent");
            dialog.showAndWait();
        } else {
            String contentText = "You have selected the name \"" + playerName.getText()
                    + "\" and will\nplay at difficulty " + difficulty.toString().toUpperCase()
                    + ". Do you wish to proceed?";
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Game Start");
            dialog.setContentText(contentText);
            ButtonType goBackType = new ButtonType("Go Back", ButtonBar.ButtonData.CANCEL_CLOSE);
            ButtonType confirmType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(confirmType, goBackType);
            dialog.getDialogPane().lookupButton(goBackType).setId("GoBackButton");
            Button confirm = (Button) dialog.getDialogPane().lookupButton(confirmType);
            confirm.setId("ConfirmButton");
            confirm.setOnAction(e -> {
                Parent root = TDApp.getParentPassParams("scenes/GameScreen.fxml", new GameInstance(playerName.getText(),
                difficulty));
                TDApp.navigateToRoot(myStackPane.getScene(), root);
            });
            dialog.showAndWait();
        }
    }

    /**
     * Navigates back to the welcome screen.
     */
    @FXML
    public void backButtonClicked(MouseEvent mouseEvent) {
        Parent root = TDApp.getParent("scenes/WelcomeScreen.fxml");
        TDApp.navigateToRoot(myStackPane.getScene(), root);
    }
}
