import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStarSearch implements SearchStrategy {
    private PriorityQueue<ProblemNode> frontierQueue;

    public AStarSearch(ProblemNode rootNode) {
        frontierQueue = new PriorityQueue<ProblemNode>();
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
