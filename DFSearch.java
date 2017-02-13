import java.util.LinkedList;
import java.util.Stack;

public class DFSearch {
    private Stack<ProblemNode> frontierStack;

    public DFSearch(ProblemNode rootNode) {
        frontierStack = new Stack<ProblemNode>();
        frontierStack.push(rootNode);
    }

    public LinkedList<int[]> search() {
        while (frontierStack.empty() == false) {
            ProblemNode node = frontierStack.pop();
            LinkedList<ProblemNode> nodeChildren = node.getChildNodes();
            for (ProblemNode p : nodeChildren) {
                if (p.isGoalNode()) return p.getPath();
                frontierStack.push(p);
            }
        }
        return null;
    }
}
