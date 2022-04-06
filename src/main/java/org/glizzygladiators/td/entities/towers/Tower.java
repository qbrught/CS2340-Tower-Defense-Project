package org.glizzygladiators.td.entities.towers;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.glizzygladiators.td.entities.SymbolicGameObject;
import org.glizzygladiators.td.entities.enemies.Enemy;

import javafx.util.Pair;

import org.glizzygladiators.td.entities.GameDifficulty;

public abstract class Tower extends SymbolicGameObject {

    public static final int SIZE = 50;
    public static final long CYCLE_COUNT = 50;
    public static final int RANGE = 200;
    protected int attackSpeed;
    protected int attackDamage;
    protected Map<GameDifficulty, Integer> statsPerDifficulty;
    protected String imgPath;
    private long lastFired;

    /**
     * Constructor for a tower
     *
     * @param locationX X coordinate of the tower
     * @param locationY Y coordinate of the tower
     * @param attackSpeed Attack speed of the tower
     * @param attackDamage Attack damage of the tower
     * @param imageLocation Path towards the tower's image
     */
    public Tower(int locationX, int locationY, int attackSpeed, int attackDamage, 
                 String imageLocation) {
        super(locationX, locationY, SIZE, SIZE, imageLocation);
        this.imgPath = imageLocation;
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
        this.statsPerDifficulty = new HashMap<>();
        this.lastFired = 0;
    }

    /**
     * Gets the price of the tower based on the difficulty
     * @param difficulty The difficulty of the game
     * @return the price of the tower
     */
    public int getPrice(GameDifficulty difficulty) {
        return statsPerDifficulty.get(difficulty);
    }

    public void fire(long now) {
        this.lastFired = now;
    } 

    public long getLastFired() {
        return this.lastFired;
    }

    public int getCenterX() {
        return this.getX() + Tower.SIZE / 2;
    }

    public int getCenterY() {
        return this.getY() + Tower.SIZE / 2;
    }

    public Enemy getClosestEnemy(ArrayList<Enemy> enemies) {
        int minDist = 1000;
        Enemy result = null;
        for (var e : enemies) {
            int xDelt = e.getX() - this.getX();
            int yDelt = e.getY() - this.getY();
            double dist = Math.sqrt(xDelt * xDelt + yDelt * yDelt);
            if (dist > RANGE) continue;
            if (dist < minDist) {
                minDist = (int) dist;
                result = e;
            }
        }
        return result;
    }

    /**
     * Gets the attack speed of the tower in (insert unit here based on the Javafx Timer hahahahaha)
     * @return The attack speed of tower in (insert unit here based on the Javafx Timer hahahahahah)
     */
    public int getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Gets the attack damage of the tower
     * @return The attack damage of the tower
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * Returns the path of the Image (don't forget to load with getResource)
     * @return the path of the Image (don't forget to load with getResource)
     */
    public String getImgPath() {
        return imgPath;
    }

    @Override
    public int hashCode() {
        int hash = (int) getX();
        hash = hash * 53 + (int) getY();
        hash = hash * 53 + (int) getWidth();
        hash = hash * 53 + (int) getHeight();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tower)) {
            return false;
        }
        Tower t = (Tower) obj;
        return getX() == t.getX() && getY() == t.getY()
               && getWidth() == t.getWidth() && getWidth() == t.getHeight();
    }
}