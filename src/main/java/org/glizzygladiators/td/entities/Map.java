package org.glizzygladiators.td.entities;

import org.glizzygladiators.td.entities.towers.Tower;

public class Map {

    private Rectangle[] collisionBound;

    public Map() {
        collisionBound = new Rectangle[]{
            new Rectangle(0, 119, 843, 168 - 119,  null),
            new Rectangle(795, 169, 843 - 795, 334 - 169, null),
            new Rectangle(134, 319, 794 - 134, 364 - 319, null),
            new Rectangle(125, 364, 169 - 125, 572 - 364, null),
            new Rectangle(170, 552, 747 - 170, 602 - 552, null)
        };

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