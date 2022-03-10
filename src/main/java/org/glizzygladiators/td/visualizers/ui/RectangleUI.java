package org.glizzygladiators.td.visualizers.ui;

import org.glizzygladiators.td.TDApp;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class RectangleUI extends javafx.scene.shape.Rectangle{
    
    public RectangleUI(int x, int y, int width, int height, String resourceLocation) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(width);
        if (resourceLocation != null) {
            var resourcePath = TDApp.class.getResource(resourceLocation).toExternalForm();
            var pattern = new ImagePattern(new Image(resourcePath));
            setFill(pattern);
        }
    } 
}
