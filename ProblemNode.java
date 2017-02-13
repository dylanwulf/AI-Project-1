import java.util.LinkedList;

public interface ProblemNode {
    public LinkedList<ProblemNode> getChildNodes();
    public boolean isGoalNode();
    public LinkedList<int[]> getPath();

}
