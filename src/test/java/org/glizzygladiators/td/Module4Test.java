package org.glizzygladiators.td;

import javafx.scene.shape.Rectangle;
import org.glizzygladiators.td.controllers.InitialConfig;
import org.glizzygladiators.td.entities.SymbolicGameObject;
import org.glizzygladiators.td.entities.enemies.Enemy;
import org.glizzygladiators.td.entities.towers.BasicTower;
import org.glizzygladiators.td.entities.towers.CannonTower;
import org.glizzygladiators.td.entities.towers.SpikeTower;
import org.glizzygladiators.td.entities.GameDifficulty;
import org.junit.jupiter.api.Test;

import org.glizzygladiators.td.entities.GameInstance;
import org.glizzygladiators.td.entities.Monument;
import org.glizzygladiators.td.entities.towers.Tower;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.TreeMap;

public class Module4Test {

    @Test
    public void enemyCollisionTest() {
        Enemy enemy = new Enemy(0, 0, 100, null, 1);
        SymbolicGameObject sgo = new SymbolicGameObject(100, 100, 40, 40);
        assertFalse(enemy.hasCollision(sgo));
        enemy.setX(100);
        enemy.setY(100);
        assertTrue(enemy.hasCollision(sgo));
    }

    @Test
    public void enemyDisappearAtMonumentTest() {
        Monument monument = new Monument(700, 475);
        Enemy enemy = new Enemy(0, 0, 100, null, 1);
        for (int i = 0; i < 7; i++) {
            enemy.setX(enemy.getX() + 100);
            enemy.setY(enemy.getY() + 67);
            if (i < 6) {
                assertFalse(enemy.hasCollision(monument));
            } else {
                assertTrue(enemy.hasCollision(monument));
            }
            if (enemy.hasCollision(monument)) {
                enemy.setEnemyHealth(0);
            }
        }
        assertEquals(0, enemy.getEnemyHealth());
    }
}
