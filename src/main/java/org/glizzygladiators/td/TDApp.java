package org.glizzygladiators.td;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.glizzygladiators.td.controllers.ParameterController;

import java.io.IOException;
import java.util.Objects;


public class TDApp extends Application {

    private static TDApp app;
    private static Stage mainStage;

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

    public static TDApp getInstance() {
        return app;
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    /**
     * Gets the path to a resource
     * @param path the path from the resources folder
     * @return the string to the resource
     */
    public static String getResourcePath(String path) {
        return TDApp.class.getResource(path).toExternalForm();
    }

    /**
     * Gets a JFX Parent object from the scenes folder.
     * @param path The path to the fxml file
     * @return The parent object associated with the FXML file
     */
    public static Parent getParent(String path) { // TODO should this be made private?
        try {
            return FXMLLoader.load(Objects.requireNonNull(TDApp.class.getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AnchorPane();
    }

    /**
     * Gets a JFX Parent object from the scenes folder and passes a parameter to its controller
     * @param path The path to the fxml file
     * @param param The parameter to pass to the controller
     * @param <S> The type of the controller. It must implement ParameterController
     * @return The parent object associated with the FXML file
     */
    public static <S extends ParameterController> Parent
        getParentPassParams(String path, Object param) { // TODO should this be made private?
        try {
            var loader = new FXMLLoader(TDApp.class.getResource(path));
            Parent root = loader.load();
            loader.<S>getController().setParams(param);
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AnchorPane();
    }

    /**
     * Navigation method to use if data is not being passed to the target scene
     * @param name the name of the scene specified in TDScenes
     * @return The previous scene in the stage
     */
    public static Scene navigateToScene(TDScenes name) {
        return navigateToScene(name, null);
    }

    /**
     * Navigates the current stage to a specified scene.
     * @param name the name of the scene specified in TDScenes
     * @param param the parameter to pass to the scene controller if applicable
     * @return The previous scene in the stage
     */
    public static Scene navigateToScene(TDScenes name, Object param) {
        Scene cScene = mainStage.getScene();
        Parent root;
        switch (name) {
        case WelcomeScreen:
            root = getParent("scenes/WelcomeScreen.fxml");
            break;
        case InitialConfig:
            root =  getParent("scenes/InitialConfig.fxml");
            break;
        case GameScreen:
            root = getParentPassParams("scenes/GameScreen.fxml", param);
            break;
        default:
            root = new AnchorPane();
        }
        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(TDApp.class.getResource("styles/style.css")
                .toExternalForm()); //Loads css which contains theming options
        TDApp.mainStage.setScene(newScene);
        return cScene; // TODO Decide whether to add a navigation stack to TDApp
    }

    public static void ShowErrorMsg(String title, String content) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setContentText(content);
        ButtonType closeDialog = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(closeDialog);
        dialog.showAndWait();
    }

}
