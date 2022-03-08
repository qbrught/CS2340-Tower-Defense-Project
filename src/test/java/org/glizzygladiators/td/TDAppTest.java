package org.glizzygladiators.td;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.Scene;
import javafx.stage.Stage;

class TDAppTest extends ApplicationTest {

    private Scene scene;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    public void start(Stage stage) {
        Scene scene = new Scene(TDApp.getParent("scenes/WelcomeScreen.fxml"));
        this.scene = scene;
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void testWelcomeScreen() {
        FxAssert.verifyThat("#startButton", LabeledMatchers.hasText("Start"));
        FxAssert.verifyThat("#quitButton", LabeledMatchers.hasText("Quit"));
    }
 
    @Test
    void testStartButton() {
        clickOn("#startButton");

        FxAssert.verifyThat("#EasyButton", LabeledMatchers.hasText("Easy"));
        FxAssert.verifyThat("#MediumButton", LabeledMatchers.hasText("Medium"));
        FxAssert.verifyThat("#HardButton", LabeledMatchers.hasText("Hard"));
    }

    void testUserSettingsWarningMessage(String name, String warning, boolean nullDifficulty) {
        clickOn("#startButton");

        clickOn("#playerNameInput").write(name);
        if (!nullDifficulty) {
            clickOn("#EasyButton");
        }
        clickOn("#ContinueButton");

        FxAssert.verifyThat("#ExitImproperUserSettingsButton", LabeledMatchers.hasText("OK"));
        FxAssert.verifyThat("#UserSettingsWarningContent", LabeledMatchers.hasText(warning));
    }

    /*@Test
    void testImproperName() {
        String name = "     ";
        String warning = "You must select your difficulty and choose a valid name "
            + "before proceeding!";
        testUserSettingsWarningMessage(name, warning, false);
    }

    @Test
    void testCharacterLimit() {
        String name = "aosidjoasjdoijasodaoaisjdoaijsdoij"; // spam characters longer than 20
        String warning = "The character limit for names is 20!";
        testUserSettingsWarningMessage(name, warning, false);
    }

    @Test
    void testNullDifficulty() {
        String name = "bob";
        String warning = "You must select your difficulty and choose a valid name "
            + "before proceeding!";
        testUserSettingsWarningMessage(name, warning, true);
    }*/

    void testChangingAmountsPerDifficulty(String difficulty, int expectedMoney,
                                          int expectedHealth) {
        clickOn("#startButton");

        clickOn("#playerNameInput").write("BadGameDesign");
        clickOn("#" + difficulty + "Button");
        clickOn("#ContinueButton");
        clickOn("#ConfirmButton");
        
        String moneyText = Integer.toString(expectedMoney);
        String healthText = Integer.toString(expectedHealth);
        FxAssert.verifyThat("#MoneyAmountLabel", LabeledMatchers.hasText(moneyText));
        FxAssert.verifyThat("#MonumentHealthLAbel", LabeledMatchers.hasText(healthText));
    }

   @Test
    void testEasyDifficulty()  {
        testChangingAmountsPerDifficulty("Easy", 500, 200);
    }

    @Test
    void testMediumDifficulty()  {
        testChangingAmountsPerDifficulty("Medium", 375, 150);
    }

    @Test
    void testHardDifficulty() {
        testChangingAmountsPerDifficulty("Hard", 250, 100);
    }
}
