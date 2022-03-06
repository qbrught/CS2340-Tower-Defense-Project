package org.glizzygladiators.td;
  
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TDAppTest extends ApplicationTest {

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    public void start(Stage stage) {
        Scene scene = new Scene(TDApp.getParent("scenes/WelcomeScreen.fxml"));
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void should_contain_button_with_text() {
        FxAssert.verifyThat("#startButton", LabeledMatchers.hasText("Start"));
        FxAssert.verifyThat("#quitButton", LabeledMatchers.hasText("Quit"));
    }

    @Test
    void when_button_is_clicked_text_changes() {
        clickOn("#startButton");

        FxAssert.verifyThat("#EasyButton", LabeledMatchers.hasText("Easy"));
        FxAssert.verifyThat("#MediumButton", LabeledMatchers.hasText("Medium"));
        FxAssert.verifyThat("#HardButton", LabeledMatchers.hasText("Hard"));
    }
}
