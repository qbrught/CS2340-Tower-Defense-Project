package org.glizzygladiators.td.entities.enemies;

//import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.glizzygladiators.td.entities.GameDifficulty;

public class AusEnemy extends Enemy {
    private static final String RESOURCE_LOCATION = "images/aus.png";
    public static final int SPEED = 6;

    private static final Map<GameDifficulty, int[]> DIFFICULTIES;
    static {
        DIFFICULTIES = new HashMap<>();
        DIFFICULTIES.put(GameDifficulty.EASY, new int[]{50, 69});
        DIFFICULTIES.put(GameDifficulty.MEDIUM, new int[]{60, 621});
        DIFFICULTIES.put(GameDifficulty.HARD, new int[]{70, 420});
    }
    public AusEnemy(int x, int y, GameDifficulty difficulty) {
        super(x, y, RESOURCE_LOCATION, SPEED, 
              DIFFICULTIES.get(difficulty)[0],
              DIFFICULTIES.get(difficulty)[1]);
    }
}

