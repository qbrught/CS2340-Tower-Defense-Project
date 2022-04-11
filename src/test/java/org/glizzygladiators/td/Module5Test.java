package org.glizzygladiators.td;

import org.glizzygladiators.td.entities.*;
import org.glizzygladiators.td.entities.health.HealthBar;
import org.glizzygladiators.td.entities.health.HealthText;
import org.glizzygladiators.td.entities.projectiles.BasicProjectile;
import org.glizzygladiators.td.entities.projectiles.Projectile;
import org.glizzygladiators.td.entities.enemies.*;
import org.glizzygladiators.td.entities.towers.BasicTower;
import org.glizzygladiators.td.entities.towers.BoostTower;
import org.glizzygladiators.td.entities.towers.CannonTower;
import org.glizzygladiators.td.entities.towers.Tower;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    public void boostTowerSuccess() {
        BoostTower boost = new BoostTower(400, 400);
        ArrayList<Tower> towers = new ArrayList<>();
        towers.add(new BasicTower(300, 400));
        towers.add(new CannonTower(400, 300));
        boost.boostOthers(towers);
        assertEquals(40, towers.get(0).getAttackDamage());
        assertEquals(30, towers.get(0).getAttackSpeed());
        assertEquals(60, towers.get(1).getAttackDamage());
        assertEquals(70, towers.get(1).getAttackSpeed());
    }

    @Test
    public void boostTowerFail() {
        BoostTower boost = new BoostTower(400, 400);
        ArrayList<Tower> towers = new ArrayList<>();
        towers.add(new BasicTower(299, 400));
        towers.add(new CannonTower(399, 300));
        boost.boostOthers(towers);
        assertEquals(30, towers.get(0).getAttackDamage());
        assertEquals(40, towers.get(0).getAttackSpeed());
        assertEquals(50, towers.get(1).getAttackDamage());
        assertEquals(80, towers.get(1).getAttackSpeed());
    }

    @Test
    public void MoveableGameObjectTranslationTest() {
        var mgo = new MoveableGameObject(1, 1, "");

        int ox = mgo.getX();
        int oy = mgo.getY();
        int nx = 100;
        int ny = 0;

        mgo.translate(nx, ny, (int) Math.pow(nx*nx+ny*ny, 0.5));
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

    @Test
    public void TestProjectileDestroyedWhenOutOfBounds() {
        Projectile p = new BasicProjectile(0, 0, 1, 1, 20);
        int[] invoked = {0};
        p.addListener(new DestroyedCallback() {
            @Override
            public void onDestroyed(Object obj) {
                assertTrue(obj == null);
                invoked[0]++;
            }
        });
        GameInstance instance = new GameInstance("bob", GameDifficulty.EASY);
        instance.addProjectile(p);
        assertEquals(instance.getProjectilesSize(), 1);
        for (int i = 0; i < 1000; i++) instance.moveProjectiles();
        assertEquals(instance.getProjectilesSize(), 0);
        assertEquals(invoked[0], 1);
    }

    @Test
    public void TestProjectileCollisionWithEnemy() {
        Projectile p = new BasicProjectile(0, 0, 1, 1, 10);
        Enemy e = new BasicEnemy(50, 50, GameDifficulty.EASY);

        int[] invoked = {0};
        p.addListener(new DestroyedCallback() {
            @Override
            public void onDestroyed(Object obj) {
                assertEquals(e, (Enemy) obj);
                invoked[0]++;
            }
        });
        GameInstance instance = new GameInstance("bob", GameDifficulty.EASY);
        instance.addEnemyUnaltered(e);
        instance.addProjectile(p);
        assertEquals(instance.getProjectilesSize(), 1);
        for (int i = 0; i < 50; i++) instance.moveProjectiles();
        assertEquals(invoked[0], 1);
        assertEquals(instance.getProjectilesSize(), 0);
    }
}
