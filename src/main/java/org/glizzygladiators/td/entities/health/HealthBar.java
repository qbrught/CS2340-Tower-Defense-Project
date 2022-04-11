package org.glizzygladiators.td.entities.health;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.glizzygladiators.td.entities.enemies.Enemy;




public class HealthBar extends Rectangle {

    public HealthBar(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
        this.setFill(Color.GREEN);
    }

    public void update(Enemy enemy) {
        this.xProperty().bind(enemy.getXProperty());
        this.yProperty().bind(enemy.getYProperty());
        this.widthProperty().bind(enemy.getHealthProperty().multiply(
                (double) enemy.SIZE / enemy.getEnemyHealth()));
    }

}
