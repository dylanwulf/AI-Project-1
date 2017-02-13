Dylan Wulf  
CSC380: Artificial Intelligence  
Project 1: Searching  
February 12, 2017  

Files:  
MapSolver.java: contains main method; reads file and initiates search  
SearchStrategy.java: Simple interface describes search strategy class layout
AStarSearch.java: Contains instructions for A* search algorithm  
BFSearch.java: Contains instructions for breadth-first search algorithm  
DFSearch.java: Contains instructions for depth-first search algorithm  
MapHeuristic.java: Interface for A* Heuristic class  
MapHeuristicStraightLine.java: Straight line heuristic for A* search strategy  
MapHeuristicTaxiDistance.java: Taxi distance heuristic for A* search strategy
ProblemNode.java: Interface for a single node in the problem search tree  
MapProblemNode.java: Describes a single node in the map problem search tree  

Building:  
To build this program, simply run the following command  
javac MapSolver.java  
The Java compiler should automatically compile all the other files as required.  
A warning will appear about unchecked or unsafe operations; this can be ignored.

Running:  
This program is run using a command like this  
java MapSolver path/to/map.txt search-strategy  
where path/to/map.txt is a relative or absolute path to the input map file,  
and search strategy is one of the following: BFS, DFS, A*-straight-line, A*-taxi-distance  
BFS stands for breadth-first search
DFS stands for depth-first search
A*-straight-line is the A* algorithm with straight line heuristic
A*-taxi-distance is the A* algorithm with taxi distance heuristic
Example: java MapSolver maps/map1.txt A*-straight-line

This program was tested on a Linux system using Java version 1.8.0_121
