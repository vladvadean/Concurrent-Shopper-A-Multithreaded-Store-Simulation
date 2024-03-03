package BusinessLogic;

import Model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static BusinessLogic.SelectionPolicy.SHORTEST_TIME;
import static java.lang.System.exit;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfClients;
    public int noServers;
    public int maxTaskPerServer = 5;

    private final Scheduler scheduler;
    private List<Task> generatedTasks;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, int numberOfClients, int noServers) {
        this.timeLimit = timeLimit;
        this.maxArrivalTime = maxProcessingTime;
        this.minArrivalTime = minProcessingTime;
        this.maxProcessingTime = maxArrivalTime;
        this.minProcessingTime = minArrivalTime;
        this.numberOfClients = numberOfClients;
        this.noServers = noServers;

        generateNRandomTasks();
        scheduler = new Scheduler(noServers, maxTaskPerServer, SHORTEST_TIME);
    }

    private void generateNRandomTasks() {
        Random rand = new Random();
        List<Task> result = new ArrayList<>();
        for (int i = 0; i < this.numberOfClients; i++) {
            int possibleArrivingTimeInterval = maxArrivalTime - minArrivalTime;
            int arrivalTime = rand.nextInt(possibleArrivingTimeInterval) + minArrivalTime;
            int possibleProcessingTimeInterval = maxProcessingTime - minProcessingTime;
            int processingTime = rand.nextInt(possibleProcessingTimeInterval) + minProcessingTime;
            Task aux = new Task(i + 1, processingTime, arrivalTime);
            result.add(aux);
        }
        Collections.sort(result);
        System.out.println("list of clients:");
        for (int i = 0; i < this.numberOfClients; i++) {
            System.out.println(result.get(i));
        }
        generatedTasks = result;
    }

    @Override
    public void run() {
        int currentTime = 1;
        int peakHour = 0;
        int peakValue = Integer.MIN_VALUE;
        float avgWaitingTime = 0;
        float avgServiceTime = 0;
        int clientsServed = 0;
        while (!(currentTime >= timeLimit || generatedTasks.isEmpty())) {
            while (!generatedTasks.isEmpty()) {
                if (generatedTasks.get(0).getArrivalTime() <= currentTime) {
                    try {
                        scheduler.dispatchTask(generatedTasks.get(0));
                        avgServiceTime += generatedTasks.get(0).getServiceTime();
                        generatedTasks.remove(0);
                        clientsServed++;
                    } catch (InterruptedException e) {
                        exit(-1);
                    }
                } else break;
            }
            avgWaitingTime += generatedTasks.size();
            System.out.println("time: " + currentTime);
            int sumTasks = 0;
            for (Server server : scheduler.getServers()) {
                sumTasks += server.getTasks().size();
                System.out.print("Server " + server.getId() + " waitingTime:" + server.getWaitingPeriod() + " current tasks: ");
                for (Task t : server.getTasks()) {
                    System.out.print(" " + t);
                }
                System.out.println();
            }
            if (sumTasks > peakValue) {
                peakValue = sumTasks;
                peakHour = currentTime;
            }
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                exit(-1);
            }
        }
        avgWaitingTime /= clientsServed;
        avgServiceTime /= clientsServed;
        System.out.println("peak time: " + peakHour + " with " + peakValue + " running tasks");
        System.out.printf("average waiting time for a task to be inserted in a server: %.2f\n", avgWaitingTime);
        System.out.printf("average service time to complete a task: %.2f\n", avgServiceTime);
    }

}