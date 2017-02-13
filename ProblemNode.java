import java.util.LinkedList;

//Interface for ProblemNode objects, which describe 
//a specific node in a problem tree.
public interface ProblemNode extends Comparable<ProblemNode> {

    public LinkedList<ProblemNode> getChildNodes();
    public boolean isGoalNode();
    public LinkedList<String> getPath();
}
