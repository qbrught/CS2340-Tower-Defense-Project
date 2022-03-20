package org.glizzygladiators.td.controllers;

import javafx.application.Platform;
import javafx.animation.PathTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.entities.GameInstance;
import org.glizzygladiators.td.entities.GameMap;
import org.glizzygladiators.td.entities.towers.*;
import org.glizzygladiators.td.entities.GameDifficulty;
import org.glizzygladiators.td.entities.enemies.*;
import org.glizzygladiators.td.visualizers.GameInstanceDriver;
import org.glizzygladiators.td.visualizers.ui.TowerUI;
import org.glizzygladiators.td.visualizers.ui.EnemyUI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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
    private GameInstanceDriver game;

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
        GameInstanceDriver.setInstance((GameInstance) params);
        game = GameInstanceDriver.getDriver();

        gameObjects.add(game.getMonument());

        moneyLabel.textProperty().bind(game.getGame().getMoneyProperty().asString());
        healthLabel.textProperty().bind(game.getGame().getHealthProperty().asString());

        PropertyChangeListener towerListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName() == GameInstanceDriver.TOWER_ACTION) {
                    if (evt.getOldValue() != "") {
                        gameObjects.remove((TowerUI) evt.getOldValue());
                    } else {
                        gameObjects.add((TowerUI) evt.getNewValue());
                    }
                }
            }
        };
        game.addPropertyChangeListener(towerListener);
        PropertyChangeListener enemyListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName() == GameInstanceDriver.SPAWN_ENEMY) {
                    gameObjects.add((Node) evt.getNewValue());
                }
            }
        };
        game.addPropertyChangeListener(enemyListener);
    }

    /**
     * Returns the GameInstance object associated with this session
     * @return the GameInstance object associated with this session
     */
    public GameInstanceDriver getGameDriver() {
        return game;
    }

    public void spawnEnemies(MouseEvent mouseEvent) {
        Timer timer = new Timer(false);
        long spacing = 1000;
        int numEnemies = 3;
        for (int i = 0; i < numEnemies; i++) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        BasicEnemy basicEnemy = new BasicEnemy(0, 0, GameDifficulty.EASY);
                        EnemyUI enemyUI = new EnemyUI(basicEnemy);
                        GameMap map = game.getGame().getMap();
                        gameObjects.add(enemyUI);
                        PathTransition transition = 
                            new PathTransition(Duration.millis(2000), map.getEnemyPath(), enemyUI);
                        transition.setCycleCount(1);
                        transition.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                game.getGame().setHealth(game.getGame().getHealth() - 5);
                                gameObjects.remove(enemyUI);
                            }
                        });
                        transition.play();
                    });
                }
            }, i * spacing);
        }
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

    public void enterTowerPlacementMode(Tower tower) {
        Scene scene = ((Stage) gamePane.getScene().getWindow()).getScene();

        if (buyModeHandler != null) {
            TDApp.showErrorMsg("Invalid State", "You're already buying something else");
            return;
        }


        buyModeHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x = (int) mouseEvent.getSceneX();
                int y = (int) mouseEvent.getSceneY();
                x -= Tower.SIZE / 2; // This is so that where you click is the center of where
                y -= Tower.SIZE / 2; // the tower is placed

                tower.setX(x);
                tower.setY(y);

                if (game.getGame().isInvalidTowerLocation(tower)) {
                    TDApp.showErrorMsg("Invalid Location",
                            "You can't place a tower there");
                    return;
                }
                game.addTower(tower);
                game.setMoney(game.getGame().getMoney() 
                              - tower.getPrice(game.getGame().getDifficulty()));
                scene.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                buyModeHandler = null;
                exitTowerPlacementMode();
            }
        };
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, buyModeHandler);
        Image image = new Image(TDApp.getResourcePath(tower.getImgPath()));
        scene.setCursor(new ImageCursor(image));
    }

    public void exitTowerPlacementMode() {
        if (buyModeHandler != null) {
            ((Stage) gamePane.getScene().getWindow())
                    .removeEventHandler(MouseEvent.MOUSE_CLICKED, buyModeHandler);
            buyModeHandler = null;
        }
        gamePane.getScene().setCursor(Cursor.DEFAULT);
    }
}
