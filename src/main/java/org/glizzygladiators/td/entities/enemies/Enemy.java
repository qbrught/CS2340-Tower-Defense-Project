package org.glizzygladiators.td.entities.enemies;

import org.glizzygladiators.td.entities.Rectangle;
import org.glizzygladiators.td.entities.GameMap;

public class Enemy extends Rectangle {
    public static final int SIZE = 40;

    private int enemyId;
    private int enemyHealth;
    private final int speed;
    private final GameMap map = new GameMap();
    int index = 1;
    int axis = 0;
    public Enemy(int x, int y, int enemyHealth, String resourceLocation, int speed) {
        super(x, y, SIZE, SIZE, resourceLocation);
        this.speed = speed;
        this.enemyHealth = enemyHealth;
    }

    public void setEnemyId(int id) {
        this.enemyId = id;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public int getEnemyId() {
        return enemyId;
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * Stimulates one step in some direction
     */
    public void move() {
        x = x + map.offsets[index - 1][0] * speed;
        y = y + map.offsets[index - 1][1] * speed;
        int[] coords = {x, y};
        if (axis < 0 || axis >= 2) System.out.println("Invalid value of axis! " + axis);
        boolean negativeBoundExceeded = map.offsets[index][axis] < 0 
            && coords[axis] <= map.path[index][axis];
        boolean positiveBoundExceeded = map.offsets[index][axis] > 0 
            && coords[axis] >= map.path[index][axis];
        if (negativeBoundExceeded || positiveBoundExceeded) {
            System.out.println(map.path[index][axis]);
            System.out.println(coords[0] + " " + coords[1]);
            System.out.println("Axis: " + axis);
            System.out.println("Index: " + index);
            axis = 1 - axis;
            index++;
        }
    }
}
