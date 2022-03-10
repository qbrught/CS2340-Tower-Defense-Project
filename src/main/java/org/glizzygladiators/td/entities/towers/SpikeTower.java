package org.glizzygladiators.td.entities.towers;

import org.glizzygladiators.td.game.GameDifficulty;

public class SpikeTower extends Tower {
    public static final String SPIKE_TOWER_IMAGE = "images/obamaSpike.png";
    public static final int SPIKE_EASY_PRICE = 65;
    public static final int SPIKE_MEDIUM_PRICE = 100;
    public static final int SPIKE_HARD_PRICE = 130;
    private static final int INIT_ATTACK_SPEED = 0;
    private static final int INIT_ATTACK_DAMAGE = 0;

    /**
     * Creates a basic tower instance.
     *
     * @param locationX X coordinate of the instantiated tower
     * @param locationY Y coordinate of the instantiated tower
     */
    public SpikeTower(int locationX, int locationY) {
        super(locationX, locationY, INIT_ATTACK_SPEED, INIT_ATTACK_DAMAGE, SPIKE_TOWER_IMAGE);
        statsPerDifficulty.put(GameDifficulty.EASY, SPIKE_EASY_PRICE);
        statsPerDifficulty.put(GameDifficulty.MEDIUM, SPIKE_MEDIUM_PRICE);
        statsPerDifficulty.put(GameDifficulty.HARD, SPIKE_HARD_PRICE);
    }
}
