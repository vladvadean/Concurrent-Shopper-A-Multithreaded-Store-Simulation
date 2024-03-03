

# Concurrent-Shopper-A-Multithreaded-Store-Simulation

## Table of contents
1. [Assignment Objective](#assignment-objective) 
2. [Problem Analysis, Modeling, Scenarios, Use Cases](#problem-analysis-modeling-scenarios-use-cases) 
3. [Design](#design) 
4. [Implementation](#implementation) 
5. [Results](#results) 
6. [Conclusions](#conclusions) 
7. [Bibliography](#bibliography)

## Assignment Objective

&nbsp;&nbsp;&nbsp;&nbsp;Threads in Java play a vital role in enabling concurrent execution of tasks within an application, significantly improving performance and resource utilization. As a fundamental component of the Java programming language, threads facilitate the development of efficient, responsive, and scalable applications. Understanding the mechanisms behind Java threads is essential for effectively implementing multi-threaded applications, such as the queue management system discussed in this assignment.

&nbsp;&nbsp;&nbsp;&nbsp;Java threads are lightweight, independent units of execution managed by the Java Virtual Machine (JVM). Each thread has its own program counter, stack, and local variables, allowing them to execute concurrently without interfering with one another. The JVM schedules threads to run on available processor cores, enabling parallel execution and improving overall performance.

&nbsp;&nbsp;&nbsp;&nbsp;The Java language provides built-in support for thread creation, synchronization, and inter-thread communication through its java.lang.Thread class and the java.util.concurrent package. By leveraging these powerful constructs, developers can harness the full potential of multi-threaded programming to create robust, efficient, and responsive applications, such as the queue management system at the core of this assignment.

&nbsp;&nbsp;&nbsp;&nbsp;The objective of this project is to develop and implement a queue management application that allocates clients to queues in such a way that waiting time is minimized. Queues are commonly used to model real-world domains, and the main purpose of a queue is to provide a place for a "client" to wait before receiving a "service". Managing queue-based systems aims to reduce the time spent by "clients" in queues before being served. One way to minimize waiting time is to add more servers, i.e., more queues in the system (each queue is considered to have an associated processor), but this approach increases the costs of the service provider.

&nbsp;&nbsp;&nbsp;&nbsp;The queue management application should simulate, through a simulation time 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛, a series of N clients arriving for services, entering Q queues, waiting, being served, and finally leaving the queues. All clients are generated when the simulation is started and are characterized by three parameters: ID (a number between 1 and N), 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 (simulation time when they are ready to enter the queue) and 𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒 (time interval or duration needed to serve the client; i.e., waiting time when the client is at the front of the queue). The application tracks the total time spent by each client in the queues and calculates the average waiting time. Each client is added to the queue with the minimum waiting time when their 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 time is greater than or equal to the simulation time (𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 ≥ 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛)

## Problem Analysis, Modeling, Scenarios, Use Cases

&nbsp;&nbsp;&nbsp;&nbsp;The queue management application aims to address the problem of minimizing client waiting times in a queue-based system. Analyzing this problem requires understanding the parameters involved, such as the number of clients, their arrival times, and the time required to serve each client. Moreover, it is crucial to study the relationships between these parameters and the waiting time in queues.

&nbsp;&nbsp;&nbsp;&nbsp;To model this problem effectively, we can leverage simulation techniques to create a virtual environment replicating the real-world scenario. By defining a simulation time (𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛), we can generate a series of N clients with unique IDs, arrival times (𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙), and service times (𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒). Clients will enter Q queues, wait to be served, and eventually leave the queues.

&nbsp;&nbsp;&nbsp;&nbsp;Modeling the problem also entails identifying the optimal strategy for assigning clients to queues. In this case, we aim to minimize the average waiting time. One approach is to assign each client to the queue with the minimum waiting time at the moment of their arrival.

&nbsp;&nbsp;&nbsp;&nbsp;By simulating this queue management system, we can observe the behavior of clients and queues, track the total time spent by clients in the queues, and calculate the average waiting time. This model allows us to analyze different scenarios, test various strategies, and ultimately make informed decisions to optimize the system's performance.

&nbsp;&nbsp;&nbsp;&nbsp;Through a well-structured simulation model, we can identify the factors impacting waiting time and explore various optimization strategies to enhance the system's efficiency, this data being presented by the average waiting time and the average service time at the end of every simulation log in the GUI of the application.

## Design
![UML Diagram](https://github.com/vladvadean/Concurrent-Shopper-A-Multithreaded-Store-Simulation/assets/126804850/3276bbc5-cd8d-41a1-92a0-bb3754548d8d)


## Implementation

&nbsp;&nbsp;&nbsp;&nbsp;The SimulationFrame class, a crucial component of the GUI package, is responsible for managing the graphical interface of the assignment. It serves as the bridge between the user inputs and the underlying simulation logic by interacting with the SimulationManager class. The SimulationManager generates a list of clients that adhere to the assignment's requirements based on the inputs provided by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Upon initiation, the SimulationFrame class creates a single thread that spawns a new Scheduler object. The Scheduler is responsible for orchestrating the queue management system and launches N threads of the Server type, each representing a queue with an associated processor. The Scheduler dynamically allocates clients to servers based on the chosen policy, aiming to minimize waiting times.

&nbsp;&nbsp;&nbsp;&nbsp;Two distinct policies, implemented as the ConcreteStrategyTime class and the ConcreteStrategyQueue class, dictate the allocation of tasks to servers. The policy selection occurs during the Scheduler class's constructor, allowing for flexibility in simulating different strategies. The addTask method, a synchronized method central to the assignment, is implemented in both strategy classes to ensure consistency in their behavior while allowing for the unique characteristics of each policy.

&nbsp;&nbsp;&nbsp;&nbsp;As the simulation progresses, the addTask method's output is displayed in real-time in the GUI's text box. To achieve this, the print stream is redirected to the corresponding text box, ensuring that users can monitor the simulation's progress and visualize the interactions between clients and servers.

&nbsp;&nbsp;&nbsp;&nbsp;To synchronize all threads with the main thread, the system employs a busy-wait mechanism with a one-second delay. This synchronization step is critical for maintaining the correct order of events and ensuring accurate calculations of waiting times and other performance metrics. As the simulation unfolds, the current simulation time, represented in seconds, is incremented accordingly.

&nbsp;&nbsp;&nbsp;&nbsp;Throughout the simulation, users can observe various performance indicators, such as the number of clients in each queue, the waiting times for individual clients, and the overall utilization of the servers. These metrics provide valuable insights into the effectiveness of the chosen policy and highlight potential areas for improvement.

&nbsp;&nbsp;&nbsp;&nbsp;Once the simulation reaches its completion, the system generates a comprehensive report that includes the peak time, average waiting time, and average service time of the completed tasks. By analyzing this report, users can draw conclusions on the effectiveness of the chosen policy in minimizing waiting times and optimizing the queue management system.

&nbsp;&nbsp;&nbsp;&nbsp;Furthermore, the SimulationFrame class offers users the ability to customize the simulation's parameters, such as the number of clients, the arrival times, and the service times. By adjusting these parameters, users can explore various scenarios and evaluate the performance of different policies under a wide range of conditions.
```java
 public static class CustomOutputStream extends OutputStream { 
	 private final JTextArea textArea;  
	 public CustomOutputStream(JTextArea textArea) { 
		 this.textArea = textArea; 
	 } 
	 @Override public void write(int b) { 
		 textArea.append(String._valueOf_((char) b)); 
		 textArea.setCaretPosition(textArea.getDocument().getLength()); 
	 }  
  }
```
_the class needs to be redirected to the log of events_
```java
@Override public void addTask(List<Server> servers, Task t) {
	 int min = Integer._MAX_VALUE_;  
	 int id = 0;  
	 for(Server i : servers) 
		 if(i.getWaitingPeriod().get() < min) {  
			 min = i.getWaitingPeriod().get(); 
			 id = i.getId(); 
		 } 
	 for(Server i : servers) { 
		 if(i.getId() == id) {  
			 i.addTask(t);
		 }  
    }  
}
```

_the addTask method from the ConcreteStrategyTime class_
```java
@Override public void run() { 
	while (!Thread._currentThread_().isInterrupted()) { 
		try {  
		  Thread._sleep_(1000); 
		} 
		catch (InterruptedException e) {  
			_exit_(-1); 
		} 
		if (tasks.size() > 0) { 
			tasks.element().setServiceTime(tasks.element().getServiceTime() - 1); 
			waitingPeriod.decrementAndGet();  
		    if (tasks.element().getServiceTime() == 0) { 
			    try { 
				    tasks.take(); 
				} 
				catch (InterruptedException e) { 
				return; 
				}  
		    }  
		}  
	}  
}
```

_the run method used by the Server threads_
```java
@Override public void run() { 
	int currentTime = 1;  
	   int peakHour = 0;  
	   int peakValue = Integer._MIN_VALUE_;  
	   float avgWaitingTime = 0;  
	   float avgServiceTime = 0;  
	   int clientsServed = 0;  
	   while (!(currentTime >= timeLimit || generatedTasks.isEmpty())) { 
	    while (!generatedTasks.isEmpty()) { 
		    if (generatedTasks.get(0).getArrivalTime() <= currentTime) { 	
			    try { 
				    scheduler.dispatchTask(generatedTasks.get(0)); 	
				    avgServiceTime += generatedTasks.get(0).getServiceTime(); generatedTasks.remove(0); clientsServed++; 
				} 
				catch (InterruptedException e) {  
					_exit_(-1); 
				}  
			 } 
			 else 
				 break; 
		 }  
	   avgWaitingTime += generatedTasks.size(); System._out_.println("time: " + currentTime);  
	   int sumTasks = 0;  
	   for (Server : scheduler.getServers()) {  
		   sumTasks += server.getTasks().size(); System._out_.print("Server " + server.getId() + " waitingTime:" + server.getWaitingPeriod() + " current tasks: ");  
		   for (Task t : server.getTasks()) {  
			   System._out_.print(" " + t); 
		   }  
		   System._out_.println(); 
	   } 
	   if (sumTasks > peakValue) {  
		   peakValue = sumTasks; peakHour = currentTime; 
	   }  
	   currentTime++;  
	   try {  
		   Thread._sleep_(1000); 
	   } 
	   catch (InterruptedException e) {  
		   _exit_(-1); 
	   }  
	}  
	avgWaitingTime /= clientsServed; avgServiceTime /= clientsServed;
	System._out_.println("peak time: " + peakHour + " with " + peakValue + " running tasks");
	System._out_.printf("average waiting time for a task to be inserted in a server: %.2f\n", avgWaitingTime);
	System._out_.printf("average service time to complete a task: %.2f\n", avgServiceTime); 
}
```
_the run method used by the main SimulationManager thread_

## Results

&nbsp;&nbsp;&nbsp;&nbsp;The outcomes of each simulation can be observed at the end of the log box, displaying the peak time, average waiting time, and average service time of the completed tasks. Since clients are randomly generated, the results may vary slightly between runs; however, these small differences should not significantly impact the overall outcomes.

&nbsp;&nbsp;&nbsp;&nbsp;The policy for selecting the server to which new tasks are added is the primary factor causing differences in results, particularly when there is a large number of clients and an extended simulation time. By comparing the results of various policies under different scenarios, we can identify the most effective strategies for minimizing waiting time and optimizing the queue management system. As part of the assignment's analysis, this section highlights the importance of choosing the appropriate policy to achieve the desired performance in real-world applications.

## Conclusions

&nbsp;&nbsp;&nbsp;&nbsp;In conclusion, this assignment serves as an invaluable exercise for understanding and practicing the use of threads and their synchronization in Java. While the busy-waiting approach employed for the Server threads in this simulation may not be the most efficient method in a real-world environment, it effectively demonstrates the core concepts and principles required for managing concurrency in practical applications.

&nbsp;&nbsp;&nbsp;&nbsp;By working through this assignment, learners gain hands-on experience in developing multi-threaded applications, exploring various synchronization techniques, and observing their impact on overall performance. The lessons learned in this exercise, such as the importance of carefully selecting the appropriate synchronization method, can be directly applied to real-world scenarios, where efficient and responsive applications are crucial for success.

## Bibliography

Oracle Java Tutorials - Threads:
https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html
Oracle Java Tutorials - Synchronization:
https://docs.oracle.com/javase/tutorial/essential/concurrency/sync.html
Java Concurrency Utilities - java.util.concurrent package:
https://docs.oracle.com/javase/8/docs/api/java.base/java/util/concurrent/package-summary.html
Oracle Java Tutorials - Executor Interfaces:
https://docs.oracle.com/javase/tutorial/essential/concurrency/executors.html
Stack Overflow - Thread and Synchronization questions:
https://stackoverflow.com/questions/tagged/java+multithreading+synchronization
Queue Management Systems - Research Papers:
https://www.semanticscholar.org/topic/queue-management-system/1990329
