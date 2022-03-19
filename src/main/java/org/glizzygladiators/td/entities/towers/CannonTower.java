package org.glizzygladiators.td.entities.towers;

import org.glizzygladiators.td.entities.GameDifficulty;

public class CannonTower extends Tower {
    public static final String CANNON_TOWER_IMAGE = "images/obamaCanon.png";
    public static final int CANNON_EASY_PRICE = 75;
    public static final int CANNON_MEDIUM_PRICE = 125;
    public static final int CANNON_HARD_PRICE = 160;
    private static final int INIT_ATTACK_SPEED = 0;
    private static final int INIT_ATTACK_DAMAGE = 0;

    /**
     * Creates a basic tower instance.
     *
     * @param locationX X coordinate of the instantiated tower
     * @param locationY Y coordinate of the instantiated tower
     */
    public CannonTower(int locationX, int locationY) {
        super(locationX, locationY, INIT_ATTACK_SPEED, INIT_ATTACK_DAMAGE, CANNON_TOWER_IMAGE);
        statsPerDifficulty.put(GameDifficulty.EASY, CANNON_EASY_PRICE);
        statsPerDifficulty.put(GameDifficulty.MEDIUM, CANNON_MEDIUM_PRICE);
        statsPerDifficulty.put(GameDifficulty.HARD, CANNON_HARD_PRICE);
    }
}
