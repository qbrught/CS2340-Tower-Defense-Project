package org.glizzygladiators.td.game;

public class GameConfig {
    private String name;
    private String difficulty;

    /**
     * Initializes a GameInstance object
     * @param inputName The name of the player
     * @param inputDifficulty the difficulty of the game
     */
    public GameConfig(String inputName, String inputDifficulty) {
        name = inputName;
        difficulty = inputDifficulty;
    }

    /**
     * Gets the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     * @param s the new name of the player.
     */
    public void setName(String s) {
        name = s;
    }

    /**
     * Gets the difficulty that the player selected.
     * @return the difficulty the player selected.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty the player selects.
     * @param s the difficulty that the player selects.
     */
    public void setDifficulty(String s) {
        difficulty = s;
    }

    /**
     * Returns the amount of money the player starts with
     * @return the amount of money the player starts with
     */
    public int getStartingMoney() {
        switch (difficulty) {
        case "easy": return 500;
        case "medium": return 375;
        case "hard": return 250;
        default: return 0; // This case should never be hit
        }
    }

    /**
     * Returns the amount of health the monument starts with
     * @return the amount of health the monument starts with
     */
    public int getStartingHealth() {
        switch (difficulty) {
        case "easy": return 200;
        case "medium": return 150;
        case "hard": return 100;
        default: return 0; // This case should never be hit
        }
    }
}
