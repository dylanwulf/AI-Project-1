//interface describes format of heuristic class which is used for AStarSearch
public interface MapHeuristic {

    public int compareNodes(MapProblemNode a, MapProblemNode b);

}
