package org.glizzygladiators.td.visualizers;

import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.entities.towers.Tower;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class TowerUI extends javafx.scene.shape.Rectangle {

    private final Tower tower;

    public TowerUI(Tower tower, String resourceLocation) {
        setX(tower.getLocationX());
        setY(tower.getLocationY());
        setWidth(tower.getWidth());
        setHeight(tower.getHeight());
        var resourcePath = TDApp.class.getResource(resourceLocation).toExternalForm();
        var pattern = new ImagePattern(new Image(resourcePath));
        setFill(pattern);
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }
}