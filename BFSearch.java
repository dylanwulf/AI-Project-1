import java.util.LinkedList;
import java.util.Queue;

public class BFSearch implements SearchStrategy {
    private Queue<ProblemNode> frontierQueue;

    public BFSearch(ProblemNode rootNode) {
        frontierQueue = new LinkedList<ProblemNode>();
        frontierQueue.add(rootNode);
    }

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
