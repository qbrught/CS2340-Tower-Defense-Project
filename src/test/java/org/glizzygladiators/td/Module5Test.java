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
}
