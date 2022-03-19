package org.glizzygladiators.td.entities;

import javafx.scene.shape.Rectangle;
import org.glizzygladiators.td.entities.towers.Tower;

public class Map {

    private SymbolicGameObject[] collisionBound;

    public Map() {
        collisionBound = new SymbolicGameObject[]{
            new SymbolicGameObject(0, 119, 843, 168 - 119),
            new SymbolicGameObject(795, 169, 843 - 795, 334 - 169),
            new SymbolicGameObject(134, 319, 794 - 134, 364 - 319),
            new SymbolicGameObject(125, 364, 169 - 125, 572 - 364),
            new SymbolicGameObject(170, 552, 747 - 170, 602 - 552)
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