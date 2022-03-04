package org.glizzygladiators.td.game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.glizzygladiators.td.TDApp;

public abstract class Tower extends javafx.scene.shape.Rectangle {

    // Removed price from the attributes that Tower has due to its nature.
    protected int attackSpeed;
    protected int attackDamage;
    protected int locationX;
    protected int locationY;

    public Tower(int locationX, int locationY, int attackSpeed, int attackDamage) {
        setX(locationX);
        setY(locationY);
        setWidth(0); // TODO: Change this to the tower's actual size.
        setHeight(0); // TODO: Change this to the tower's actual size.
//        var resourcePath = TDApp.class.getResource(resource).toExternalForm();
//        var pattern = new ImagePattern(new Image(resourcePath));
//        setFill(pattern);
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

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }
}