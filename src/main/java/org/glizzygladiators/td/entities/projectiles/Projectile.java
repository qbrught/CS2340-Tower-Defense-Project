package org.glizzygladiators.td.entities.projectiles;

import java.util.ArrayList;

import org.glizzygladiators.td.entities.DestroyedCallback;
import org.glizzygladiators.td.entities.MoveableGameObject;
import org.glizzygladiators.td.entities.enemies.Enemy;

import javafx.util.Pair;

public abstract class Projectile extends MoveableGameObject {
    
    public static final int DEFAULT_PROJECTILE_SIZE = 10;

    public final int xVelocity;
    public final int yVelocity;
    public final int speed;
    private DestroyedCallback listener;
    public final int damage;

    public Projectile(int x, int y, 
                      int width, int height, String imgLocation,
                      int xDelta, int yDelta, int speed, int damage) {
        super(width, height, imgLocation);
        this.x.set(x);
        this.y.set(y);
        this.xVelocity = xDelta;
        this.yVelocity = yDelta;
        this.speed = speed;
        this.listener = null;
        this.damage = damage;
    }

    public Enemy collides(ArrayList<Pair<Enemy, Integer>> enemies) {
        for (Pair<Enemy, Integer> tuple : enemies) {
            if (this.hasCollision(tuple.getKey())) {
                return tuple.getKey();
            }
        }
        return null;
    }

    public void move() {
        translate(xVelocity, yVelocity, speed);
    }

    public void addListener(DestroyedCallback callback) {
        this.listener = callback;
    } 

    public void fireDestroyedEvent(Enemy enemy) {
        if (listener != null) {
            listener.onDestroyed(enemy);
        }
    }

    public abstract void detonate();
}
