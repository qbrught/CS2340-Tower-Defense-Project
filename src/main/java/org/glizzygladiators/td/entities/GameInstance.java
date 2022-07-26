package org.glizzygladiators.td.entities;

import java.util.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

import org.glizzygladiators.td.entities.projectiles.TankProjectile;
import org.glizzygladiators.td.entities.towers.BoostTower;
import org.glizzygladiators.td.entities.towers.Tower;
import org.glizzygladiators.td.entities.enemies.*;
import org.glizzygladiators.td.entities.projectiles.BasicProjectile;
import org.glizzygladiators.td.entities.projectiles.Projectile;
import org.glizzygladiators.td.entities.towers.BasicTower;
import org.glizzygladiators.td.entities.towers.CannonTower;

public class GameInstance {
    private String name;
    private GameDifficulty difficulty;
    private IntegerProperty money;
    private IntegerProperty moneySpent;
    private IntegerProperty health;
    private IntegerProperty score;
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles;
    private Monument monument;
    private GameMap map;

    /**
     * Initializes a GameInstance object
     * @param inputName The name of the player
     * @param inputDifficulty the difficulty of the game
     */
    public GameInstance(String inputName,
                        GameDifficulty inputDifficulty) {
        name = inputName;
        difficulty = inputDifficulty;
        money = new SimpleIntegerProperty(getStartingMoney());
        health = new SimpleIntegerProperty(getStartingHealth());
        score = new SimpleIntegerProperty(0);
        moneySpent = new SimpleIntegerProperty(0);
        towers = new ArrayList<Tower>();
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        monument = new Monument(700, 475);
        map = new GameMap();
    }

    public void addEnemy(Enemy enemy) {
        synchronized (enemies) {
            enemies.add(enemy);
            MoveTo objStart = (MoveTo) map.getEnemyPath().getElements().get(0);
            enemy.setX((int) objStart.getX() - Enemy.SIZE / 2);
            enemy.setY((int) objStart.getY() - Enemy.SIZE / 2);
        }
    }
    
    public void addEnemyUnaltered(Enemy enemy) {
        synchronized (enemies) {
            enemies.add(enemy);
        }
    }

    public void removeEnemy(Enemy enemy) {
        synchronized (enemies) {
            enemies.remove(enemy);
        }
    }

