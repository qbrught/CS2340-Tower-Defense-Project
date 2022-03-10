package org.glizzygladiators.td.entities.towers;

import java.util.HashMap;
import java.util.Map;

import org.glizzygladiators.td.game.GameDifficulty;

public abstract class Tower {

    public static final int SIZE = 50;
    protected final String resourceLocation;
    protected int attackSpeed;
    protected int attackDamage;
    protected int locationX;
    protected int locationY;
    protected int width;
    protected int height;
    protected Map<GameDifficulty, Integer> statsPerDifficulty;

    /**
     * Constructor for a tower
     *
     * @param locationX X coordinate of the tower
     * @param locationY Y coordinate of the tower
     * @param attackSpeed Attack speed of the tower
     * @param attackDamage Attack damage of the tower
     * @param resource Path towards the tower's image
     */
    public Tower(int locationX, int locationY, int attackSpeed, int attackDamage, 
                 String resourceLocation) {
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
        this.locationX = locationX;
        this.locationY = locationY;
        this.width = SIZE;
        this.height = SIZE;
        this.statsPerDifficulty = new HashMap<>();
        this.resourceLocation = resourceLocation;
    }

    public String getResourceLocation() {
        return resourceLocation;
    }

    /**
     * Gets the price of the tower based on the difficulty
     * @param difficulty The difficulty of the game
     * @return the price of the tower
     */
    public int getPrice(GameDifficulty difficulty) {
        return statsPerDifficulty.get(difficulty);
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
     * Gets the X location of the tower
     * @return X location of the tower
     */
    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    /**
     * Gets the Y location of the tower
     * @return Y location of the tower
     */
    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    } 

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}