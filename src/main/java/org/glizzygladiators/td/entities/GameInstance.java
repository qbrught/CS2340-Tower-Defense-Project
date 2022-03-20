package org.glizzygladiators.td.entities;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.glizzygladiators.td.entities.towers.Tower;
import org.glizzygladiators.td.entities.enemies.*;

public class GameInstance {

    private static int enemyCounter = 0;

    private String name;
    private GameDifficulty difficulty;
    private IntegerProperty money;
    private IntegerProperty health;
    private ArrayList<Tower> towers;
    private Map<Integer, Enemy> enemies;
    private Monument monument;
    private GameMap map;

    /**
     * Initializes a GameInstance object
     * @param inputName The name of the player
     * @param inputDifficulty the difficulty of the game
     */
    public GameInstance(String inputName,
                        GameDifficulty inputDifficulty) {
        name = inputName;
        difficulty = inputDifficulty;
        money = new SimpleIntegerProperty(getStartingMoney());
        health = new SimpleIntegerProperty(getStartingHealth());
        towers = new ArrayList<Tower>();
        enemies = new HashMap<Integer, Enemy>();
        monument = new Monument(700, 475);
        map = new GameMap();
    }

    /**
     * Gets the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the difficulty that the player selected.
     * @return the difficulty the player selected.
     */
    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Returns the player's money
     * @return the player's money
     */
    public int getMoney() {
        return money.get();
    }

    /**
     * Sets the player's money
     * @param newMoney the player's money
     */
    public void setMoney(int newMoney) {
        System.out.println("newMoney: " + newMoney);
        money.set(newMoney);
        System.out.println("Updated: " + this.money.get());
    }

    /**
     * Returns the property containing the player's money
     * @return the property containing the player's money
     */
    public IntegerProperty getMoneyProperty() {
        return money;
    }

    /**
     * Returns the monument's health
     * @return the monument's health
     */
    public int getHealth() {
        return health.get();
    }

    /**
     * Sets the monument's health
     * @param newHealth the monument's health
     */
    public void setHealth(int newHealth) {
        this.health.set(newHealth);
    }

    /**
     * Returns the IntegerProperty containing the monument's health
     * @return the IntegerProperty containing the monument's health
     */
    public IntegerProperty getHealthProperty() {
        return health;
    }

    /**
     * Returns an Observable list of towers
     * @return an Observable list of towers
     */
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public boolean isInvalidTowerLocation(Tower tower) {
        return tower.isOutOfBounds()
               || map.hasCollisionWithPath(tower)
               || collidesWithTower(tower) 
               || monument.collidesWithMonument(tower);
    }

    private boolean collidesWithTower(Tower gameObj) {
        for (Tower t : towers) {
            if (t.hasCollision(gameObj)) {
                return true;
            }
        }
        return false;
    }

    public void addEnemy(org.glizzygladiators.td.entities.enemies.Enemy enemy) {
        enemy.setEnemyId(enemyCounter++);
        enemies.put(enemy.getEnemyId(), enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    /**
     * Returns an Observable list of enemies
     * @return an Observable list of enemies
     */
    public Map<Integer, Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Returns the monument associated with this GameInstance
     * @return the monument associated with this GameInstance
     */
    public Monument getMonument() {
        return monument;
    }

    /**
     * Returns the map that this GameInstance uses
     * @return the map that this GameInstance uses
     */
    public GameMap getMap() {
        return map;
    }

    /**
     * Returns the amount of money the player starts with
     * @return the amount of money the player starts with
     */
    private int getStartingMoney() {
        switch (difficulty) {
        case EASY: return 500;
        case MEDIUM: return 375;
        case HARD: return 250;
        default: return 0; // This case should never be hit
        }
    }

    /**
     * Returns the amount of health the monument starts with
     * @return the amount of health the monument starts with
     */
    private int getStartingHealth() {
        switch (difficulty) {
        case EASY: return 200;
        case MEDIUM: return 150;
        case HARD: return 100;
        default: return 0; // This case should never be hit
        }
    }
}
