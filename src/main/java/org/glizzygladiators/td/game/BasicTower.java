package org.glizzygladiators.td.game;

public class BasicTower extends Tower {

    public static final String BASIC_TOWER_IMAGE = ""; // TODO: Create an image for this.
    public static final int BASIC_EASY_PRICE = 0; // TODO: Add the right price.
    public static final int BASIC_MEDIUM_PRICE = 0; // TODO: Add the right price.
    public static final int BASIC_HARD_PRICE = 0; // TODO: Add the right price.
    private static final int INIT_ATTACK_SPEED = 0; // TODO: Add the initial attack speed.
    private static final int INIT_ATTACK_DAMAGE = 0; // TODO: Add the initial attack damage.

    /**
     * Creates a basic tower instance.
     *
     * @param locationX X coordinate of the instantiated tower
     * @param locationY Y coordinate of the instantiated tower
     */
    public BasicTower(int locationX, int locationY) {
        super(locationX, locationY, INIT_ATTACK_SPEED, INIT_ATTACK_DAMAGE, BASIC_TOWER_IMAGE);
    }

    @Override
    public int getPrice(GameDifficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return BASIC_EASY_PRICE;
            case MEDIUM:
                return BASIC_MEDIUM_PRICE;
            case HARD:
                return BASIC_HARD_PRICE;
            default:
                return -1; // This shouldn't happen.
        }
    }
}