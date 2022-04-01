package org.glizzygladiators.td.entities;

import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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

    public int startX;
    public int startY;
    private Path enemyPath;

    public GameMap() {
        generatePath();
    }

    private void generatePath() {
        enemyPath = new Path();
        var stuff = enemyPath.getElements();

        var p1 = new Pair<>(Enemy.SIZE / 2, 145);
        var p2 = new Pair<>(810, 145);
        var p3 = new Pair<>(810, 346);
        var p4 = new Pair<>(160, 346);
        var p5 = new Pair<>(160, 576);
        var p6 = new Pair<>(670, 576);
        var ps = new Pair[]{p2, p3, p4, p5, p6};

        for (int i = 0; i < ps.length; i++) {
            ps[i] = new Pair<>(((Integer) ps[i].getKey()) - Enemy.SIZE / 2, 
                               ((Integer) ps[i].getValue()) - Enemy.SIZE / 2);
        }
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