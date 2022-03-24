package org.glizzygladiators.td.entities.enemies;

import org.glizzygladiators.td.entities.GameMap;
import org.glizzygladiators.td.entities.SymbolicGameObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Enemy extends SymbolicGameObject {
    public static final int SIZE = 40;

    private int enemyId;
    private IntegerProperty enemyHealth;
    private final int speed;
    private final int damage;
    public Enemy(int x, int y, String resourceLocation, int speed, int health, int damage) {
        super(x, y, SIZE, SIZE, resourceLocation);
        this.speed = speed;
        this.enemyHealth = new SimpleIntegerProperty(health);
        this.damage = damage;
    }

    public void setEnemyId(int id) {
        this.enemyId = id;
    }

    public IntegerProperty getHealthProperty() {
        return enemyHealth;
    }

    public int getEnemyHealth() {
        return enemyHealth.getValue();
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth.setValue(enemyHealth);
    }

    public int getEnemyId() {
        return enemyId;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }
}
