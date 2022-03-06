package org.glizzygladiators.td.controllers;

import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.game.*;
import org.glizzygladiators.td.game.TowerEnum;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private List<Rectangle> path;
    private ObservableList<Node> gameObjects;
    private GameInstance game;
    private EventHandler<MouseEvent> buyModeHandler = null;


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

        path = new ArrayList<>();
        path.add(new Rectangle(0, 119, 843, 168 - 119));
        path.add(new Rectangle(795, 169, 843 - 795, 334 - 169));
        path.add(new Rectangle(134, 319, 794 - 134, 364 - 319));
        path.add(new Rectangle(125, 364, 169 - 125, 572 - 364));
        path.add(new Rectangle(170, 552, 747 - 170, 602 - 552));
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
        var scene = new Scene(TDApp.getParentPassParams("scenes/BuyMenu.fxml", this));
        scene.getStylesheets().add(TDApp.class.getResource("styles/style.css")
                .toExternalForm());
        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void enterTowerPlacementMode(TowerEnum tower) {
        Scene scene = gamePane.getScene();

        buyModeHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x = (int) mouseEvent.getSceneX();
                int y = (int) mouseEvent.getSceneY();
                x -= Tower.SIZE / 2; // This is so that where you click is the center of where
                y -= Tower.SIZE / 2; // the tower is placed

                boolean checkOverlap = false;
                for (Rectangle part : path) {
                    checkOverlap = overlaps(new Rectangle(x, y, Tower.SIZE, Tower.SIZE), part);
                    if (checkOverlap) {
                        break;
                    }
                }

                // TODO put the following into a loop and show a popup if invalid location
                if (!checkOverlap) {
                    switch (tower) {
                        case BASIC:
                            BasicTower basicTower = new BasicTower(x, y);
                            game.getTowers().add(basicTower);
                            gameObjects.add(basicTower);
                            game.setMoney(game.getMoney() - basicTower.getPrice(game.getDifficulty()));
                            break;
                        case CANNON:
                            CannonTower cannonTower = new CannonTower(x, y);
                            game.getTowers().add(cannonTower);
                            gameObjects.add(cannonTower);
                            game.setMoney(game.getMoney() - cannonTower.getPrice(game.getDifficulty()));
                            break;
                        case SPIKE:
                            SpikeTower spikeTower = new SpikeTower(x, y);
                            game.getTowers().add(spikeTower);
                            gameObjects.add(spikeTower);
                            game.setMoney(game.getMoney() - spikeTower.getPrice(game.getDifficulty()));
                            break;
                    }
                    exitTowerPlacementMode();
                }
            }
        };
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, buyModeHandler);
    }

    public void exitTowerPlacementMode() {
        if (buyModeHandler == null) return;
        gamePane.getScene()
                .removeEventHandler(MouseEvent.MOUSE_CLICKED, buyModeHandler);
        buyModeHandler = null;
    }

    public boolean overlaps(Rectangle r1, Rectangle r2) {
        return r1.getX() <= r2.getX() + r2.getWidth() && r1.getX() + r1.getWidth() >= r2.getX() &&
                r1.getY() <= r2.getY() + r2.getHeight() && r1.getY() + r1.getHeight() >= r2.getY();
    }
}