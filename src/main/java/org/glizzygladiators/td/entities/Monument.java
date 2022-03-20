package org.glizzygladiators.td.entities;

import javafx.scene.shape.Rectangle;
import org.glizzygladiators.td.entities.towers.Tower;

public class Monument extends SymbolicGameObject {

    public static final String imgPath = "images/monument.jpg";
    private static final int SIZE = 200;

    public Monument(int x, int y) {
        super(x, y, SIZE, SIZE, imgPath);
    }

    public boolean collidesWithMonument(SymbolicGameObject gameObj) {
        return hasCollision(gameObj);
    }
}