package org.glizzygladiators.td.visualizers.ui;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.entities.Monument;

public class MonumentUI extends Rectangle {
    public MonumentUI(Monument monument) {
        super(monument.getX(), 
              monument.getY(), 
              monument.getWidth(), 
              monument.getHeight());
        setFill(new ImagePattern(new Image(TDApp.getResourcePath(monument.IMG_PATH))));
    }
}
