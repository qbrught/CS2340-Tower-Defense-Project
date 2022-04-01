package org.glizzygladiators.td.entities.projectiles;

public class BasicProjectile extends Projectile {
    public static final int PROJECTILE_SPEED = 3;
    public static final int DAMAGE = 50;
    public static final String imgLocation = "images/obamaBasic.png";

    public BasicProjectile(int x, int y, int xDir, int yDir) {
        super(x, y, DEFAULT_PROJECTILE_SIZE, DEFAULT_PROJECTILE_SIZE,
              imgLocation, xDir, yDir, PROJECTILE_SPEED, DAMAGE);
    }

    @Override
    public void detonate() {
        // do nothing
    }
}
