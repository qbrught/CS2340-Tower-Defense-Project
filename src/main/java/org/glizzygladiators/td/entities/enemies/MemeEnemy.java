package org.glizzygladiators.td.entities.enemies;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.glizzygladiators.td.entities.GameDifficulty;

public class MemeEnemy extends Enemy {
    private static final String resourceLocation = "images/meme.png";
    public static final int SPEED = 5;

    private static final Map<GameDifficulty, int[]> difficulties;
    static {
        difficulties = new HashMap<>();
        difficulties.put(GameDifficulty.EASY, new int[]{50, 69});
        difficulties.put(GameDifficulty.MEDIUM, new int[]{60, 3621});
        difficulties.put(GameDifficulty.HARD, new int[]{70, 400});
    }
    public MemeEnemy(int x, int y, GameDifficulty difficulty) {
        super(x, y, resourceLocation, SPEED, 
              difficulties.get(difficulty)[0],
              difficulties.get(difficulty)[1]);
    }
}
