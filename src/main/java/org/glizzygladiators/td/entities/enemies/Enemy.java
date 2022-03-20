package org.glizzygladiators.td.entities.enemies;

import org.glizzygladiators.td.entities.GameMap;
import org.glizzygladiators.td.entities.SymbolicGameObject;

public class Enemy extends SymbolicGameObject {
    public static final int SIZE = 40;

    private int enemyId;
    private int enemyHealth;
    private final int speed;
    public Enemy(int x, int y, int enemyHealth, String resourceLocation, int speed) {
        super(x, y, SIZE, SIZE, resourceLocation);
        this.speed = speed;
        this.enemyHealth = enemyHealth;
    }

    public void setEnemyId(int id) {
        this.enemyId = id;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public int getEnemyId() {
        return enemyId;
    }

    public int getSpeed() {
        return speed;
    }
}
