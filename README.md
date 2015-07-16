# AOS-Project-Broadcast-Service
Project Description:

In this project, you have to design and implement a broadcast service in a distributed system.
Assume a distributed system in which nodes are arranged in a certain topology (specified in a
configuration file). Build a spanning tree using a distributed algorithm of your choice. (Feel free
to design your own algorithm.) Once the spanning tree construction completes, each node should
know which subset of its neighbors are also its tree neighbors.

Use the spanning tree constructed above to implement a broadcast service that allows any node
to send a message to all nodes in the system. The broadcast service should inform the source node
of the completion of the broadcast operation. For this project, assume that only one broadcast
operation can occur at any time.

Output: Each node should print its set of tree neighbors. Each node should also output any
broadcast message it sends or receives.

Steps to Run:
1. Caller.java is the main class from which I am initiating all the processes.
   Run Caller.java for each node in the system by passing node number as argument in the command line.
   Ex: java Caller 1

2. Once all the nodes are active you will start to see their activity on their window.
   After the spanning tree is created you will be prompted to enter the broadcast message.
   Once the message is entered it is broadcasted to all other nodes.
   Please note that only one node can broadcast at a time.
3. After completion of broadcast, convergecast kicks inn.
   All the parent nodes will receive the acknowledgement message in the form of DONE message to imply the     completion of broadcast.
