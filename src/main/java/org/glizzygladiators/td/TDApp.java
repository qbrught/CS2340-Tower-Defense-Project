package org.glizzygladiators.td;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.glizzygladiators.td.controllers.ParameterController;

import java.io.IOException;
import java.util.Objects;


public class TDApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = TDApp.getParent("scenes/WelcomeScreen.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getScene().getStylesheets().add(TDApp.class.getResource("styles/style.css")
                .toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
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
     * Navigates the current stage to a specified scene.
     * @param scene old parent scene
     * @param newRoot new parent scene to navigate to
     * @return The previous scene in the stage
     */
    public static Parent navigateToRoot(Scene scene, Parent newRoot) {
        Parent oldParent = scene.getRoot();
        scene.setRoot(newRoot);
        return oldParent;
    }

    public static void showErrorMsg(String title, String content) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setContentText(content);
        ButtonType closeDialog = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(closeDialog);
        dialog.showAndWait();
    }
}
