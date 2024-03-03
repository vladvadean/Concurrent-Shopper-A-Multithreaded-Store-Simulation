package Model;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.exit;


public class Server implements Runnable {
    private final BlockingQueue<Task> tasks;
    private final AtomicInteger waitingPeriod;
    private final int id;

    public Server(int id, int maxTasksPerServer) {
        waitingPeriod = new AtomicInteger(0);
        tasks = new LinkedBlockingQueue<>(40);
        this.id = id;
    }

    public synchronized void addTask(Task newTask){
        try {
            tasks.put(newTask);
        } catch (InterruptedException e) {
            exit(-1);
        }
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                exit(-1);
            }
            if (tasks.size() > 0) {
                tasks.element().setServiceTime(tasks.element().getServiceTime() - 1);
                waitingPeriod.decrementAndGet();
                if (tasks.element().getServiceTime() == 0) {
                    try {
                        tasks.take();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }
}