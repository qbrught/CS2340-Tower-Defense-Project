package org.glizzygladiators.td.game;

import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Map {

    private Shape[] collisionBound;
    private Path path;

    public Map() {
        // TODO yeah
    }


    public Path getPath() {
        return path;
    }

    public boolean hasCollision(Shape shape) {
        return false;
    }
}
