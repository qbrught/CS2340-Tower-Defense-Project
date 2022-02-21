package org.glizzygladiators.td.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.glizzygladiators.td.game.GameConfig;
import org.glizzygladiators.td.game.Monument;

import java.net.URL;
import java.util.ResourceBundle;

public class GameScreen implements ParameterController, Initializable {

    @FXML
    private Label moneyLabel;
    @FXML
    private Label healthLabel;
    @FXML
    private Group gamePane;
    private ObservableList<Node> gameObjects;
    private GameConfig config;

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
        gameObjects.add(new Monument(700, 475, "images/monument.jpg"));
    }

    @Override
    public void setParams(Object params) {
        config = (GameConfig) params;
        setMoney(config.getStartingMoney());
        setHealth(config.getStartingHealth());
    }

    /**
     * Returns the amount of money the player has
     * @return the amount of money the player has
     */
    public int getMoney() {
        return Integer.parseInt(moneyLabel.getText());
    }

    /**
     * Sets the amount of money the user has
     * @param money the amount of money the user has
     */
    public void setMoney(int money) {
        moneyLabel.setText(money + "");
    }

    /**
     * Returns the amount of health the monument has
     * @return the amount of health the monument has
     */
    public int getHealth() {
        return Integer.parseInt(healthLabel.getText());
    }

    /**
     * Sets the amount of health the monument has
     * @param health the amount of health the monument has
     */
    public void setHealth(int health) {
        healthLabel.setText(health + "");
    }
}
