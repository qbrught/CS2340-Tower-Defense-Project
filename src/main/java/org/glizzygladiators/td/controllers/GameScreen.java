package org.glizzygladiators.td.controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.glizzygladiators.td.TDApp;
import org.glizzygladiators.td.entities.*;
import org.glizzygladiators.td.entities.health.HealthBar;
import org.glizzygladiators.td.entities.health.HealthText;
import org.glizzygladiators.td.entities.towers.*;
import org.glizzygladiators.td.entities.enemies.*;
import org.glizzygladiators.td.entities.projectiles.Projectile;
import org.glizzygladiators.td.visualizers.GameInstanceDriver;
import org.glizzygladiators.td.visualizers.ui.TowerUI;
import org.glizzygladiators.td.visualizers.ui.EnemyUI;
import org.glizzygladiators.td.visualizers.ui.ProjectileUI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class GameScreen implements ParameterController, Initializable {

    @FXML
    private StackPane sideBarPane;
    @FXML
    private Group gamePane;

    @FXML
    private Label moneyLabel;
    @FXML
    private Label healthLabel;

    private ObservableList<Node> gameObjects;
    private GameInstanceDriver game;

    private EventHandler<MouseEvent> buyModeHandler = null;

    public static final int DEFAULT_DURATION_MS = 20000;
    public static final int DEFAULT_SPACING = 500;

    private Timer enemySpawnTimer;
    private AnimationTimer enemyMoveTimer;
    private AnimationTimer projectileTimer;
    private AnimationTimer projectileSpawnTimer;
    private int cycleCount = 0;
    private int enemiesSpawned = 0;
    private int wave = 0;

    /**
     * Runs code right after FXML objects are initialized
     *
     * @param url            url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameObjects = gamePane.getChildren();
        gamePane.prefWidth(1000);
        gamePane.prefHeight(750);
    }

    @Override
    public void setParams(Object params) {
        GameInstanceDriver.setInstance((GameInstance) params);
        game = GameInstanceDriver.getDriver();

        gameObjects.add(game.getMonument());
        enemySpawnTimer = new Timer(true);
        enemyMoveTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.getGame().moveEnemies();
            }
        };
        enemyMoveTimer.start();

        projectileTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.getGame().moveProjectiles();
            }
        };
        projectileTimer.start();

        projectileSpawnTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                cycleCount++;
                ArrayList<Projectile> projectiles = 
                    game.getGame().fireProjectiles(cycleCount);
                for (Projectile p : projectiles) {
                    ProjectileUI pUI = new ProjectileUI(p);
                    pUI.xProperty().bind(p.getXProperty());
                    pUI.yProperty().bind(p.getYProperty());
                    p.addListener(new DestroyedCallback() {
                        @Override
                        public void onDestroyed(Object obj) {
                            gameObjects.remove(pUI);
                            if (obj != null) {
                                Enemy e = (Enemy) obj;
                                e.setEnemyHealth(e.getEnemyHealth() - p.getDamage());
                                p.detonate(); // this might change
                            }
                        }
                    });
                    gameObjects.add(pUI);
                    game.getGame().addProjectile(p);
                }
            }
        };
        projectileSpawnTimer.start();

        moneyLabel.textProperty().bind(game.getGame().getMoneyProperty().asString());
        healthLabel.textProperty().bind(game.getGame().getHealthProperty().asString());
        healthLabel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue,
                                String newValue) {
                if (newValue.equals("0")) {
                    Scene scene = gamePane.getScene();
                    Parent root = TDApp.getParent("scenes/GameOverScreen.fxml");
                    TDApp.navigateToRoot(scene, root);
                    enemySpawnTimer.cancel();
                    enemyMoveTimer.stop();
                    projectileTimer.stop();
                    projectileSpawnTimer.stop();
                }
            }
        });

        PropertyChangeListener towerListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName() == GameInstanceDriver.TOWER_ACTION) {
                    if (evt.getOldValue() != "") {
                        gameObjects.remove((TowerUI) evt.getOldValue());
                    } else {
                        gameObjects.add((TowerUI) evt.getNewValue());
                    }
                }
            }
        };
        game.addPropertyChangeListener(towerListener);
        PropertyChangeListener enemyListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName() == GameInstanceDriver.SPAWN_ENEMY) {
                    gameObjects.add((Node) evt.getNewValue());
                }
            }
        };
        game.addPropertyChangeListener(enemyListener);
    }

    /**
     * Returns the GameInstance object associated with this session
     * @return the GameInstance object associated with this session
     */
    public GameInstanceDriver getGameDriver() {
        return game;
    }

    public Enemy getEnemy(int i, GameDifficulty difficulty) {
        switch (i) {
        case 0:
            return new BasicEnemy(0, 0, difficulty);
        case 1:
            return new JosephEnemy(0, 0, difficulty);
        case 2:
            return new JooperEnemy(0, 0, difficulty);
        case 3:
            return new MemeEnemy(0, 0, difficulty);
        case 4:
            return new AusEnemy(0, 0, difficulty);
        default:
            return new JosephEnemy(0, 0, difficulty);
        }
    }

    public void spawnEnemies(MouseEvent mouseEvent) {
        Button button = (Button) gamePane.getScene().lookup("#StartWaveButton");
        button.setDisable(true);
        wave++;
        Random random = new Random();
        random.setSeed(wave);
        long spacing = 1000;
        for (int i = 0; i < 10; i++) {
            enemySpawnTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    enemiesSpawned++;
                    Platform.runLater(() -> {
                        Enemy enemy = getEnemy(random.nextInt(5), game.getGame().getDifficulty());
                        EnemyUI enemyUI = new EnemyUI(enemy);
                        HealthBar healthBar = new HealthBar(enemy.getX(), enemy.getY(),
                                enemy.getEnemyHealth(), 10);
                        HealthText healthText = new HealthText(enemy.getX(), enemy.getY(),
                                String.valueOf(enemy.getEnemyHealth()));
                        healthBar.update(enemy);
                        healthText.update(enemy);
                        enemyUI.xProperty().bind(enemy.getXProperty());
                        enemyUI.yProperty().bind(enemy.getYProperty());
                        enemy.getHealthProperty().addListener(new ChangeListener<Number>() {
                            @Override
                            public void changed(ObservableValue<? extends Number> observable,
                                                Number oldValue,
                                                Number newValue) {
                                Integer currentHealth = (Integer) newValue;
                                if (currentHealth <= 0) {
                                    enemy.fireCallback(false);
                                }
                            }
                        });
                        gameObjects.add(enemyUI);
                        gameObjects.add(healthBar);
                        gameObjects.add(healthText);
                        game.getGame().addEnemy(enemy);
                        enemy.setCallback(new DestroyedCallback() {
                            @Override
                            public void onDestroyed(Object doDamage) {
                                gameObjects.remove(enemyUI);
                                gameObjects.remove(healthBar);
                                gameObjects.remove(healthText);
                                game.getGame().removeEnemy(enemy);
                                if ((Boolean) doDamage) {
                                    game.getGame().setHealth(game.getGame().getHealth()
                                            - enemy.getDamage());
                                } else {
                                    game.getGame().setMoney(game.getGame().getMoney() + 10);  
                                }
                            }
                        });
                        if (enemiesSpawned == 10) {
                            enemiesSpawned = 0;
                            button.setDisable(false);
                        }
                    });
                }
            }, i * spacing);
        }
    }

    /**
     * Purchases a tower and allows a user to place it.
     * @param mouseEvent The mouse event that activates the buy tower process.
     */
    public void buyTower(MouseEvent mouseEvent) {
        var scene = new Scene(TDApp.getParentPassParams("scenes/BuyMenu.fxml", this));
        scene.getStylesheets().add(TDApp.class.getResource("styles/style.css")
                .toExternalForm());
        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void enterTowerPlacementMode(Tower tower) {
        Scene scene = ((Stage) gamePane.getScene().getWindow()).getScene();

        if (buyModeHandler != null) {
            TDApp.showErrorMsg("Invalid State", "You're already buying something else");
            return;
        }


        buyModeHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x = (int) mouseEvent.getSceneX();
                int y = (int) mouseEvent.getSceneY();
                x -= Tower.SIZE / 2; // This is so that where you click is the center of where
                y -= Tower.SIZE / 2; // the tower is placed

                tower.setX(x);
                tower.setY(y);

                if (game.getGame().isInvalidTowerLocation(tower)) {
                    TDApp.showErrorMsg("Invalid Location",
                            "You can't place a tower there");
                    return;
                }
                game.addTower(tower);
                game.setMoney(game.getGame().getMoney() 
                              - tower.getPrice(game.getGame().getDifficulty()));
                if (tower instanceof BoostTower) {
                    ((BoostTower) tower).boostOthers(game.getGame().getTowers());
                }
                scene.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                buyModeHandler = null;
                exitTowerPlacementMode();
            }
        };
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, buyModeHandler);
        Image image = new Image(TDApp.getResourcePath(tower.getImgPath()));
        scene.setCursor(new ImageCursor(image));
    }

    public void exitTowerPlacementMode() {
        if (buyModeHandler != null) {
            ((Stage) gamePane.getScene().getWindow())
                    .removeEventHandler(MouseEvent.MOUSE_CLICKED, buyModeHandler);
            buyModeHandler = null;
        }
        gamePane.getScene().setCursor(Cursor.DEFAULT);
    }

    public void upgradeMenu(MouseEvent mouseEvent) {
        ArrayList<Tower> towers = game.getGame().getTowers();
        Tower tower = null;
        for (Tower t : towers) {
            if (t.hasCollision(new SymbolicGameObject((int) mouseEvent.getX(), (int) mouseEvent.getY(), 1, 1))) {
                tower = t;
                break;
            }
        }

        if (tower == null) {
            return;
        } else if (tower instanceof BoostTower) {
            TDApp.showErrorMsg("Boost Tower Error", "You cannot upgrade a boost tower.");
            return;
        }

        UpgradeMenu.setWorkingTower(tower);
        var scene = new Scene(TDApp.getParentPassParams("scenes/UpgradeMenu.fxml", this));
        scene.getStylesheets().add(TDApp.class.getResource("styles/style.css")
                .toExternalForm());
        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
