package org.glizzygladiators.td.visualizers;

import org.glizzygladiators.td.entities.GameInstance;
//import org.glizzygladiators.td.entities.enemies.Enemy;
import org.glizzygladiators.td.entities.towers.Tower;
import org.glizzygladiators.td.visualizers.ui.MonumentUI;
import org.glizzygladiators.td.visualizers.ui.TowerUI;
import org.glizzygladiators.td.visualizers.ui.EnemyUI;

import java.util.HashMap;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class GameInstanceDriver {
    
    public static final String TOWER_ACTION = "Towers";
    public static final String HEALTH_ACTION = "Health";
    public static final String MONEY_ACTION = "Money";
    public static final String SPAWN_ENEMY = "Spawn_Enemy";
    public static final String MOVE_ENEMY = "Move_Enemy";
    public static final String DESPAWN_ENEMY = "Despawn_Enemy";

    private static GameInstanceDriver gameInstanceDriver;
    private PropertyChangeSupport support;
    private Lock lock;

    private final GameInstance gameInstance;

    private final java.util.Map<Tower, TowerUI> towers;
    private final java.util.Map<Integer, EnemyUI> enemies;
    private final MonumentUI monument;
    
    public static void setInstance(GameInstance instance) {
        gameInstanceDriver = new GameInstanceDriver(instance);
    }

    public static GameInstanceDriver getDriver() {
        return gameInstanceDriver;
    }

    /**
     * A private constructor to support the singleton construct with the static 
     * field above.
     * 
     * @param gameInstance The Game instance we are wrapping.
     */
    private GameInstanceDriver(GameInstance gameInstance) {
        this.lock = new ReentrantReadWriteLock().writeLock();
        this.gameInstance = gameInstance;
        this.towers = new HashMap<>();
        this.enemies = new HashMap<>();
        this.monument = new MonumentUI(gameInstance.getMonument());
        this.support = new PropertyChangeSupport(this);
    }

    public MonumentUI getMonument() {
        return monument;
    }

    public void addTower(Tower tower) {
        TowerUI towerUI = new TowerUI(tower);
        towers.put(tower, towerUI);
        gameInstance.getTowers().add(tower);
        support.firePropertyChange(TOWER_ACTION, "", towerUI);
    }

    public void removeTower(Tower tower) {
        gameInstance.getTowers().remove(tower);
        support.firePropertyChange(TOWER_ACTION, towers.remove(tower), "");
    }

    public void setHealth(int health) {
        //int oldHealth = gameInstance.getHealth();
        //gameInstance.setHealth(health);
        //support.firePropertyChange(HEALTH_ACTION, oldHealth, health);
        gameInstance.setHealth(health);
    }

    public void setMoney(int money) {
        //int oldMoney = gameInstance.getMoney();
        //gameInstance.setMoney(money);
        //support.firePropertyChange(MONEY_ACTION, oldMoney, money);
        gameInstance.setMoney(money);
    }
    public void setScore(int score) {
        gameInstance.setScore(score);
    }

    public void setMoneySpent(int spent) {
        gameInstance.setMoneySpent(spent);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }


    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    /**
     * Do NOT modify anything within the object without using a PropertyChangeListener.
     * @return Game Instance
     */

    public GameInstance getGame() {
        return gameInstance;
    }
}
