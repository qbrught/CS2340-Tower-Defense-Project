package org.glizzygladiators.td.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameInstance {

    private String name;
    private GameDifficulty difficulty;
    private IntegerProperty money;
    private IntegerProperty health;
    private ObservableList<Tower> towers;
    private ObservableList<Enemy> enemies;
    private Monument monument;

    /**
     * Initializes a GameInstance object
     * @param inputName The name of the player
     * @param inputDifficulty the difficulty of the game
     */
    public GameInstance(String inputName, String inputDifficulty) {
        name = inputName;
        switch (inputDifficulty) {
        case "easy":
            difficulty = GameDifficulty.EASY;
            break;
        case "medium":
            difficulty = GameDifficulty.MEDIUM;
            break;
        case "hard":
            difficulty = GameDifficulty.HARD;
            break;
        default:
            difficulty = GameDifficulty.EASY;
        }
        money = new SimpleIntegerProperty(getStartingMoney());
        health = new SimpleIntegerProperty(getStartingHealth());
        towers = FXCollections.emptyObservableList();
        enemies = FXCollections.emptyObservableList();
        monument = new Monument(700, 475, "images/monument.jpg");
            // The constants for the monument object only apply to this map. This definition should
            // change if different maps and different monuments are added to the game
    }

    /**
     * Gets the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the difficulty that the player selected.
     * @return the difficulty the player selected.
     */
    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Returns IntegerProperty object containing the player's money
     * @return IntegerProperty object containing the player's money
     */
    public IntegerProperty getMoneyProperty() {
        return money;
    }

    /**
     * Returns the player's money
     * @return the player's money
     */
    public int getMoney() {
        return money.get();
    }

    /**
     * Sets the player's money
     * @param newMoney the player's money
     */
    public void setMoney(int newMoney) {
        money.set(newMoney);
    }

    /**
     * Returns IntegerProperty object containing the monument's health
     * @return IntegerProperty object containing the monument's health
     */
    public IntegerProperty getHealthProperty() {
        return health;
    }

    /**
     * Returns the monument's health
     * @return the monument's health
     */
    public int getHealth() {
        return health.get();
    }

    /**
     * Sets the monument's health
     * @param newHealth the monument's health
     */
    public void setHealth(int newHealth) {
        health.set(newHealth);
    }

    /**
     * Returns an Observable list of towers
     * @return an Observable list of towers
     */
    public ObservableList<Tower> getTowers() {
        return towers;
    }

    /**
     * Returns an Observable list of enemies
     * @return an Observable list of enemies
     */
    public ObservableList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Returns the monument associated with this GameInstance
     * @return the monument associated with this GameInstance
     */
    public Monument getMonument() {
        return monument;
    }

    /**
     * Returns the amount of money the player starts with
     * @return the amount of money the player starts with
     */
    private int getStartingMoney() {
        switch (difficulty) {
        case EASY: return 500;
        case MEDIUM: return 375;
        case HARD: return 250;
        default: return 0; // This case should never be hit
        }
    }

    /**
     * Returns the amount of health the monument starts with
     * @return the amount of health the monument starts with
     */
    private int getStartingHealth() {
        switch (difficulty) {
        case EASY: return 200;
        case MEDIUM: return 150;
        case HARD: return 100;
        default: return 0; // This case should never be hit
        }
    }
}
