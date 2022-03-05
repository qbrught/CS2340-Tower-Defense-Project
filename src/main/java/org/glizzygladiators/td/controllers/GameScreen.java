package org.glizzygladiators.td.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.glizzygladiators.td.game.BasicTower;
import org.glizzygladiators.td.game.CannonTower;
import org.glizzygladiators.td.game.GameInstance;
import org.glizzygladiators.td.game.SpikeTower;

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
     *
     * @param url            url
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

    /**
     * Purchases a tower and allows a user to place it.
     * @param mouseEvent The mouse event that activates the buy tower process.
     */
    public void buyTower(MouseEvent mouseEvent) {
        String s = ((Button) mouseEvent.getSource()).getId();

        // TODO: Check to see if the player has sufficient funds for the tower type. If not, return this method.

        Scene scene = ((Button) mouseEvent.getSource()).getScene();
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x = (int) mouseEvent.getSceneX();
                int y = (int) mouseEvent.getSceneY();

                if (true) { // TODO: Change this from "true" to the "not overlapping" condition.
                    switch (s) {
                        case "basicTower":
                            BasicTower basicTower = new BasicTower(x, y);
                            game.getTowers().add(basicTower);
                            gameObjects.add(basicTower);
                            game.setMoney(game.getMoney() - basicTower.getPrice(game.getDifficulty()));
                            break;
                        case "cannonTower":
                            CannonTower cannonTower = new CannonTower(x, y);
                            game.getTowers().add(cannonTower);
                            gameObjects.add(cannonTower);
                            game.setMoney(game.getMoney() - cannonTower.getPrice(game.getDifficulty()));
                            break;
                        case "spikeTower":
                            SpikeTower spikeTower = new SpikeTower(x, y);
                            game.getTowers().add(spikeTower);
                            gameObjects.add(spikeTower);
                            game.setMoney(game.getMoney() - spikeTower.getPrice(game.getDifficulty()));
                            break;
                    }
                    scene.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                }
            }
        });
    }
}