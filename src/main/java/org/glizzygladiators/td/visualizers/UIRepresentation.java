package org.glizzygladiators.td.visualizers;

import java.util.Map;

import javafx.scene.Parent;
import javafx.beans.property.Property;

public class UIRepresentation {
    private Map<String, Property<?>> properties;
    private Map<String, Parent> resources;

    public Property<?> getProperty(String string) {
        return properties.get(string);
    }

    public Parent getSceneRoot(String string) {
        return resources.get(string);
    }
}
