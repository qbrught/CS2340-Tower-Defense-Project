package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.TDScenes;

import java.util.Locale;

public class InitialConfig {

    @FXML private TextField playerName;
    @FXML private StackPane myStackPane;
    private String difficulty; // I AM UNSURE IF THESE VARIABLES NEED TO BE CHANGED ANYTIME SOON.

    @FXML
    public void setDifficulty(MouseEvent mouseEvent) {
        if (((Button) mouseEvent.getSource()).getText().equals("Easy")) {
            difficulty = "easy";
        } else if (((Button) mouseEvent.getSource()).getText().equals("Medium")) {
            difficulty = "medium";
        } else if (((Button) mouseEvent.getSource()).getText().equals("Hard")) {
            difficulty = "hard";
        }
    }

    @FXML
    public void finishInit() {
        if (difficulty == null || playerName.getText() == null
                || playerName.getText().equals("") || playerName.getText().isBlank()) {
            Stage stage = (Stage) myStackPane.getScene().getWindow();
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            dialog.setContentText("You must select your difficulty and choose a valid name before proceeding!");
            ButtonType closeDialog = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(closeDialog);
            dialog.showAndWait();
        } else if (playerName.getText().length() > 20) {
            Stage stage = (Stage) myStackPane.getScene().getWindow();
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            dialog.setContentText("The character limit for names is 20!");
            ButtonType closeDialog = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(closeDialog);
            dialog.showAndWait();
        } else {
            Label warning = new Label("You have selected the name \"" + playerName.getText()
                    + "\" and will\nplay at difficulty " + difficulty.toUpperCase() + ". Do you wish to proceed?");
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
                TDApp.navigateToScene(TDScenes.WelcomeScreen); // THIS IS FOR TESTING PURPOSES. THIS MUST BE CHANGED TO THE NEXT SCREEN.
            });
            stage.show();
        }
    }

    @FXML
    public void backButtonClicked(MouseEvent mouseEvent) {
        TDApp.navigateToScene(TDScenes.WelcomeScreen);
    }
}
