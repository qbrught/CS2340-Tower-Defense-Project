package org.glizzygladiators.td.entities;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SymbolicGameObject {

    protected DoubleProperty x;
    protected DoubleProperty y;
    protected int width;
    protected int height;
    protected String imgPath;

    public SymbolicGameObject(int x, int y, int width, int height) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.width = width;
        this.height = height;
        this.imgPath = null;
    }

    public SymbolicGameObject(int x, int y, int width, int height, String imgPath) {
        this(x, y, width, height);
        this.imgPath = imgPath;
    }

    public DoubleProperty getXProperty() {
        return x;
    }

    public int getX() {
        return x.intValue();
    }

    public void setX(int x) {
        this.x.setValue(x);
    }

    public DoubleProperty getYProperty() {
        return y;
    }

    public int getY() {
        return y.intValue();
    }

    public void setY(int y) {
        this.y.setValue(y);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public boolean hasCollision(SymbolicGameObject go) {
        return this.getX() <= go.getX() + go.getWidth()
                && this.getX() + width >= go.getX()
                && this.getY() <= go.getY() + go.getHeight()
                && this.getY() + height >= go.getY();
    }

    public boolean isOutOfBounds() {
        return (getX() > 1000 - width || getX() < 0)
                || (getY() > 750 - height || getY() < 0);
    }
}
