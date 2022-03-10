package org.glizzygladiators.td.entities.towers;

import org.glizzygladiators.td.game.GameDifficulty;

public class BasicTower extends Tower {

    public static final String BASIC_TOWER_IMAGE = "images/obamaBasic.png";
    public static final int BASIC_EASY_PRICE = 50;
    public static final int BASIC_MEDIUM_PRICE = 75;
    public static final int BASIC_HARD_PRICE = 100;
    private static final int INIT_ATTACK_SPEED = 0;
    private static final int INIT_ATTACK_DAMAGE = 0;

    /**
     * Creates a basic tower instance.
     *
     * @param locationX X coordinate of the instantiated tower
     * @param locationY Y coordinate of the instantiated tower
     */
    public BasicTower(int locationX, int locationY) {
        super(locationX, locationY, INIT_ATTACK_SPEED, INIT_ATTACK_DAMAGE, BASIC_TOWER_IMAGE);
        statsPerDifficulty.put(GameDifficulty.EASY, BASIC_EASY_PRICE);
        statsPerDifficulty.put(GameDifficulty.MEDIUM, BASIC_MEDIUM_PRICE);
        statsPerDifficulty.put(GameDifficulty.HARD, BASIC_HARD_PRICE);
    }
}