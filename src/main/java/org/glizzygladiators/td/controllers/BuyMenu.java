package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.entities.towers.BasicTower;
import org.glizzygladiators.td.entities.towers.CannonTower;
import org.glizzygladiators.td.entities.towers.SpikeTower;
import org.glizzygladiators.td.entities.towers.Tower;

public class BuyMenu implements ParameterController {

    @FXML
    private ScrollPane root;

    private GameScreen gsController;

    @Override
    public void setParams(Object params) {
        gsController = (GameScreen) params;
    }

    public void buyTower(Tower tower) {
        // check if tower can be purchased
        var playerMoney = gsController.getGameDriver().getGame().getMoney();
        int towerCost = tower.getPrice(gsController.getGameDriver().getGame().getDifficulty());
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
        buyTower(new BasicTower(0, 0));
    }

    public void buyCannonTower(MouseEvent mouseEvent) {
        buyTower(new CannonTower(0, 0));
    }

    public void buySpikeTower(MouseEvent mouseEvent) {
        buyTower(new SpikeTower(0, 0));
    }
}
