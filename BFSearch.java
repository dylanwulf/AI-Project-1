//Dylan Wulf
//Artificial Intelligence Project 1
//Feb 12, 2017

import java.util.LinkedList;
import java.util.Queue;

//Breadth-First Search strategy. Implemented using a Queue.
public class BFSearch implements SearchStrategy {
    private Queue<ProblemNode> frontierQueue;

    //Constructor
    public BFSearch(ProblemNode rootNode) {
        frontierQueue = new LinkedList<ProblemNode>();
        frontierQueue.add(rootNode);
    }

    //Search returns the first node to be added to the queue which is at the goal location,
    //or null if no such node is found.
    public ProblemNode search() {
        while (frontierQueue.size() > 0) {
            ProblemNode node = frontierQueue.remove();
            LinkedList<ProblemNode> nodeChildren = node.getChildNodes();
            for (ProblemNode p : nodeChildren) {
                if (p.isGoalNode()) return p;
                frontierQueue.add(p);
            }
        }
        return null;
    }
}
