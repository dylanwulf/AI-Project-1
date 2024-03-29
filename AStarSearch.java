//Dylan Wulf
//Artificial Intelligence Project 1
//Feb 12, 2017

import java.util.LinkedList;
import java.util.PriorityQueue;

//A* search strategy uses a Priority Queue to keep the most "desirable" node at the beginning
//Desirability determined by heuristic + current path cost 
//(See MapHeuristic* classes for exact calculations). 
public class AStarSearch implements SearchStrategy {
    private PriorityQueue<ProblemNode> frontierQueue;

    //Constructor
    public AStarSearch(ProblemNode rootNode) {
        frontierQueue = new PriorityQueue<ProblemNode>();
        frontierQueue.add(rootNode);
    }

    //Search method returns the first node to be added to the priority queue which is
    //at the goal location, or null if no such node is found.
    public ProblemNode search() {
        while (frontierQueue.size() > 0) {
            ProblemNode node = frontierQueue.remove();
            LinkedList<ProblemNode> nodeChildren = node.getChildNodes();
            for (ProblemNode p : nodeChildren) {
                frontierQueue.add(p);
            }
            if (frontierQueue.size() > 0 && frontierQueue.peek().isGoalNode())
                return frontierQueue.remove();
        }
        return null;
    }
}
