public class MapHeuristicGeoDistance implements MapHeuristic {

    public int compareNodes(MapProblemNode a, MapProblemNode b) {
       int[] goalLoc = MapProblemNode.getGoalLocation();
       int[] aLoc = a.getLocation();
       int[] bLoc = b.getLocation();
       int aGeoDistance = (goalLoc[0] - aLoc[0]) + (goalLoc[1] - aLoc[1]);
       int bGeoDistance = (goalLoc[0] - bLoc[0]) + (goalLoc[1] - bLoc[1]);
       return Integer.compare(aGeoDistance + a.getPathCost(), bGeoDistance + b.getPathCost());
    }
}
