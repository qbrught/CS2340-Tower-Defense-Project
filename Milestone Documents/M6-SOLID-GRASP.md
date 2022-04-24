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

## Protected Variations: Destroyed functional interface

This principle states that we want to protect code from variations of other classes. In this case, we abstracted the end behavior of collision between different objects into the ```Destroyed``` interface . This increases stability of code and allows for interchangeability of different behaviors depending on what is being destroyed. For example, this interface can be invoked when a bullet makes contact with the enemy, or when the enemy comes in contact with the tower. This follows the code to an interface principle, increasing abstraction and stability.

## Polymorphism: Tower class and various Tower types

When we are dealing with different Towers, one simple approach is to select an image to load based on an elaborate map or switch statement. Instead, what we did was we had a central ```Tower``` class which can then be extended by ```BasicTower```, ```CannonTower```, and ```BoostTower```. Extends is the polymorphic operation we use to implement this principle. This is an example of polymorphism in use where we can use polymorphism to determine how a tower can be visualized during runtime. This dos away with the need to do intricate if/else logic to determine which tower is specified, letting polymorphism to handle it for us. 

## Information Expertion: GameInstance class

GameInstance is the class which contains all the logical representations of our UI components. As a result, all objects, such as ```Projectile``` objects, are created there. Also, all movement logic had also been assigned to GameInstance so that a ```move()``` function is invoked by the ```AnimationTimer``` every frame. We found that by doing so, GameInstance can have a consistent logical state of what is beiong visualized. In doing so, since it has all the necessary information such as what path the enemies take, projectile direction and speed, and tower locations. Everything relating to enemy movement, collisions, and tower firing projectiles are all dealt with here. 
