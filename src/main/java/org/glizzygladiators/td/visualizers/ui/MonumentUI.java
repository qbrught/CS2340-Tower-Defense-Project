package org.glizzygladiators.td.visualizers.ui;

import org.glizzygladiators.td.entities.Monument;

public class MonumentUI extends RectangleUI {
    public MonumentUI(Monument monument) {
        super(monument.getX(), 
              monument.getY(), 
              monument.getWidth(), 
              monument.getHeight(), 
              monument.getResourceLocation());
    }
}
