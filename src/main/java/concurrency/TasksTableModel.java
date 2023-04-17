package concurrency;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TasksTableModel extends AbstractTableModel {

    private static final int TASK_LIST_MAX_SIZE = 20;
    private static final String[] COLUMN_NAMES = {"Name", "Status", "Result"};
    private final List<Task> tasks = new ArrayList<>();


    public boolean addTaskIfPossible(Task task) {
        boolean taskAdded = false;
        if (tasks.size() < TASK_LIST_MAX_SIZE) {
            tasks.add(task);
            taskAdded = true;
        } else {
            Optional<Task> finishedTask = findFinishedTask();
            if (finishedTask.isPresent()) {
                tasks.remove(finishedTask.get());
                tasks.add(task);
                taskAdded = true;
            }
        }
        return taskAdded;
    }

    private Optional<Task> findFinishedTask() {
        return tasks.stream().filter(Task::isDone)
                .findAny();
    }

    public List<Task> getTasksList() {
        return tasks;
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Task task = tasks.get(row);
        return switch (column) {
            case 0 -> task.getName();
            case 1 -> task.getStatus();
            case 2 -> task.getResult();
            default -> null;
        };
    }

}