package org.glizzygladiators.td.visualizers.ui;

import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.entities.enemies.Enemy;

public class EnemyUI extends Rectangle {
    private final Enemy enemy;
    public EnemyUI(Enemy enemy) {
        super(enemy.getX(),
              enemy.getY(),
              enemy.getWidth(),
              enemy.getHeight());
        setFill(new ImagePattern(new Image(TDApp.getResourcePath(enemy.getImgPath()))));
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public int getHealth() {
        return enemy.getEnemyHealth();
    }

    public IntegerProperty getHealthProperty() {
        return enemy.getHealthProperty();
    }

    public void clear() {
        
    }
}
