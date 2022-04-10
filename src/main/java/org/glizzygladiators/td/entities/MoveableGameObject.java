package org.glizzygladiators.td.entities;

import java.lang.Math;

public class MoveableGameObject extends SymbolicGameObject {
    public MoveableGameObject(int width, int height, String imgPath) {
        super(0, 0, width, height, imgPath);
    }

    public void translate(int x, int y, double speed) {
        double mag = Math.pow(x * x + y * y, 0.5);
        if (mag == 0) return;
        double normalizedX = x * speed / mag;
        double normalizedY = y * speed / mag;
        this.x.set(this.x.getValue() + normalizedX);
        this.y.set(this.y.getValue() + normalizedY);
    }
}
