package org.glizzygladiators.td.entities.towers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.glizzygladiators.td.entities.SymbolicGameObject;
import org.glizzygladiators.td.entities.enemies.Enemy;

import org.glizzygladiators.td.entities.GameDifficulty;

public abstract class Tower extends SymbolicGameObject {

    public static final int SIZE = 50;
    public static final int STARTING_RANGE = 200;
    public static final int UPGRADE_COST_MULTIPLIER = 25;
    protected Map<GameDifficulty, Integer> statsPerDifficulty;
    protected String imgPath;
    private long lastFired;
    protected long attackSpeed; // Lower is faster. This is the number of cycle counts before it attacks again.
    protected int attackDamage;
    int range;
    protected int attackSpeedLevel;
    protected int attackDamageLevel;
    protected int rangeLevel;
    protected int upgradeCost;

    /**
     * Constructor for a tower
     *
     * @param locationX X coordinate of the tower
     * @param locationY Y coordinate of the tower
     * @param imageLocation Path towards the tower's image
     */
    public Tower(int locationX, int locationY, String imageLocation) {
        super(locationX, locationY, SIZE, SIZE, imageLocation);
        this.imgPath = imageLocation;
        this.statsPerDifficulty = new HashMap<>();
        this.lastFired = 0;
        this.attackSpeedLevel = 1;
        this.attackDamageLevel = 1;
        this.rangeLevel = 1;
        this.upgradeCost = (this.attackSpeedLevel + this.attackDamageLevel + this.rangeLevel) * 25;
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
            if (dist > STARTING_RANGE) {
                continue;
            }
            if (dist < minDist) {
                minDist = (int) dist;
                result = e;
            }
        }
        return result;
    }

    public long getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(long attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getAttackSpeedLevel() {
        return attackSpeedLevel;
    }

    public void setAttackSpeedLevel(int attackSpeedLevel) {
        this.attackSpeedLevel = attackSpeedLevel;
    }

    public int getAttackDamageLevel() {
        return attackDamageLevel;
    }

    public void setAttackDamageLevel(int attackDamageLevel) {
        this.attackDamageLevel = attackDamageLevel;
    }

    public int getRangeLevel() {
        return rangeLevel;
    }

    public void setRangeLevel(int rangeLevel) {
        this.rangeLevel = rangeLevel;
    }

    public int getUpgradeCost() {
        return this.upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
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

    public int calculateUpgradeCost() {
        return ((this.getAttackSpeedLevel()) + (this.getAttackDamageLevel()) + (this.getRangeLevel())) * UPGRADE_COST_MULTIPLIER;
    }

    public void upgrade(String s) {
        switch (s) {
            case "Speed":
                this.setAttackSpeedLevel(this.getAttackSpeedLevel() + 1);
                this.setAttackSpeed((this.getAttackSpeed() * 3) / 4);
                this.setUpgradeCost(this.calculateUpgradeCost());
                break;
            case "Damage":
                this.setAttackDamageLevel(this.getAttackDamageLevel() + 1);
                this.setAttackDamage((this.getAttackDamage() * 4) / 3);
                this.setUpgradeCost(this.calculateUpgradeCost());
                break;
            case "Range":
                this.setRangeLevel(this.getRangeLevel() + 1);
                this.setRange((this.getRange() * 4) / 3);
                this.setUpgradeCost(this.calculateUpgradeCost());
                break;
        }
    }
}