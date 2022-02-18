package org.glizzygladiators.td;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TDApp extends Application {

    public static TDApp app;
    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        app = this;
        mainStage = stage;
        navigateToScene(TDScenes.WelcomeScreen);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Gets a JFX Parent object from the scenes folder.
     * @param path The path to the fxml file
     * @return The parent object associated with the FXML file
     */
    public static Parent getParent(String path) {
        try {
            return FXMLLoader.load(Objects.requireNonNull(TDApp.class.getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AnchorPane();
    }

    /**
     * Navigates the current stage to a specified scene.
     * @param name the name of the scene specified in TDScenes
     * @return The previous scene in the stage
     */
    public static Scene navigateToScene(TDScenes name) {
        Scene cScene = mainStage.getScene();
        Scene newScene = null;
        switch (name) {
            case WelcomeScreen:
                newScene = new Scene(getParent("scenes/WelcomeScreen.fxml"));
                break;
            case InitialConfig:
                newScene = new Scene(getParent("scenes/InitialConfig.fxml"));
                break;
        }
        newScene.getStylesheets().add(TDApp.class.getResource("styles/style.css").toExternalForm()); //Loads css which contains theming options
        mainStage.setScene(newScene);
        return cScene;
    }
}
