package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.glizzygladiators.td.game.GameDifficulty;
import org.glizzygladiators.td.game.GameInstance;
import org.glizzygladiators.td.TDApp;

import static org.glizzygladiators.td.game.GameDifficulty.*;

import java.util.ArrayList;

public class InitialConfig {

    @FXML private TextField playerName;
    @FXML private StackPane myStackPane;
    private GameDifficulty difficulty;

    /**
     * Handler to set the difficulty of the game.
     * @param mouseEvent mouse event to handle setting difficulty
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
        default:
            difficulty = EASY;
        }
    }

    public static String determineMessage(GameDifficulty difficulty,
                                          String playerName) {
        ArrayList<String> warnings = new ArrayList<String>();
        if (difficulty == null) {
            warnings.add("Choose difficulty");
        } 
        if (playerName == null || playerName == "" || playerName.isBlank()) {
            warnings.add("Enter name with non-space characters");
        }
        if (playerName.length() > 20) {
            warnings.add("Enter name less than 20 characters");
        }
        if (warnings.isEmpty()) return null;
        String message = "Please do the following:\n";
        for (String warning : warnings) {
            message += warning + "\n";
        }
        return message;
    }
    /**
     * Completes the initialization of the game if the player has a valid
     * difficulty, name, and confirms the information before starting.
     */
    @FXML
    public void finishInit() {
        String message = determineMessage(difficulty, playerName.getText());
        if (message != null) {
            Dialog<String> dialog = new Dialog<>();

            dialog.setTitle("Error");
            dialog.setContentText(message);
            ButtonType closeDialog = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(closeDialog);
            dialog.getDialogPane().lookupButton(closeDialog).setId(
                    "ExitImproperUserSettingsButton");
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
                Parent root = TDApp.getParentPassParams("scenes/GameScreen.fxml",
                        new GameInstance(playerName.getText(), difficulty));
                TDApp.navigateToRoot(myStackPane.getScene(), root);
            });
            dialog.showAndWait();
        }
    }

    /**
     * Navigates back to the welcome screen.
     * @param mouseEvent mouse event that handles back button
     */
    @FXML
    public void backButtonClicked(MouseEvent mouseEvent) {
        Parent root = TDApp.getParent("scenes/WelcomeScreen.fxml");
        TDApp.navigateToRoot(myStackPane.getScene(), root);
    }
}
