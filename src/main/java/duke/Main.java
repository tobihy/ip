package duke;

import javafx.application.Application;
import javafx.stage.Stage;

import duke.ui.MainWindow;

/**
 * Entry point of the Duke application.
 * Initialises the application and starts up a Duke chatbot.
 */
public class Main extends Application {
    /**
     * Starts up the application.
     *
     * @param stage the stage of the application
     */
    @Override
    public void start(Stage stage) {
        MainWindow window = new MainWindow(stage);
        new Duke().run(window);
    }
}
