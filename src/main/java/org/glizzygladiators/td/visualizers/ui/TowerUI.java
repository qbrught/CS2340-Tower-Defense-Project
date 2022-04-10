package org.glizzygladiators.td.visualizers.ui;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.entities.towers.Tower;

public class TowerUI extends Rectangle {

    private final Tower tower;

    public TowerUI(Tower tower) {
        super(tower.getX(),
              tower.getY(),
              tower.getWidth(),
              tower.getHeight());
        setFill(new ImagePattern(new Image(TDApp.getResourcePath(tower.getImgPath()))));
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }
}