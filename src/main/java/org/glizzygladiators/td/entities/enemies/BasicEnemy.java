package org.glizzygladiators.td.entities.enemies;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.glizzygladiators.td.entities.GameDifficulty;

public class BasicEnemy extends Enemy {
    private static final String resourceLocation = "images/obamaTank.png";
    public static final int SPEED = 1;

    private static final Map<GameDifficulty, int[]> difficulties;
    static {
        difficulties = new HashMap<>();
        difficulties.put(GameDifficulty.EASY, new int[]{50, 10});
        difficulties.put(GameDifficulty.MEDIUM, new int[]{60, 15});
        difficulties.put(GameDifficulty.HARD, new int[]{70, 20});
    }
    public BasicEnemy(int x, int y, GameDifficulty difficulty) {
        super(x, y, resourceLocation, SPEED, 
              difficulties.get(difficulty)[0],
              difficulties.get(difficulty)[1]);
    }
}
