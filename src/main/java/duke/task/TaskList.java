package duke.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    // contains tasks in an ArrayList
    private final ArrayList<Task> tasks;

    /**
     * Creates a new TaskList.
     *
     * @param tasks the ArrayList of tasks to be stored as a TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a specified task as completed.
     *
     * @param i the index of the task to be marked
     * @return the marked task
     */
    public Task markTask(int i) {
        tasks.get(i)
                .markAsDone();

        return tasks.get(i);
    }

    /**
     * Marks a specified task as uncompleted.
     *
     * @param i the index of the task to be marked
     * @return the marked task
     */
    public Task unmarkTask(int i) {
        tasks.get(i)
                .markAsUndone();

        return tasks.get(i);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param t the task that was added
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param i the index of the task to be deleted
     * @return the deleted task
     */
    public Task deleteTask(int i) {
        Task t = tasks.get(i);
        tasks.remove(i);

        return t;
    }

    /**
     * Retrieves a task from the TaskList.
     *
     * @param i the index of the task to be retrieved
     * @return the retrieved task
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Checks the current number of tasks.
     *
     * @return the number of tasks in the TaskList
     */
    public int listSize() {
        return tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     * @return true if the TaskList is empty, false otherwise
     */
    public boolean isEmpty() {
        return listSize() == 0;
    }

    /**
     * Checks if the specified task exists in the TaskList.
     * @param i the index of the task provided
     * @return true if task exists, false otherwise
     */
    public boolean containsTask(int i) {
        return i >= 0 && i < listSize();
    }

    /**
     * Filters the TaskList by a specified keyword.
     * @param keyword the specified keyword
     * @return a new TaskList with tasks matching the keyword
     */
    public TaskList filter(String keyword) {
        return new TaskList(tasks.stream()
                        .filter(task -> task.hasKeyword(keyword))
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    /**
     * Produces a string representation of the TaskList.
     *
     * @return a string containing the tasks within the TaskList
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            s.append("    ")
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.get(i));
            // no new line after last task
            if (i + 1 < tasks.size()) {
                s.append("\n");
            }
        }

        return s.toString();
    }
}
