package concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Integer> {

    private static final int MIN_EXECUTION_TIME = 2;
    private static final int MAX_EXECUTION_TIME = 10;
    private static final double DEFAULT_FAILING_CHANCE = 0.25;

    private final Random random = new Random();
    private final String name;
    private Status status = Status.Pending;
    private Integer result;
    private final FutureTask<Integer> futureTask;
    private final double failingChance;

    public Task(String name) {
        this(name, DEFAULT_FAILING_CHANCE);
    }

    public Task(String name, double failingChance) {
        this.name = name;
        futureTask = new FutureTask<>(this);
        this.failingChance = failingChance;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getResult() {
        return result;
    }

    public FutureTask<Integer> getFutureTask() {
        return futureTask;
    }

    public boolean isDone() {
        return futureTask.isDone();
    }

    @Override
    public Integer call() {
        status = Status.Running;
        int time = MIN_EXECUTION_TIME + random.nextInt(MAX_EXECUTION_TIME - MIN_EXECUTION_TIME);
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        boolean shouldFail = random.nextDouble() < failingChance;
        if (shouldFail) {
            result = null;
            status = Status.Failed;
        } else {
            result = random.nextInt();
            status = Status.Accomplished;
        }
        return result;
    }

}