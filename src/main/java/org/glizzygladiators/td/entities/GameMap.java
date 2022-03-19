package org.glizzygladiators.td.entities;

import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.util.Pair;
import org.glizzygladiators.td.entities.enemies.Enemy;
import org.glizzygladiators.td.entities.towers.Tower;

import java.util.ArrayList;

public class GameMap {

    private SymbolicGameObject[] collisionBound = new SymbolicGameObject[]{
        new SymbolicGameObject(0, 119, 843, 168 - 119,  null),
        new SymbolicGameObject(795, 169, 843 - 795, 334 - 169, null),
        new SymbolicGameObject(134, 319, 794 - 134, 364 - 319, null),
        new SymbolicGameObject(125, 364, 169 - 125, 572 - 364, null),
        new SymbolicGameObject(170, 552, 747 - 170, 602 - 552, null)
    };;

    private Path enemyPath;

//    public int[][] path = {
//        {0, collisionBound[0].y},
//        {795, collisionBound[0].y},
//        {795, collisionBound[2].y},
//        {collisionBound[2].x, collisionBound[2].height},
//        {750, 750},
//        {1000, 1000}
//    };
//    public int[][] offsets = {
//        {1, 0},
//        {0, 1},
//        {-1, 0},
//        {0, 1},
//        {1, 0}
//    };
//    public final int startX;
//    public final int startY;

    public GameMap() {

    }

    private void generatePath() {
        var es = Enemy.SIZE / 2; // constant to subtract by to account for the center of a rect
        enemyPath = new Path();
        var stuff = enemyPath.getElements();

        var p1 = new Pair<>(Enemy.SIZE / 2 - es, 145 - es);
        var p2 = new Pair<>(810 - es, 145 - es);
        var p3 = new Pair<>(810 - es, 346 - es);
        var p4 = new Pair<>(160 - es, 346 - es);
        var p5 = new Pair<>(160 - es, 576 - es);
        var p6 = new Pair<>(747 - es, 576 - es);
        var ps = new Pair[]{p2, p3, p4, p5, p6};

        stuff.add(new MoveTo(p1.getKey(), p1.getValue()));
        for (var p : ps) stuff.add(new LineTo((Integer)p.getKey(), (Integer)p.getValue()));
    }

    /**
     * Whether a tower has a collision with the path
     * @param input the tower to check against
     * @return whether the tower collides with the path
     */
    public boolean hasCollisionWithPath(Tower input) {
        for (var rect: collisionBound) {
            if (input.hasCollision(rect)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the path that enemies move along
     * @return the path that enemies move along
     */
    public Path getEnemyPath() {
        return enemyPath;
    }
}