package Model;

public class Task implements Comparable<Task> {
    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    @Override
    public int compareTo(Task o) {
        return this.arrivalTime - o.arrivalTime;
    }

    public int getID() {
        return ID;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String toString() {
        return "id: " + this.getID() + " processingTime: " + this.getServiceTime() + " arrivalTime: " + this.getArrivalTime();
    }
}
