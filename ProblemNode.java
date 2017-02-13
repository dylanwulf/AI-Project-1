import java.util.LinkedList;

public interface ProblemNode extends Comparable<ProblemNode> {
    public LinkedList<ProblemNode> getChildNodes();
    public boolean isGoalNode();
    public LinkedList<String> getPath();

}
