package org.glizzygladiators.td.visualizers.ui;

import org.glizzygladiators.td.entities.enemies.Enemy;

public class EnemyUI extends RectangleUI {
    private final Enemy enemy;

    public EnemyUI(Enemy enemy) {
        super(enemy.getX(),
              enemy.getY(),
              enemy.getWidth(),
              enemy.getHeight(),
              enemy.getResourceLocation());
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }
    
    public void move(int x, int y) {
        enemy.setX(x);
        enemy.setY(y);
        setX(x);
        setY(y);
    }

    public void clear() {
        
    }
}
