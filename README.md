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
