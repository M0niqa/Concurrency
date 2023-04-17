package concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {

    private static final int THREAD_POOL_SIZE = 5;
    private final static AtomicInteger taskId = new AtomicInteger(0);
    private final Executor executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    private final TasksTableModel model;
    private final TasksFrame view;

    public Controller() {
        model = new TasksTableModel();
        view = new TasksFrame(model);
        view.addNewTaskListener(e -> generateTask());
    }

    private void generateTask() {
        Task task = new Task(generateName());
        boolean taskAdded = model.addTaskIfPossible(task);
        if (taskAdded) {
            executor.execute(task.getFutureTask());
        }
    }

    private static String generateName() {
        return "task " + taskId.incrementAndGet();
    }
}
