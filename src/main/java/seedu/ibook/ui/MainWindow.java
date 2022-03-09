package seedu.ibook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.ibook.commons.core.LogsCenter;
import seedu.ibook.logic.Logic;
import seedu.ibook.logic.commands.CommandResult;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;

    private MenuToolbar menuToolbar;
    private CommandBox commandBox;
    private ResultWindow resultWindow;
    private Table table;

    @FXML
    private VBox mainContent;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        primaryStage.hide();
    }

    Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        ObservableList<Node> children = mainContent.getChildren();

        menuToolbar = new MenuToolbar();
        children.add(menuToolbar.getRoot());

        commandBox = new CommandBox(this::executeCommand);
        children.add(commandBox.getRoot());

        resultWindow = new ResultWindow();
        children.add(resultWindow.getRoot());

        table = new Table(logic.getFilteredIBook());
        children.add(table.getRoot());
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.ibook.logic.Logic#execute(String)
     */
    private void executeCommand(String commandText) {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultWindow.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isExit()) {
                handleExit();
            }

        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultWindow.setFeedbackToUser(e.getMessage());
        }
    }
}
