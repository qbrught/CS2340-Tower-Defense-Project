package org.glizzygladiators.td.entities.towers;

import org.glizzygladiators.td.entities.GameDifficulty;

import java.util.ArrayList;

public class BoostTower extends Tower {
    public static final String BOOST_TOWER_IMAGE = "images/obamaBoost.png";
    public static final int BOOST_EASY_PRICE = 65;
    public static final int BOOST_MEDIUM_PRICE = 100;
    public static final int BOOST_HARD_PRICE = 130;
    private int boostLevel;

    /**
     * Creates a basic tower instance.
     *
     * @param locationX X coordinate of the instantiated tower
     * @param locationY Y coordinate of the instantiated tower
     */
    public BoostTower(int locationX, int locationY) {
        super(locationX, locationY, BOOST_TOWER_IMAGE);
        statsPerDifficulty.put(GameDifficulty.EASY, BOOST_EASY_PRICE);
        statsPerDifficulty.put(GameDifficulty.MEDIUM, BOOST_MEDIUM_PRICE);
        statsPerDifficulty.put(GameDifficulty.HARD, BOOST_HARD_PRICE);
        this.boostLevel = 10;
    }

    public void boostOthers(ArrayList<Tower> towers) {
        int radius = 100;
        for (var t : towers) {
            int dx = t.getCenterX() - this.getCenterX();
            int dy =  t.getCenterY() - this.getCenterY();
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= 100) {
                t.setAttackDamage(t.getAttackDamage() + boostLevel);
                t.setAttackSpeed(t.getAttackSpeed() - boostLevel);
            }
        }
    }

}
