package org.glizzygladiators.td;

import org.glizzygladiators.td.controllers.InitialConfig;
import org.glizzygladiators.td.entities.SymbolicGameObject;
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

public class TDGameScreenTest {

    private String difficultyNull = "Choose difficulty\n";
    private String emptyName = "Enter name with non-space characters\n";
    private String nameTooLong = "Enter name less than 20 characters\n";
    private String message = "Please do the following:\n";

    @Test
    public void testGameInstance() {
        Map<GameDifficulty, int[]> expectedOutputs = new TreeMap<>();
        expectedOutputs.put(GameDifficulty.EASY, new int[]{500, 200});
        expectedOutputs.put(GameDifficulty.MEDIUM, new int[]{375, 150});
        expectedOutputs.put(GameDifficulty.HARD, new int[]{250, 100});
        for (GameDifficulty difficulty : GameDifficulty.values()) {
            GameInstance instance = new GameInstance("Bob", difficulty);
            int[] expectedOutput = expectedOutputs.get(difficulty);
            assertEquals(expectedOutput[0], instance.getMoney());
            assertEquals(expectedOutput[1], instance.getHealth());
        }
    }

    @Test
    public void testDetermineMessage() {
        assertNull(InitialConfig.determineMessage(GameDifficulty.HARD, "bob"));
        assertEquals(InitialConfig.determineMessage(GameDifficulty.HARD, "    "),
                     message + emptyName);
        assertEquals(InitialConfig.determineMessage(GameDifficulty.EASY, 
                                                    "Bad game design asdioajsdoij"),
                    message + nameTooLong);
    }

    @Test
    public void testCompositeMessages() {
        assertEquals(InitialConfig.determineMessage(null, "    "),
                     message + difficultyNull + emptyName);
        assertEquals(InitialConfig.determineMessage(null, "Joseph chose bad game design"),
                     message + difficultyNull + nameTooLong);
    }

    @Test
    public void testBasicCollision() {
        SymbolicGameObject r1 = new SymbolicGameObject(100, 50, 200, 30, null);
        SymbolicGameObject r2 = new SymbolicGameObject(120, 70, 300, 40, null);
        assertTrue(r1.hasCollision(r2));
        r2 = new SymbolicGameObject(10, 10, 10, 10, null);
        assertFalse(r1.hasCollision(r2));
    }

    @Test
    public void testBorderCollision() {
        CannonTower tower = new CannonTower(1000 - Tower.SIZE - 1, 350);
        assertFalse(tower.isOutOfBounds());
        tower.setX(2000);
        tower.setY(2000);
        assertTrue(tower.isOutOfBounds());
        tower.setX(0);
        tower.setY(0);
        assertFalse(tower.isOutOfBounds());
        tower.setX(1500);
        tower.setY(750 - Tower.SIZE - 1);
        assertTrue(tower.isOutOfBounds());
    }

    @Test
    public void testPathCollision() {
        CannonTower r = new CannonTower(0, 0);
        org.glizzygladiators.td.entities.GameMap map =
            new org.glizzygladiators.td.entities.GameMap();
        assertFalse(map.hasCollisionWithPath(r));
        r = new CannonTower(100, 100);
        assertTrue(map.hasCollisionWithPath(r));
    }

    @Test
    public void testTowerPrice() {
        Tower[] towers = {new BasicTower(0, 0), new CannonTower(0, 0), new SpikeTower(0, 0)};
        for (int i = 0; i < 3; i++) {
            for (int j = i+1; j < 3; j++) {
                for (GameDifficulty difficulty : GameDifficulty.values()) {
                    assertNotEquals(towers[i].getPrice(difficulty), towers[j].getPrice(difficulty));
                }
            }
        }
    }

    @Test
    public void testMonumentCollision() {
        Monument monument = new Monument(100, 100);
        assertTrue(monument.collidesWithMonument(new SymbolicGameObject(120, 120, 10, 10, null)));
        assertFalse(monument.collidesWithMonument(new SymbolicGameObject(400, 400, 10, 10, null)));
        assertTrue(monument.collidesWithMonument(new SymbolicGameObject(100, 150, 5, 5, null)));
        assertFalse(monument.collidesWithMonument(new SymbolicGameObject(100, 500, 1, 1, null)));
    }
}
