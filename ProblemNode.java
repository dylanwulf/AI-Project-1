//Dylan Wulf
//Artificial Intelligence Project 1
//Feb 12, 2017

import java.util.LinkedList;

//Interface for ProblemNode objects, which describe 
//a specific node in a problem tree.
public interface ProblemNode extends Comparable<ProblemNode> {

    public LinkedList<ProblemNode> getChildNodes();
    public boolean isGoalNode();
    public LinkedList<String> getPath();
    public int getPathCost();
}
