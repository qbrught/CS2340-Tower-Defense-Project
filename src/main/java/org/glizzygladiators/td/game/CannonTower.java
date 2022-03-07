package org.glizzygladiators.td.game;

public class CannonTower extends Tower {

    public static final String CANNON_TOWER_IMAGE = "images/obamaCanon.png";
    public static final int CANNON_EASY_PRICE = 75; // TODO: Add the right price.
    public static final int CANNON_MEDIUM_PRICE = 125; // TODO: Add the right price.
    public static final int CANNON_HARD_PRICE = 160; // TODO: Add the right price.
    private static final int INIT_ATTACK_SPEED = 0; // TODO: Add the initial attack speed.
    private static final int INIT_ATTACK_DAMAGE = 0; // TODO: Add the initial attack damage.

    /**
     * Creates a cannon tower instance.
     *
     * @param locationX X coordinate of the instantiated tower
     * @param locationY Y coordinate of the instantiated tower
     */
    public CannonTower(int locationX, int locationY) {
        super(locationX, locationY, INIT_ATTACK_SPEED, INIT_ATTACK_DAMAGE, CANNON_TOWER_IMAGE);
    }

    @Override
    public int getPrice(GameDifficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return CANNON_EASY_PRICE;
            case MEDIUM:
                return CANNON_MEDIUM_PRICE;
            case HARD:
                return CANNON_HARD_PRICE;
            default:
                return -1; // This shouldn't happen.
        }
    }
}