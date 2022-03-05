package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.glizzygladiators.td.game.GameDifficulty;
import org.glizzygladiators.td.game.GameInstance;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.TDScenes;

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
            Stage stage = (Stage) myStackPane.getScene().getWindow();
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
            dialog.showAndWait();
        } else {
            Label warning = new Label("You have selected the name \"" + playerName.getText()
                    + "\" and will\nplay at difficulty " + difficulty.toString().toUpperCase()
                    + ". Do you wish to proceed?");
            warning.setAlignment(Pos.CENTER);
            Button goBack = new Button("Go Back");
            Button confirm = new Button("Confirm");
            HBox horiBox = new HBox(goBack, confirm);
            VBox verBox = new VBox(warning, horiBox);
            horiBox.setAlignment(Pos.CENTER);
            horiBox.setSpacing(25);
            verBox.setAlignment(Pos.CENTER);
            verBox.setSpacing(15);
            StackPane stack = new StackPane();
            stack.getChildren().add(verBox);
            stack.setAlignment(Pos.CENTER);
            Scene scene = new Scene(stack, 450, 170);
            Stage stage = new Stage();
            stage.setScene(scene);
            goBack.setOnAction(e -> {
                stage.close();
            });
            confirm.setOnAction(e -> {
                stage.close();
                TDApp.navigateToScene(TDScenes.GameScreen, new GameInstance(playerName.getText(),
                        difficulty));
            });
            stage.show();
        }
    }

    /**
     * Navigates back to the welcome screen.
     */
    @FXML
    public void backButtonClicked() {
        TDApp.navigateToScene(TDScenes.WelcomeScreen);
    }
}
