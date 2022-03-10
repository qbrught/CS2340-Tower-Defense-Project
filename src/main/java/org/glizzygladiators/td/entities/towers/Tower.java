package org.glizzygladiators.td.entities.towers;

import java.util.HashMap;
import java.util.Map;

import org.glizzygladiators.td.entities.Rectangle;
import org.glizzygladiators.td.game.GameDifficulty;

public abstract class Tower extends Rectangle {

    public static final int SIZE = 50;
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
        super(locationX, locationY, SIZE, SIZE, resourceLocation);
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
        this.statsPerDifficulty = new HashMap<>();
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

    @Override
    public int hashCode() {
        int hash = x;
        hash = hash * 53 + y;
        hash = hash * 53 + width;
        hash = hash * 53 + height;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tower)) return false;
        Tower t = (Tower) obj;
        return x == t.getX() && y == t.getY()
               && width == t.getWidth() && height == t.getHeight(); 
    }
}