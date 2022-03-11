package org.glizzygladiators.td.entities;

import org.glizzygladiators.td.entities.towers.Tower;

public class Monument extends Rectangle {

    public static final String resourceLocation = "images/monument.jpg";
    private static final int SIZE = 200;

    public Monument(int x, int y) {
        super(x, y, SIZE, SIZE, resourceLocation);
    }

    public boolean collidesWithMonument(Rectangle gameObj) {
        return hasCollision(gameObj);
    }
}
