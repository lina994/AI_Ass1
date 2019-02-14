# Environment simulator and agents for the Hurricane Evacuation Problem
Environment simulator that runs a path optimization problem. We are given a weighted graph, and the goal is to rescue as many people as possible before a given deadline.  However, unlike standard shortest path problems in graphs, which have easy known efficient solution methods (e.g. the Dijkstra algorithm), here the problem is that there are more than 2 vertices to visit, their order is not given, and even the number of visited vertices is not known in advance. This is a problem encountered in many real-world settings, such as when you are trying to evacuate people who are stuck at home with no transportation before the hurricane arrives.

## Running
### Input
file.txt file include graph description, people locations and deadline.
For example:

    #T 4                 ; Number of vertices n in graph (from 1 to n)
    #E 1 2 W1        ; Edge from vertex 1 to vertex 2, weight 1
    #E 3 4 W1        ; Edge from vertex 3 to vertex 4, weight 1
    #E 2 3 W1        ; Edge from vertex 2 to vertex 3, weight 1
    #E 1 3 W4        ; Edge from vertex 1 to vertex 3, weight 4
    #E 2 4 W5        ; Edge from vertex 2 to vertex 4, weight 5
    #V 2 P 1           ; Vertex 2 initially contains 1 person to be rescued
    #V 1 S              ; Vertex 1 contains a hurricane shelter
    #V 4 P 2           ; Vertex 4 initially contains 2 persons to be rescued
    #D 10               ; Deadline is at time 10



###Output
results.txt 
