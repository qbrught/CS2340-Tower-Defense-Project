package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.game.Tower;
import org.glizzygladiators.td.game.TowerEnum;

public class BuyMenu implements ParameterController {

    @FXML
    private ScrollPane root;

    private GameScreen gsController;

    @Override
    public void setParams(Object params) {
        gsController = (GameScreen) params;
    }

    public void buyTower(TowerEnum tower) {
        // check if tower can be purchased
        var playerMoney = gsController.getGame().getMoney();
        var towerCost = Tower.getPrice(tower, gsController.getGame().getDifficulty());
        if (playerMoney < towerCost) {
            TDApp.showErrorMsg("Insufficient Funds!",
                    "You do not have the money required to buy this tower\n"
                            + "Your money: " + playerMoney + "\n"
                            + "Tower cost: " + towerCost + "\n");
            return;
        }

        gsController.enterTowerPlacementMode(tower);
        ((Stage) root.getScene().getWindow()).close();
    }

    public void buyBasicTower(MouseEvent mouseEvent) {
        buyTower(TowerEnum.BASIC);
    }

    public void buyCannonTower(MouseEvent mouseEvent) {
        buyTower(TowerEnum.CANNON);
    }

    public void buySpikeTower(MouseEvent mouseEvent) {
        buyTower(TowerEnum.SPIKE);
    }
}
