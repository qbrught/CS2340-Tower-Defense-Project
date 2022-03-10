package org.glizzygladiators.td.entities;

// Logical representation of rectangle
public class Rectangle {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String resourceLocation;

    public Rectangle(int x, int y, int width, int height, String resourceLocation) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.resourceLocation = resourceLocation;
    }

    public String getResourceLocation() {
        return resourceLocation;
    }

    public boolean hasCollision(Rectangle r) {
        return x <= r.getX() + r.getWidth()
               && x + width >= r.getX()
               && y <= r.getY() + r.getHeight()
               && y + height >= r.getY();
    }

    public boolean isOutOfBounds() {
        return (x > 1000 - width || x < 0) 
               || (y > 750 - height || y < 0);
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

    public int getHeight() {
        return height;
    }
}
