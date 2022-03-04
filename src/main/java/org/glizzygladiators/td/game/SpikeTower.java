package org.glizzygladiators.td.game;

public class SpikeTower extends Tower {

    public static final String SPIKE_TOWER_IMAGE = ""; // TODO: Create an image for this.

    public static final int SPIKE_EASY_PRICE = 0; // TODO: Add the right price.
    public static final int SPIKE_MEDIUM_PRICE = 0; // TODO: Add the right price.
    public static final int SPIKE_HARD_PRICE = 0; // TODO: Add the right price.
    private static final int INIT_ATTACK_SPEED = 0; // TODO: Add the initial attack speed.
    private static final int INIT_ATTACK_DAMAGE = 0; // TODO: Add the initial attack damage.

    public SpikeTower(int locationX, int locationY) {
        super(locationX, locationY, INIT_ATTACK_SPEED, INIT_ATTACK_DAMAGE);
    }

    public int getPrice(GameDifficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return SPIKE_EASY_PRICE;
            case MEDIUM:
                return SPIKE_MEDIUM_PRICE;
            case HARD:
                return SPIKE_HARD_PRICE;
            default:
                return -1; // This shouldn't happen.
        }
    }
}