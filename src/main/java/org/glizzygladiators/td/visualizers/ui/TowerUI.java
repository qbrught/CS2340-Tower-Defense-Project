package org.glizzygladiators.td.visualizers.ui;

import org.glizzygladiators.td.entities.towers.Tower;

public class TowerUI extends RectangleUI {

    private final Tower tower;

    public TowerUI(Tower tower) {
        super(tower.getX(),
              tower.getY(),
              tower.getWidth(),
              tower.getHeight(),
              tower.getResourceLocation());
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }
}