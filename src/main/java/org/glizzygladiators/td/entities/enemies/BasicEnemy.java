package org.glizzygladiators.td.entities.enemies;

import org.glizzygladiators.td.entities.GameDifficulty;

public class BasicEnemy extends Enemy {
    private static final String resourceLocation = "images/obamaTank.png";
    private static final int SPEED_EASY = 1;
    private static final int HEALTH_EASY = 50;

    public BasicEnemy(int x, int y, GameDifficulty difficulty) {
        super(x, y, HEALTH_EASY, resourceLocation, SPEED_EASY);
    }
}
