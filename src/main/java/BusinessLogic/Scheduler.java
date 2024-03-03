package BusinessLogic;

import Model.*;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final List<Server> servers;
    private Strategy strategy;

    public Scheduler(int maxNoServers,int maxTasksPerServer, SelectionPolicy selectionPolicy) {
        this.servers = new ArrayList<>();
        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server(i + 1, maxTasksPerServer);
            servers.add(server);
            Thread thread = new Thread(server);
            thread.start();
        }
        changeStrategy(selectionPolicy);
    }
    public void changeStrategy(SelectionPolicy policy){

        if(policy==SelectionPolicy.SHORTEST_QUEUE){
            strategy=new ConcreteStrategyQueue();
        }
        if(policy==SelectionPolicy.SHORTEST_TIME){
            strategy=new ConcreteStrategyTime();
        }
    }
    public synchronized void dispatchTask(Task t) throws InterruptedException {
        strategy.addTask(servers,t);
    }

    public List<Server> getServers() {
        return servers;
    }
}