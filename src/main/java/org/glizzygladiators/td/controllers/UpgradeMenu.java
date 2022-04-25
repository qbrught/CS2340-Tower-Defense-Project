package org.glizzygladiators.td.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.entities.towers.BasicTower;
import org.glizzygladiators.td.entities.towers.BoostTower;
import org.glizzygladiators.td.entities.towers.CannonTower;
import org.glizzygladiators.td.entities.towers.Tower;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class UpgradeMenu implements ParameterController {

    private static Tower workingTower;
    public static void setWorkingTower(Tower t) {
        workingTower = t;
    }

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView tower;

    @FXML
    private Text text;

    @FXML
    private Label upgradeSpeed;

    @FXML
    private Label upgradeDamage;

    @FXML
    private Label upgradeRange;

    @FXML
    private Label upgradeCost;

    private GameScreen gsController;

    @Override
    public void setParams(Object params) {
        gsController = (GameScreen) params;
        tower.setImage(new Image("/org/glizzygladiators/td/" + workingTower.getImgPath()));
        if (workingTower instanceof BasicTower) {
            text.setText("Upgrade your basic tower!");
        } else if (workingTower instanceof CannonTower) {
            text.setText("Upgrade your cannon tower!");
        } else if (workingTower instanceof BoostTower) {
            text.setText("Upgrade your boost tower!");
        }
        upgradeSpeed.setText("Current Speed Level: " + workingTower.getAttackSpeedLevel());
        upgradeDamage.setText("Current Damage Level: " + workingTower.getAttackDamageLevel());
        upgradeRange.setText("Current Range Level: " + workingTower.getRangeLevel());
        upgradeCost.setText("Price: " + workingTower.getUpgradeCost());
    }

    public void upgrade(MouseEvent mouseEvent) {
        String s = ((Button) mouseEvent.getSource()).getText().substring("Upgrade ".length());
        if (gsController.getGameDriver().getGame().getMoney() < workingTower.getUpgradeCost()) {
            TDApp.showErrorMsg("Insufficient Funds", "You do not have enough money to buy this upgrade!");
            return;
        }
        gsController.getGameDriver().getGame().setMoney(gsController.getGameDriver().getGame().getMoney() - workingTower.getUpgradeCost());
        gsController.getGameDriver().getGame().setMoneySpent(gsController.getGameDriver().getGame().getMoneySpent() + workingTower.getUpgradeCost());
        switch (s) {
            case ("Speed"):
                if (workingTower.getAttackSpeedLevel() == 3) {
                    TDApp.showErrorMsg("Max Level", "You already have the max speed level!");
                } else {
                    for (Tower t : gsController.getGameDriver().getGame().getTowers()) {
                        if (t.equals(workingTower)) {
                            t.upgrade(s);
                            workingTower = t;
                            break;
                        }
                    }
                }
                break;
            case ("Damage"):
                if (workingTower.getAttackDamageLevel() == 3) {
                    TDApp.showErrorMsg("Max Level", "You already have the max damage level!");
                } else {
                    for (Tower t : gsController.getGameDriver().getGame().getTowers()) {
                        if (t.equals(workingTower)) {
                            t.upgrade(s);
                            workingTower = t;
                            break;
                        }
                    }
                }
                break;
            case ("Range"):
                if (workingTower.getRangeLevel() == 3) {
                    TDApp.showErrorMsg("Max Level", "You already have the max range level!");
                } else {
                    for (Tower t : gsController.getGameDriver().getGame().getTowers()) {
                        if (t.equals(workingTower)) {
                            t.upgrade(s);
                            workingTower = t;
                            break;
                        }
                    }
                }
                break;
            default:
                System.out.println("You definitely did something you shouldn't've done.");
        }
        upgradeSpeed.setText("Current Speed Level: " + workingTower.getAttackSpeedLevel());
        upgradeDamage.setText("Current Damage Level: " + workingTower.getAttackDamageLevel());
        upgradeRange.setText("Current Range Level: " + workingTower.getRangeLevel());
        upgradeCost.setText("Price: " + workingTower.getUpgradeCost());
    }
}
