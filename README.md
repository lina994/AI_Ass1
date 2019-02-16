# Environment simulator and agents for the Hurricane Evacuation Problem

Environment simulator that runs a path optimization problem. We are given a weighted graph, and the goal is to rescue as many people as possible before a given deadline.

However, unlike standard shortest path problems in graphs, which have easy known efficient solution methods (e.g. the Dijkstra algorithm), here the problem is that there are more than 2 vertices to visit, their order is not given, and even the number of visited vertices is not known in advance. 

This is a problem encountered in many real-world settings, such as when you are trying to evacuate people who are stuck at home with no transportation before the hurricane arrives.

Using the following principles:

* Artificial Intelligence:
    * Heuristic static evaluation function
    * Greedy search , A\*  and Realtime A\*  search agents
* Dijkstra algorithm
* Minimum Heap
* OOP, Files I/O

## Hurricane Evacuation problem environment

The environment consists of a weighted unidrected graph. Each vertex may contain a number of people to be evacuated, or a hurricane shelter.

An agent at a vertex automatically picks up all the people at this vertex just before starting the next move, unless the vertex contains a hurricane shelter, in which case everybody in the vehicle is dropped off at the shelter (goal). 

It is also possible for edges (roads) to be blocked (all edges are initially unblocked).

An agent can only do no-op (taking 1 time unit) or traverse actions. The time for traverse actions is equal to:

    w(1+Kp)

where:
* w is the edge weight
* p is the number of people in the vehicle
* K is global non-negative constant, determining how much the vehicle is slowed due to load.

The simulator run each agent in turn, performing the actions retured by the agents, and update the world accordingly. The action always succeeds, unless the time limit is breached.


### Agents types

#### Simple non-AI agents:

* A human agent (read the next move from the user)
* A greedy agent
    * The agent should compute the shortest currently unblocked path to the next vertex with people to be rescued, or to a shelter if it is carrying people, and try to follow it. If there is no such path, do no-op.
* A vandal agent (blocks roads).
    * It does V no-ops, and then blocks the lowest-cost edge adjacent to its current vertex (takes 1 time unit). Then it traverses a lowest-cost remaining edge. Prefer the lowest-numbered node in case of ties. If there is no edge to block or traverse, do no-op.

#### Intelligent agents (Search agents that use a heuristic evaluation function):

* Greedy search agent
    * Picks the move with best immediate heuristic value to expand next.
*  A\* search agent
    * With the same heuristic.
* Simplified version of real-time A\* agent.

#### Performance of AI agents

    P = f * S + N

* S is the agent's score
* N is the number of search expansion steps performed by the search algorithm.
* f is a weight constant.


#### Bonus mode

Search agent and one vandal agent also acting in the environment. The search agent  take this into account.


## Running
### Input
file.txt file include graph description, people locations and deadline.
For example:

    #T 4             ; Number of vertices n in graph (from 1 to n)
    #E 1 2 W1        ; Edge from vertex 1 to vertex 2, weight 1
    #E 3 4 W1        ; Edge from vertex 3 to vertex 4, weight 1
    #E 2 3 W1        ; Edge from vertex 2 to vertex 3, weight 1
    #E 1 3 W4        ; Edge from vertex 1 to vertex 3, weight 4
    #E 2 4 W5        ; Edge from vertex 2 to vertex 4, weight 5
    #V 2 P 1         ; Vertex 2 initially contains 1 person to be rescued
    #V 1 S           ; Vertex 1 contains a hurricane shelter
    #V 4 P 2         ; Vertex 4 initially contains 2 persons to be rescued
    #D 10            ; Deadline is at time 10
<br>

### Graph visualization

![graph](https://github.com/lina994/AI_Ass1/blob/master/resources/input_example.png?raw=true "graph")
<br>

Additional input will be provided by the user via the terminal:

* number of agents
* types of agents
* initial positions of agents
* parameter k
* parameter f
* mode

For example:

    Please enter number of agents
    > 2
    Please enter the type of agent 1:
     -For HUMAN agent press 1
     -For GREEDY agent press 2
     -For VANDAL agent press 3
     -For GREEDY-SEARCH agent press 4
     -For A-STAR agent press 5
     -For REAL-TIME-A-STAR agent press 6
    > 4
    Please enter the initial position of agent 1 (natural number between 1-4)
    > 1
    Please enter the type of agent 2:
     -For HUMAN agent press 1
     -For GREEDY agent press 2
     -For VANDAL agent press 3
     -For GREEDY-SEARCH agent press 4
     -For A-STAR agent press 5
     -For REAL-TIME-A-STAR agent press 6
    > 2
    Please enter the initial position of agent 2 (natural number between 1-4)
    > 1
    Please enter the parameter k
    > 1
    Please enter the parameter f
    > 1

### Output

* Detailed output will be displayed in the terminal
* A summary will be saved in the results.txt  file


## Authors

* Alina
    * [github](https://github.com/lina994 "github")
* Elina
    * [github](https://github.com/ElinaS21 "github")


## Official assignment description
[assignment 1](https://www.cs.bgu.ac.il/~shimony/AI2019/AIass1.html "assignment description")





