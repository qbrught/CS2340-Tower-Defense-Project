package org.glizzygladiators.td.game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.glizzygladiators.td.TDApp;

public class Monument extends javafx.scene.shape.Rectangle {

    private static final int SIZE = 200;

    /**
     * Instantiates a monument object
     * @param posX X position
     * @param posY Y position
     * @param resource The string to the image of the monument starting at "resources"
     */
    public Monument(int posX, int posY, String resource) {
        setX(posX);
        setY(posY);
        setWidth(SIZE);
        setHeight(SIZE);
        var resourcePath = TDApp.class.getResource(resource).toExternalForm();
        var pattern = new ImagePattern(new Image(resourcePath));
        setFill(pattern);
    }
}
