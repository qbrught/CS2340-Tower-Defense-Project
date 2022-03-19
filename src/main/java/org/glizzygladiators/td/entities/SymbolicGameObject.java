package org.glizzygladiators.td.entities;

public class SymbolicGameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String imgPath;

    public SymbolicGameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imgPath = null;
    }

    public SymbolicGameObject(int x, int y, int width, int height, String imgPath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imgPath = imgPath;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
        return x <= go.getX() + go.getWidth()
                && x + width >= go.getX()
                && y <= go.getY() + go.getHeight()
                && y + height >= go.getY();
    }
    public boolean isOutOfBounds() {
        return (x > 1000 - width || x < 0)
                || (y > 750 - height || y < 0);
    }
}
