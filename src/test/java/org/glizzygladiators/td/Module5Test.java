package org.glizzygladiators.td;

import org.glizzygladiators.td.entities.*;
import org.glizzygladiators.td.entities.health.HealthBar;
import org.glizzygladiators.td.entities.health.HealthText;
import org.glizzygladiators.td.entities.enemies.*;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class Module5Test {

    @Test
   public void HealthBarFollowsEnemyTest() {
        Enemy enemy = new BasicEnemy(0, 0, GameDifficulty.EASY);
        HealthBar healthBar = new HealthBar(enemy.getX(),enemy.getY(), enemy.getEnemyHealth(), 10);
        healthBar.update(enemy);
        Random rand = new Random();
        for (int i = 0; i < 8; i++) {
            int w = rand.nextInt(1000);
            int h = rand.nextInt(750);
            enemy.setX(w);
            enemy.setY(h);
            assertTrue(healthBar.getX() == enemy.getX() && healthBar.getY() == enemy.getY());
        }
    }

    @Test
    public void HealthTextChangesValue() {
        Enemy enemy = new BasicEnemy(0,0, GameDifficulty.EASY);
        HealthText healthText = new HealthText(enemy.getX(), enemy.getY(), String.valueOf(enemy.getEnemyHealth()));
        healthText.update(enemy);
        for (int i = 0; i < 50; i++) {
            enemy.setEnemyHealth(i);
            assertTrue(healthText.getText().equals(String.valueOf(enemy.getEnemyHealth())));
        }
    }

    @Test
    public void MoveableGameObjectTranslationTest() {
        var mgo = new MoveableGameObject(1, 1, "");

        int ox = mgo.getX();
        int oy = mgo.getY();
        int nx = 100;
        int ny = 100;

        mgo.translate(nx, ny, Math.pow(nx*nx+ny*ny, 0.5));
        assertEquals(ox + nx, mgo.getX());
        assertEquals(oy + ny, mgo.getY());
    }

    @Test
    public void MoveableGameObjectTranslationSpeedTest() {
        int nx = 100;
        int ny = 0;
        var mgo = new MoveableGameObject(1, 1, "");

        mgo.setX(0);
        mgo.setY(0);
        mgo.translate(nx, ny, 1);
        assertEquals(1, mgo.getX());
        assertEquals(0, mgo.getY());

        mgo.setX(0);
        mgo.setY(0);
        mgo.translate(nx, ny, 17);
        assertEquals(17, mgo.getX());
        assertEquals(0, mgo.getY());

        mgo.setX(0);
        mgo.setY(0);
        mgo.translate(nx, ny, 100);
        assertEquals(100, mgo.getX());
        assertEquals(0, mgo.getY());
    }
}
