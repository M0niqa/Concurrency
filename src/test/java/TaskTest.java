import concurrency.Status;
import concurrency.Task;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void shouldReturnNullAndFailStatusIfFailingChanceEqualsOne() {
        Task task = new Task("task 1", 1);

        task.call();

        assertEquals(task.getStatus(), Status.Failed);
        assertNull(task.getResult());
    }

    @Test
    public void shouldChangeStatusToAccomplishedIfFailingChanceIsZero() {
        Task task = new Task("task 1", 0);

        task.call();

        assertEquals(task.getStatus(), Status.Accomplished);
    }
}
