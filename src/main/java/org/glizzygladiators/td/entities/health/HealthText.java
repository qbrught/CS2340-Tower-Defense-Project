package org.glizzygladiators.td.entities.health;

import javafx.scene.text.Text;
import org.glizzygladiators.td.entities.enemies.Enemy;

public class HealthText extends Text {
    public HealthText(int x, int y, String text) {
        super(x, y, text);
        this.setStyle("-fx-font: 20 arial;");

    }

    public void update(Enemy enemy) {
        this.xProperty().bind(enemy.getXProperty());
        this.yProperty().bind(enemy.getYProperty());
        this.textProperty().bind(enemy.getHealthProperty().asString());
    }

}
