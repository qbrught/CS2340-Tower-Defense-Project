package org.glizzygladiators.td.entities.projectiles;

public class TankProjectile extends Projectile {
    public static final int PROJECTILE_SPEED = 3;
    public static final String imgLocation = "images/bomb.png";

    public TankProjectile(int x, int y, int xDir, int yDir, int damage) {
        super(x, y, DEFAULT_PROJECTILE_SIZE, DEFAULT_PROJECTILE_SIZE,
                imgLocation, xDir, yDir, PROJECTILE_SPEED, damage);
    }

    @Override
    public void detonate() {
        // do nothing
    }
}
