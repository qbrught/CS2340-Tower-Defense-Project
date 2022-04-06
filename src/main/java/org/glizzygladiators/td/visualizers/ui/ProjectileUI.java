package org.glizzygladiators.td.visualizers.ui;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.entities.projectiles.Projectile;

public class ProjectileUI extends Rectangle {

    private final Projectile projectile;

    public ProjectileUI(Projectile projectile) {
        super(projectile.getX(),
              projectile.getY(),
              projectile.getWidth(),
              projectile.getHeight());
        setFill(new ImagePattern(new Image(TDApp.getResourcePath(projectile.getImgPath()))));
        this.projectile = projectile;
    }

    public Projectile getProjectile() {
        return projectile;
    }
}
