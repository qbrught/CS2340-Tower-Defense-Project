package org.glizzygladiators.td.game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.glizzygladiators.td.TDApp;

public abstract class Tower extends javafx.scene.shape.Rectangle {

    public static final int SIZE = 100; // TODO: Change this if needed.
    protected int attackSpeed;
    protected int attackDamage;
    protected int locationX;
    protected int locationY;

    /**
     * Constructor for a tower
     *
     * @param locationX X coordinate of the tower
     * @param locationY Y coordinate of the tower
     * @param attackSpeed Attack speed of the tower
     * @param attackDamage Attack damage of the tower
     * @param resource Path towards the tower's image
     */
    public Tower(int locationX, int locationY, int attackSpeed, int attackDamage, String resource) {
        setX(locationX);
        setY(locationY);
        setWidth(SIZE);
        setHeight(SIZE);
        var resourcePath = TDApp.class.getResource(resource).toExternalForm();
        var pattern = new ImagePattern(new Image(resourcePath));
        setFill(pattern);
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     * Gets the price of the tower based on the difficulty
     * @param difficulty The difficulty of the game // TODO Make this an enum
     * @return the price of the tower
     */
    public abstract int getPrice(GameDifficulty difficulty);

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

    /**
     * Gets the Y location of the tower
     * @return Y location of the tower
     */
    public int getLocationY() {
        return locationY;
    }
}