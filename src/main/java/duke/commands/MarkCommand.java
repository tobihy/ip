package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private final int targetIndex;

    public MarkCommand(int i) {
        targetIndex = i - 1;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.markTask(targetIndex);
        storage.save(tasks);

        return "Great job! I've marked the task as completed:\n" + ui.tab(t.toString());
    }

    public boolean isExit() {
        return false;
    }
}
