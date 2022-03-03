package org.glizzygladiators.td.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.glizzygladiators.td.game.GameInstance;

import java.net.URL;
import java.util.ResourceBundle;

public class GameScreen implements ParameterController, Initializable {


    @FXML
    private StackPane sideBarPane;
    @FXML
    private Group gamePane;

    @FXML
    private Label moneyLabel;
    @FXML
    private Label healthLabel;

    private ObservableList<Node> gameObjects;
    private GameInstance game;



    /**
     * Runs code right after FXML objects are initialized
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameObjects = gamePane.getChildren();
        gamePane.prefWidth(1000);
        gamePane.prefHeight(750);
    }

    @Override
    public void setParams(Object params) {
        game = (GameInstance) params;
        gameObjects.add(game.getMonument());

        moneyLabel.textProperty().bind(game.getMoneyProperty().asString());
        healthLabel.textProperty().bind(game.getHealthProperty().asString());
    }

    /**
     * Returns the GameInstance object associated with this session
     * @return the GameInstance object associated with this session
     */
    public GameInstance getGame() {
        return game;
    }

}