    public void moveEnemies() {
        synchronized (enemies) {
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                if (enemy.getIndex() == map.getEnemyPath().getElements().size()) {
                    enemy.fireCallback(true);
                    continue;
                }
                LineTo lineTo = (LineTo) map.getEnemyPath()
                    .getElements().get(enemy.getIndex());
                int xDelta = ((int) lineTo.getX()) - enemy.getX();
                int yDelta = ((int) lineTo.getY()) - enemy.getY(); 
                int mag = (int) Math.sqrt(Math.pow(xDelta, 2) + Math.pow(yDelta, 2));
                if (mag > enemy.getSpeed()) {
                    mag = enemy.getSpeed();
                }
                enemy.translate(xDelta, yDelta, mag);
                if (enemy.getX() == ((int) lineTo.getX())
                        && enemy.getY() == ((int) lineTo.getY())) {
                    enemies.get(i).setIndex(enemies.get(i).getIndex() + 1);
                }
            }
        }
    }

    public ArrayList<Projectile> fireProjectiles(int cycleCount) {
        ArrayList<Projectile> newProjectiles = new ArrayList<>();
        synchronized (towers) {
            for (Tower tower : towers) {
                if (tower instanceof BoostTower) {
                    continue; // May change later. Temporary solution.
                }
                if (cycleCount - tower.getLastFired() >= tower.getAttackSpeed()) {
                    Enemy enemy = null;
                    synchronized (enemies) {
                        enemy = tower.getClosestEnemy(enemies);
                    }
                    if (enemy != null) {
                        tower.fire(cycleCount);
                        int xDelt = (enemy.getX() + Enemy.SIZE) - tower.getCenterX();
                        int yDelt = (enemy.getY() + Enemy.SIZE) - tower.getCenterY();
                        Projectile projectile = null;
                        if (tower instanceof BasicTower) {
                            projectile = new BasicProjectile(tower.getCenterX(), tower.getCenterY(),
                                    xDelt, yDelt, tower.getAttackDamage());
                        } else if (tower instanceof CannonTower) {
                            projectile = new TankProjectile(tower.getCenterX(), tower.getCenterY(),
                                    xDelt, yDelt, tower.getAttackDamage());
                        }
                        newProjectiles.add(projectile);
                    }
                }
            }
        }
        return newProjectiles;
    }

    public void addProjectile(Projectile p) {
        synchronized (projectiles) {
            this.projectiles.add(p);
        }
    }

    public void moveProjectiles() {
        synchronized (projectiles) {
            for (int i = 0, increment = 1; i < projectiles.size(); i += increment, increment = 1) {
                Projectile p = projectiles.get(i);
                p.move();
                boolean remove = false;
                if (p.isOutOfBounds()) {
                    p.fireDestroyedEvent(null);
                    remove = true;
                }
                Enemy enemy = p.collides(enemies);
                if (enemy != null) {
                    p.fireDestroyedEvent(enemy);
                    remove = true;
                }
                if (remove) {
                    increment = 0;
                    projectiles.remove(i);
                }
            }
        }
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
     * Returns the property containing the player's money
     * @return the property containing the player's money
     */
    public IntegerProperty getMoneyProperty() {
        return money;
    }

    /**
     * Returns the monument's health
     * @return the monument's health
     */
    public int getHealth() {
        return health.get();
    }

    /**
     * Returns the score
     * @return game score
     */
    public int getScore() { return score.get();}

    /**
     * Returns the money spent
     * @return moeny spent
     */
    public int getMoneySpent() { return moneySpent.get();}

    /**
     * Sets the monument's health
     * @param newHealth the monument's health
     */
    public void setHealth(int newHealth) {
        if (newHealth >= 0) {
            this.health.set(newHealth);
        } else {
            this.health.set(0);
        }
    }
    /**
     * Sets the game score
     * @param newScore the new score
     */
    public void setScore(int newScore) {
        if (newScore >= 0) {
            this.score.set(newScore);
        }
    }
    /**
     * Sets the money spent
     * @param newSpent the new score
     */
    public void setMoneySpent(int newSpent) {
        if (newSpent >= 0) {
            this.moneySpent.set(newSpent);
        }
    }
    /**
     * Returns the IntegerProperty containing the monument's health
     * @return the IntegerProperty containing the monument's health
     */
    public IntegerProperty getHealthProperty() {
        return health;
    }
    /**
     * Returns the Integer property containing the score
     * @return the Integerproprty contains the game score
     */
    public IntegerProperty getScoreProperty() { return score; }

    /**
     * Returns the Integer property containing the score
     * @return the Integerproprty contains the game score
     */
    public IntegerProperty getMoneySpentProperty() { return moneySpent; }

    /**
     * Returns an Observable list of towers
     * @return an Observable list of towers
     */
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public boolean isInvalidTowerLocation(Tower tower) {
        return tower.isOutOfBounds()
               || map.hasCollisionWithPath(tower)
               || collidesWithTower(tower) 
               || monument.collidesWithMonument(tower);
    }

    private boolean collidesWithTower(Tower gameObj) {
        for (Tower t : towers) {
            if (t.hasCollision(gameObj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns false if the monument/health is gone
     * @return GameOver
     */
    public boolean gameOver() {
        if (health.get() > 0) {
            return false;
        } else {
            towers = null;
            return true;
        }
    }

    /**
     * Returns the monument associated with this GameInstance
     * @return the monument associated with this GameInstance
     */
    public Monument getMonument() {
        return monument;
    }

    /**
     * Returns the map that this GameInstance uses
     * @return the map that this GameInstance uses
     */
    public GameMap getMap() {
        return map;
    }

    public int getProjectilesSize() {
        return projectiles.size();
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
