package org.glizzygladiators.td;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Pair;
import org.glizzygladiators.td.entities.SymbolicGameObject;
import org.glizzygladiators.td.entities.enemies.*;
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

}
