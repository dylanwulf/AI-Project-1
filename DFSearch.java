//Dylan Wulf
//Artificial Intelligence Project 1
//Feb 12, 2017

import java.util.LinkedList;
import java.util.Stack;

//Depth-first search strategy. Implemented using a stack.
public class DFSearch implements SearchStrategy {
    private Stack<ProblemNode> frontierStack;

    //Constructor
    public DFSearch(ProblemNode rootNode) {
        frontierStack = new Stack<ProblemNode>();
        frontierStack.push(rootNode);
    }

    //Search method returns the first node to be added to the stack which is at the goal location, 
    //or null if no such node is found.
    public ProblemNode search() {
        while (frontierStack.empty() == false) {
            ProblemNode node = frontierStack.pop();
            LinkedList<ProblemNode> nodeChildren = node.getChildNodes();
            for (ProblemNode p : nodeChildren) {
                if (p.isGoalNode()) return p;
                frontierStack.push(p);
            }
        }
        return null;
    }
}
