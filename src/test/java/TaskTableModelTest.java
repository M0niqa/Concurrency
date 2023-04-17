import concurrency.Task;
import concurrency.TasksTableModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTableModelTest {

    @Test
    public void shouldAddToTaskList() {
        Task task1 = new Task("task 1");
        TasksTableModel model = new TasksTableModel();

        boolean wasTaskAdded = model.addTaskIfPossible(task1);

        assertTrue(wasTaskAdded);
        assertEquals(1, model.getTasksList().size());
        assertEquals(model.getTasksList().get(0), task1);
    }

    @Test
    public void shouldNotAddToTaskListIfListFull() {
        Task task21 = new Task("task 21");
        TasksTableModel model = new TasksTableModel();
        for (int i = 0; i < 20; i++) {
            model.addTaskIfPossible(new Task("Task " + i));
        }

        boolean wasTaskAdded = model.addTaskIfPossible(task21);

        assertFalse(wasTaskAdded);
    }
}
