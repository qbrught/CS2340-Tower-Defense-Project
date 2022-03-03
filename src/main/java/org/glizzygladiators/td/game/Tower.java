package org.glizzygladiators.td.game;

public abstract class Tower extends javafx.scene.shape.Rectangle {




    /**
     * Gets the price of the tower based on the difficulty
     * @param difficulty The difficulty of the game // TODO Make this an enum
     * @return the price of the tower
     */
    public abstract int getPrice(String difficulty);

    /**
     * Gets the attack speed of the tower in (insert unit here based on the Javafx Timer hahahahaha)
     * @return The attack speed of tower in (insert unit here based on the Javafx Timer hahahahahah)
     */
    public abstract int getAttackSpeed();

}
