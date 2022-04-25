package org.glizzygladiators.td;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Pair;
import org.glizzygladiators.td.entities.SymbolicGameObject;
import org.glizzygladiators.td.entities.enemies.*;
import org.glizzygladiators.td.entities.projectiles.Projectile;
import org.glizzygladiators.td.entities.DestroyedCallback;
import org.glizzygladiators.td.entities.GameDifficulty;
import org.glizzygladiators.td.entities.towers.BasicTower;
import org.glizzygladiators.td.entities.towers.Tower;
import org.junit.jupiter.api.Test;
import org.glizzygladiators.td.entities.GameInstance;
import org.glizzygladiators.td.entities.GameMap;
import org.glizzygladiators.td.entities.Monument;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Module6Test {
    @Test
    public void testUpgradeCostChange() {
        Tower t = new BasicTower(0, 0);
        assertEquals(75, t.getUpgradeCost());
        t.setAttackSpeedLevel(3);
        t.setAttackDamageLevel(3);
        t.setRangeLevel(3);
        t.setUpgradeCost(t.calculateUpgradeCost());
        assertEquals(225, t.getUpgradeCost());
    }

    @Test
    public void testUpgrade() {
        Tower tower = new BasicTower(0, 0);
        assertEquals(1, tower.getAttackSpeedLevel());
        assertEquals(40, tower.getAttackSpeed());
        tower.upgrade("Speed");
        assertEquals(2, tower.getAttackSpeedLevel());
        assertEquals(30, tower.getAttackSpeed());
    }

    @Test
    public void testEnemyDamageMonument() {
        GameInstance instance = new GameInstance("bob", GameDifficulty.EASY);
        Enemy e = new BasicEnemy(0, 0, GameDifficulty.EASY);
        e.setCallback(new DestroyedCallback() {
            @Override
            public void onDestroyed(Object obj) {
                instance.setHealth(instance.getHealth() - e.getDamage());
                instance.removeEnemy(e);
            }
        });
        instance.addEnemy(e);
        for (int i = 0; i < 5000; i++) instance.moveEnemies();
        assertEquals(190, instance.getHealth());
    }

    @Test
    public void testProjectileCreation() {
        GameInstance instance = new GameInstance("bob", GameDifficulty.EASY);
        Enemy e = new BasicEnemy(0, 0, GameDifficulty.EASY);
        instance.addEnemy(e);
        Tower t = new BasicTower(e.getX(), e.getY());
        instance.getTowers().add(t);
        assertEquals(1, instance.fireProjectiles(1000).size());
    }

    @Test
    public void testFinalBossHealthDifferentForDifferentGameDifficulties() {
        var e1 = new FinalBoss(0, 0, GameDifficulty.EASY);
        var e2 = new FinalBoss(0, 0, GameDifficulty.MEDIUM);
        var e3 = new FinalBoss(0, 0, GameDifficulty.HARD);
        assertNotEquals(e1.getEnemyHealth(), e2.getEnemyHealth());
        assertNotEquals(e3.getEnemyHealth(), e2.getEnemyHealth());
    }

    @Test
    public void testFinalBossLargerThanNormalEnemy() {
        Enemy e = new BasicEnemy(0, 0, GameDifficulty.EASY);
        FinalBoss f = new FinalBoss(0, 0, GameDifficulty.EASY);
        assertTrue(e.SIZE < f.BOSS_SIZE);
    }

}
