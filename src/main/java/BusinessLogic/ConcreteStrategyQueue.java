package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        int min = Integer.MAX_VALUE;
        int id = 0;
        for(Server i : servers)
            if(i.getTasks().size() < min) {
                min = i.getWaitingPeriod().get();
                id = i.getId();
            }
        for(Server i : servers) {
            if(i.getId() == id) {
                i.addTask(t);
            }
        }
    }
}
