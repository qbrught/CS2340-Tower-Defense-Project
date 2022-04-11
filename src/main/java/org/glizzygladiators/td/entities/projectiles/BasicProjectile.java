package org.glizzygladiators.td.entities.projectiles;

public class BasicProjectile extends Projectile {
    public static final int PROJECTILE_SPEED = 3;
    public static final String IMG_LOCATION = "images/dart.png";

    public BasicProjectile(int x, int y, int xDir, int yDir, int damage) {
        super(x, y, DEFAULT_PROJECTILE_SIZE, DEFAULT_PROJECTILE_SIZE,
                IMG_LOCATION, xDir, yDir, PROJECTILE_SPEED, damage);
    }

    @Override
    public void detonate() {
        // do nothing
    }
}
