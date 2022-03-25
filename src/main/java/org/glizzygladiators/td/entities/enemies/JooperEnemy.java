package org.glizzygladiators.td.entities.enemies;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.glizzygladiators.td.entities.GameDifficulty;

public class JooperEnemy extends Enemy {
    private static final String resourceLocation = "images/Jooper.png";
    public static final int SPEED = 2;

    private static final Map<GameDifficulty, int[]> difficulties;
    static {
        difficulties = new HashMap<>();
        difficulties.put(GameDifficulty.EASY, new int[]{50, 69});
        difficulties.put(GameDifficulty.MEDIUM, new int[]{60, 361});
        difficulties.put(GameDifficulty.HARD, new int[]{70, 400});
    }
    public JooperEnemy(int x, int y, GameDifficulty difficulty) {
        super(x, y, resourceLocation, SPEED, 
              difficulties.get(difficulty)[0],
              difficulties.get(difficulty)[1]);
    }
}

