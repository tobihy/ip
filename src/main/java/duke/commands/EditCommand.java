package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidEditException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Edits a task from the TaskList.
 */
public class EditCommand extends Command {
    private static final String EXECUTE_PREFIX_MESSAGE = "I have edited the task for you: ";

    private enum EditType {
        DESCRIPTION,
        DATE,
        TIME,
        DATETIME
    }

    private final int target;
    private String description;
    private LocalDate d;
    private LocalTime t;
    private final EditType type;

    /**
     * Creates a new command to edit description.
     *
     * @param target the task given by the user
     * @param description the new description
     */
    public EditCommand(int target, String description) {
        this.target = target - 1;
        this.description = description;
        this.type = EditType.DESCRIPTION;
    }

    /**
     * Creates a new command to edit date.
     *
     * @param target the task given by the user
     * @param d the new date
     */
    public EditCommand(int target, LocalDate d) {
        this.target = target - 1;
        this.d = d;
        this.type = EditType.DATE;
    }

    /**
     * Creates a new command to edit time.
     *
     * @param target the task given by the user
     * @param t the new time
     */
    public EditCommand(int target, LocalTime t) {
        this.target = target - 1;
        this.t = t;
        this.type = EditType.TIME;
    }

    /**
     * Creates a new command to edit date and time.
     *
     * @param target the task given by the user
     * @param d the new date
     * @param t the new time
     */
    public EditCommand(int target, LocalDate d, LocalTime t) {
        this.target = target - 1;
        this.d = d;
        this.t = t;
        this.type = EditType.DATETIME;
    }

    /**
     * Executes the edit command and returns the result.
     *
     * @param tasks   the TaskList containing the current tasks
     * @param ui      the Ui of the chatbot
     * @param storage the storage of the chatbot
     * @return the result of execution
     * @throws DukeException if there were any errors during execution
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!tasks.containsTask(target)) {
            throw new InvalidEditException("the task does not exist.");
        }

        Task targetTask = tasks.getTask(target);
        switch (type) {
        case DESCRIPTION:
            targetTask.editDescription(description);
            break;
        case DATE:
            targetTask.editDate(d);
            break;
        case TIME:
            targetTask.editTime(t);
            break;
        case DATETIME:
            targetTask.editDateTime(d, t);
            break;
        default:
            throw new DukeException("An error has occurred while executing edit.");
        }
        storage.save(tasks);

        return EXECUTE_PREFIX_MESSAGE + "\n" + targetTask;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
