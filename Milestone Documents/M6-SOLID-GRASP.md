# M6 - SOLID and GRASP Principles
M5-smelly.md

## Low Coupling - TowerUI class and Tower class
```java
public class TowerUI extends Rectangle {

    private final Tower tower;

    public TowerUI(Tower tower) {
        super(tower.getX(), tower.getY(), tower.getWidth(), tower.getHeight());
        setFill(new ImagePattern(new Image(TDApp.getResourcePath(tower.getImgPath()))));
        this.tower = tower;
    }

    public Tower getTower() { return tower; }
}
```

This code reduces coupling between the UI code and the logic of the game. We do this by wrapping the tower logic class inside the visual tower representation, TowerUI. This allows the UI to access values that are pertinent to rendering the tower, but leaves the logic and processing to the code behind.

## Liskov Substitution Principle - MoveableGameObject Interface

The Liskov substitution principle states that that parent class should be able to be substituted for a child of the parent without loss of functionality. In our game, everything is logically represented with through the SymbolicGameObject interface. Game objects that need to move implement a child of SymbolicGameObject called MoveableGameObject. All instances where a SymbolicGameObject is needed, a MoveableGameObject can also be passed in, as it also supports all of SymbolicGameObject's functionality.

