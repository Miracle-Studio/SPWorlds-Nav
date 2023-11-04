package ua.mei.spwn.util;

import java.util.*;
import java.util.concurrent.*;

public class AsyncTasksService {
    protected ExecutorService threadPool;
    protected LinkedList<AsyncTask> tasks = new LinkedList<>();
    protected LinkedList<AsyncTask> newTasks = new LinkedList<>();

    public AsyncTasksService(ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

    public void addTask(Task task, TaskCallback callback, TaskExceptionCallback exceptionCallback) {
        Future<Object> future = threadPool.submit(task::run);
        newTasks.add(new AsyncTask(future, callback, exceptionCallback));
    }

    public void updateTasks() {
        tasks.addAll(newTasks);
        newTasks.clear();

        for (AsyncTask task : tasks) {
            if (!task.isDone()) continue;

            try {
                task.callback().onEnd(task.task().get());
            } catch (ExecutionException e) {
                e.printStackTrace();
                task.exceptionCallback().onEnd((Exception) e.getCause());
            } catch (Exception e) {
                e.printStackTrace();
                task.exceptionCallback().onEnd(e);
            }
        }
    }

    public void removeDone() {
        tasks.removeIf(AsyncTask::isDone);
    }

    public interface Task {
        Object run() throws Exception;
    }

    public interface TaskCallback {
        void onEnd(Object result);
    }

    public interface TaskExceptionCallback {
        void onEnd(Exception e);
    }

    protected record AsyncTask(Future<Object> task, TaskCallback callback, TaskExceptionCallback exceptionCallback) {
        public boolean isDone() {
            return task.isDone();
        }
    }
}
