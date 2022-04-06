package org.glizzygladiators.td.entities.enemies;

//import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.glizzygladiators.td.entities.GameDifficulty;

public class MemeEnemy extends Enemy {
    private static final String RESOURCE_LOCATION = "images/meme.png";
    public static final int SPEED = 5;

    private static final Map<GameDifficulty, int[]> DIFFICULTIES;
    static {
        DIFFICULTIES = new HashMap<>();
        DIFFICULTIES.put(GameDifficulty.EASY, new int[]{50, 69});
        DIFFICULTIES.put(GameDifficulty.MEDIUM, new int[]{60, 3621});
        DIFFICULTIES.put(GameDifficulty.HARD, new int[]{70, 400});
    }
    public MemeEnemy(int x, int y, GameDifficulty difficulty) {
        super(x, y, RESOURCE_LOCATION, SPEED, 
              DIFFICULTIES.get(difficulty)[0],
              DIFFICULTIES.get(difficulty)[1]);
    }
}
