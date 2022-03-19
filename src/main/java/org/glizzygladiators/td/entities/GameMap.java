package org.glizzygladiators.td.entities;

import org.glizzygladiators.td.entities.towers.Tower;

public class GameMap {

    private Rectangle[] collisionBound = new Rectangle[]{
        new Rectangle(0, 119, 843, 168 - 119,  null),
        new Rectangle(795, 169, 843 - 795, 334 - 169, null),
        new Rectangle(134, 319, 794 - 134, 364 - 319, null),
        new Rectangle(125, 364, 169 - 125, 572 - 364, null),
        new Rectangle(170, 552, 747 - 170, 602 - 552, null)
    };;
    public int[][] path = {
        {0, collisionBound[0].y},
        {795, collisionBound[0].y},
        {795, collisionBound[2].y},
        {collisionBound[2].x, collisionBound[2].height},
        {750, 750},
        {1000, 1000}
    };
    public int[][] offsets = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, 1},
        {1, 0}
    };
    public final int startX;
    public final int startY;

    public GameMap() {
        startX = path[0][0];
        startY = path[0][1];
    }

    public boolean hasCollisionWithPath(Tower input) {
        for (var rect: collisionBound) {
            if (input.hasCollision(rect)) {
                return true;
            }
        }
        return false;
    }
}