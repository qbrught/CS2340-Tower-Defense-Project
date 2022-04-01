package org.glizzygladiators.td;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Pair;

import org.glizzygladiators.td.entities.SymbolicGameObject;
import org.glizzygladiators.td.entities.enemies.*;
import org.glizzygladiators.td.entities.GameDifficulty;
import org.junit.jupiter.api.Test;

import org.glizzygladiators.td.entities.GameInstance;
import org.glizzygladiators.td.entities.GameMap;
import org.glizzygladiators.td.entities.Monument;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Module4Test {

    @Test
    public void enemyCollisionTest() {
        Enemy enemy = new BasicEnemy(0, 0, GameDifficulty.EASY);
        SymbolicGameObject sgo = new SymbolicGameObject(100, 100, 40, 40);
        assertFalse(enemy.hasCollision(sgo));
        enemy.setX(100);
        enemy.setY(100);
        assertTrue(enemy.hasCollision(sgo));
    }

    @Test
    public void enemyDisappearAtMonumentTest() {
        Monument monument = new Monument(700, 475);
        Enemy enemy = new BasicEnemy(0, 0, GameDifficulty.MEDIUM);
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

    @Test
    public void testEnemyDifferentSpeeds() {
        Enemy enemy = new BasicEnemy(0, 0, GameDifficulty.EASY);
        assertEquals(enemy.getSpeed(), BasicEnemy.SPEED);
        enemy = new MemeEnemy(0, 0, GameDifficulty.MEDIUM);
        assertEquals(enemy.getSpeed(), MemeEnemy.SPEED);
        enemy = new AusEnemy(0, 0, GameDifficulty.HARD);
        assertEquals(enemy.getSpeed(), AusEnemy.SPEED);
    }

    @Test
    public void testEnemyPath() {
        GameMap map = new GameMap();
        Path expectedPath = new Path();
        var stuff = expectedPath.getElements();

        var p1 = new Pair<>(Enemy.SIZE / 2, 145);
        var p2 = new Pair<>(810, 145);
        var p3 = new Pair<>(810, 346);
        var p4 = new Pair<>(160, 346);
        var p5 = new Pair<>(160, 576);
        var p6 = new Pair<>(670, 576);
        Pair[] ps = new Pair[]{p2, p3, p4, p5, p6};

        stuff.add(new MoveTo(p1.getKey(), p1.getValue()));
        for (var p : ps) {
            stuff.add(new LineTo((Integer) p.getKey(), (Integer) p.getValue()));
        }
    
        var enemyPath = map.getEnemyPath().getElements();
        for (int i = 0; i < stuff.size(); i++) {
            if (i == 0) {
                MoveTo moveTo = (MoveTo) enemyPath.get(0);
                MoveTo expectedMoveTo = (MoveTo) stuff.get(0);
                assertEquals(moveTo.getX(), expectedMoveTo.getX());
                assertEquals(moveTo.getY(), expectedMoveTo.getY());
            } else {
                LineTo lineTo = (LineTo) enemyPath.get(i);
                LineTo expectedLineTo = (LineTo) stuff.get(i);
                assertEquals(lineTo.getX(), expectedLineTo.getX());
                assertEquals(lineTo.getY(), expectedLineTo.getY());
            }
        }
    }
    @Test
    public void testGameOverHealth() {
        GameInstance easy = new GameInstance("test",  GameDifficulty.EASY);
        GameInstance med = new GameInstance("test",  GameDifficulty.MEDIUM);
        GameInstance hard = new GameInstance("test",  GameDifficulty.HARD);
        GameInstance[] games = new GameInstance[]{easy, med, hard};
        for (GameInstance game: games) {
            game.setHealth(0);
            assertEquals(game.gameOver(),  true);
            for (int i = game.getHealth(); i > 0; i--) {
                game.setHealth(i);
                assertEquals(game.gameOver(), false);
            }
        }
    }


    @Test
    public void testDifferentEnemiesDoDifferentDamage() {
        Enemy ae = new AusEnemy(0, 0, GameDifficulty.EASY);
        Enemy be = new BasicEnemy(0, 0, GameDifficulty.EASY);
        Enemy je1 = new JooperEnemy(0, 0, GameDifficulty.EASY);
        Enemy je2 = new JosephEnemy(0, 0, GameDifficulty.EASY);
        Enemy me = new MemeEnemy(0, 0, GameDifficulty.EASY);
        boolean allEqual = ae.getDamage() == be.getDamage()
                && ae.getDamage() == je1.getDamage()
                && ae.getDamage() == je2.getDamage()
                && ae.getDamage() == me.getDamage()
                && be.getDamage() == je1.getDamage()
                && be.getDamage() == je2.getDamage()
                && be.getDamage() == me.getDamage()
                && je1.getDamage() == je2.getDamage()
                && je1.getDamage() == me.getDamage()
                && je2.getDamage() == me.getDamage();
        assertFalse(allEqual);
    }

    @Test
    public void testEnemiesDoDifferentDamageOnDifferentDifficulty() {
        Enemy aee = new AusEnemy(0, 0, GameDifficulty.EASY);
        Enemy aem = new AusEnemy(0, 0, GameDifficulty.MEDIUM);
        Enemy aeh = new AusEnemy(0, 0, GameDifficulty.HARD);
        assertNotEquals(aee.getDamage(), aem.getDamage());
        assertNotEquals(aee.getDamage(), aeh.getDamage());
        assertNotEquals(aem.getDamage(), aeh.getDamage());
    }

    @Test
    public void testEnemiesDifferentHealth() {
        Enemy easy = new MemeEnemy(0, 0, GameDifficulty.EASY);
        Enemy med = new MemeEnemy(0, 0, GameDifficulty.MEDIUM);
        Enemy hard = new MemeEnemy(0, 0, GameDifficulty.HARD);
        assertNotEquals(easy.getEnemyHealth(), hard.getEnemyHealth());
        assertNotEquals(easy.getEnemyHealth(), med.getEnemyHealth());
        assertNotEquals(med.getEnemyHealth(), hard.getEnemyHealth());
    }
}
