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

    private Button button;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    public void start(Stage stage) {
        button = new Button("click me!");
        button.setId("myButton");
        button.setOnAction(actionEvent -> button.setText("clicked!"));
        Scene scene = new Scene(new StackPane(button), 100, 100);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    void should_contain_button_with_text() {
        FxAssert.verifyThat(button, LabeledMatchers.hasText("click me!"));
        // or (lookup by css id):
        FxAssert.verifyThat("#myButton", LabeledMatchers.hasText("click me!"));
        // or (lookup by css class):
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("click me!"));
    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    void when_button_is_clicked_text_changes() {
        // when:
        clickOn("#myButton");

        // then:
        FxAssert.verifyThat(button, LabeledMatchers.hasText("clicked!"));
        // or (lookup by css id):
        FxAssert.verifyThat("#myButton", LabeledMatchers.hasText("clicked!"));
        // or (lookup by css class):
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("clicked!"));
    }
}
