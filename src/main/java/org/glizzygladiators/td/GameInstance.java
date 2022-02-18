package org.glizzygladiators.td;

public class GameInstance {
    private String name;
    private String difficulty;

    /**
     * Default constructor of the game instance.
     */
    public GameInstance() {

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
}
