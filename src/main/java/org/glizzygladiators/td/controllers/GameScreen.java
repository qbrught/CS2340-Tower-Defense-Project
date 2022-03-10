package org.glizzygladiators.td.controllers;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.entities.towers.*;
import org.glizzygladiators.td.visualizers.TowerUI;
import org.glizzygladiators.td.game.*;
import org.glizzygladiators.td.game.TowerEnum;

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

                tower.setLocationX(x);
                tower.setLocationY(y);

                if (isInvalidTowerLocation(tower)) {
                    TDApp.showErrorMsg("Invalid Location",
                            "You can't place a tower there");
                    return;
                }

                TowerUI towerUI = new TowerUI(tower, tower.getResourceLocation());
                game.getTowers().add(towerUI);
                gameObjects.add(towerUI);
                game.setMoney(game.getMoney() - tower.getPrice(game.getDifficulty()));
                scene.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                buyModeHandler = null;
                exitTowerPlacementMode();
            }
        };
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, buyModeHandler);
        Image image = new Image(TDApp.getResourcePath(BasicTower.BASIC_TOWER_IMAGE));
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

    public static boolean hasCollision(Tower r1, Rectangle r2) {
        return r1.getLocationX() <= r2.getX() + r2.getWidth()
                && r1.getLocationX() + Tower.SIZE >= r2.getX()
                && r1.getLocationY() <= r2.getY() + r2.getHeight()
                && r1.getLocationY() + Tower.SIZE >= r2.getY();
    }

    public static boolean towerPlacedOffMap(int x, int y) {
        return (x > 1000 - Tower.SIZE || x < 0) 
               || (y > 750 - Tower.SIZE || y < 0);
    }

    public static boolean collidesWithMonument(Tower gameObj, Monument monument) {
        return hasCollision(gameObj, monument);
    }

    private boolean isInvalidTowerLocation(Tower testTower) {
        return towerPlacedOffMap((int) testTower.getLocationX(), (int) testTower.getLocationY()) 
               || collidesWithPath(testTower) 
               || collidesWithTower(testTower) 
               || collidesWithMonument(testTower, game.getMonument());
    }

    private boolean collidesWithPath(Tower gameObj) {
        return game.getMap().hasCollisionWithPath(gameObj);
    }

    private boolean collidesWithTower(Tower gameObj) {
        for (TowerUI t : game.getTowers()) {
            if (hasCollision(gameObj, t)) {
                return true;
            }
        }
        return false;
    }
}
