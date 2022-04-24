package org.glizzygladiators.td.entities.enemies;

import org.glizzygladiators.td.entities.GameDifficulty;

import java.util.HashMap;
import java.util.Map;

public class FinalBoss extends Enemy{
    private static final String RESOURCE_LOCATION = "images/boss.png";
    private static final int BOSS_SIZE = 80;
    public static final int SPEED = 1;

    private static final Map<GameDifficulty, int[]> DIFFICULTIES;
    static {
        DIFFICULTIES = new HashMap<>();
        DIFFICULTIES.put(GameDifficulty.EASY, new int[]{1500, 1500});
        DIFFICULTIES.put(GameDifficulty.MEDIUM, new int[]{2500, 2500});
        DIFFICULTIES.put(GameDifficulty.HARD, new int[]{3500, 3500});
    }
    public FinalBoss(int x, int y, GameDifficulty difficulty) {
        super(x, y, BOSS_SIZE, RESOURCE_LOCATION, SPEED,
                DIFFICULTIES.get(difficulty)[0],
                DIFFICULTIES.get(difficulty)[1]);
    }
}

