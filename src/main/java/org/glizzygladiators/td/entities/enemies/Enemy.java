package org.glizzygladiators.td.entities.enemies;

import org.glizzygladiators.td.entities.DestroyedCallback;
import org.glizzygladiators.td.entities.MoveableGameObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Enemy extends MoveableGameObject {
    public static final int SIZE = 40;

    private int enemyId;
    private IntegerProperty enemyHealth;
    private final int speed;
    private final int damage;
    private DestroyedCallback callback;
    public Enemy(int x, int y, String resourceLocation, int speed, int health, int damage) {
        super(SIZE, SIZE, resourceLocation);
        this.speed = speed;
        this.enemyHealth = new SimpleIntegerProperty(health);
        this.damage = damage;
        this.callback = null;
    }

    public void setCallback(DestroyedCallback callback) {
        this.callback = callback;
    }

    public void fireCallback(boolean doDamage) {
        if (callback != null) callback.onDestroyed(doDamage);
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
