package org.glizzygladiators.td.game;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import org.glizzygladiators.td.controllers.GameScreen;

public class Map {

    private Rectangle[] collisionBound;
    private Path path;

    public Map() {
        collisionBound = new Rectangle[]{
            new Rectangle(0, 119, 843, 168 - 119),
            new Rectangle(795, 169, 843 - 795, 334 - 169),
            new Rectangle(134, 319, 794 - 134, 364 - 319),
            new Rectangle(125, 364, 169 - 125, 572 - 364),
            new Rectangle(170, 552, 747 - 170, 602 - 552)
        };

    }

    public Path getPath() {
        return path;
    }

    public boolean hasCollisionWithPath(Rectangle input) {
        for (var rect: collisionBound) {
            if (hasCollision(input, rect)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCollision(Rectangle r1, Rectangle r2) {
        return GameScreen.hasCollision(r1, r2);
    }
}
