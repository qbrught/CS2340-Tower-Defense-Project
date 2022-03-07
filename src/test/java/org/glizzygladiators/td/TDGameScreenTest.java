package org.glizzygladiators.td;

import org.glizzygladiators.td.controllers.GameScreen;
import org.glizzygladiators.td.controllers.InitialConfig;
import org.glizzygladiators.td.game.BasicTower;
import org.glizzygladiators.td.game.CannonTower;
import org.glizzygladiators.td.game.GameDifficulty;
import org.junit.jupiter.api.Test;

import javafx.scene.shape.Rectangle;

import org.glizzygladiators.td.game.GameInstance;
import org.glizzygladiators.td.game.Monument;
import org.glizzygladiators.td.game.SpikeTower;
import org.glizzygladiators.td.game.Tower;
import org.glizzygladiators.td.game.TowerEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.TreeMap;

public class TDGameScreenTest {

    String difficultyNull = "Choose difficulty\n";
    String emptyName = "Enter name with non-space characters\n";
    String nameTooLong = "Enter name less than 20 characters\n";
    String message = "Please do the following:\n";

    @Test
    public void testGameInstance() {
        Map<GameDifficulty, int[]> expectedOutputs = new TreeMap<>();
        expectedOutputs.put(GameDifficulty.EASY, new int[]{500, 200});
        expectedOutputs.put(GameDifficulty.MEDIUM, new int[]{375, 150});
        expectedOutputs.put(GameDifficulty.HARD, new int[]{250, 100});
        for (GameDifficulty difficulty : GameDifficulty.values()) {
            GameInstance instance = new GameInstance("Bob", difficulty, false);
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
        Rectangle r1 = new Rectangle(100, 50, 200, 30);
        Rectangle r2 = new Rectangle(120, 70, 300, 40);
        assertTrue(GameScreen.hasCollision(r1, r2));
        r2 = new Rectangle(10, 10, 10, 10);
        assertFalse(GameScreen.hasCollision(r1, r2));
    }

    @Test
    public void testBorderCollision() {
        assertFalse(GameScreen.towerPlacedOffMap(1000 - Tower.SIZE - 1, 350));
        assertTrue(GameScreen.towerPlacedOffMap(2000, 1500));
        assertFalse(GameScreen.towerPlacedOffMap(0, 0));
        assertTrue(GameScreen.towerPlacedOffMap(1500, 750 - Tower.SIZE - 1));
    }

    @Test
    public void testPathCollision() {
        Rectangle r = new Rectangle(0, 0, Tower.SIZE, Tower.SIZE);
        org.glizzygladiators.td.game.Map map = new org.glizzygladiators.td.game.Map();
        assertFalse(map.hasCollisionWithPath(r));
        r = new Rectangle(100, 100, 500, 500);
        assertTrue(map.hasCollisionWithPath(r));
    }

    int getCorrectPrice(TowerEnum tower, GameDifficulty difficulty) {
        switch (tower) {
            case BASIC:
                return new BasicTower(0, 0).getPrice(difficulty);
            case CANNON:
                return new CannonTower(0, 0).getPrice(difficulty);
            case SPIKE:
                return new SpikeTower(0, 0).getPrice(difficulty);
            default:
                return -1;
        }
    }

    @Test
    public void testTowerPrice() {
        for (TowerEnum tower : TowerEnum.values()) {
            for (GameDifficulty difficulty : GameDifficulty.values()) {
                assertEquals(Tower.getPrice(tower, difficulty), 
                             getCorrectPrice(tower, difficulty));
            }
        }
    }

    @Test
    public void testMonumentCollision() {
        Monument monument = new Monument(100, 100, null);
        assertTrue(GameScreen.collidesWithMonument(new Rectangle(120, 120, 10, 10), monument));
        assertFalse(GameScreen.collidesWithMonument(new Rectangle(400, 400, 10, 10), monument));
        assertTrue(GameScreen.collidesWithMonument(new Rectangle(100, 150, 5, 5), monument));
        assertFalse(GameScreen.collidesWithMonument(new Rectangle(100, 500, 1, 1), monument));
    }
}
