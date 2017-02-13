public class MapHeuristicStraightLine implements MapHeuristic {

    public int compareNodes(MapProblemNode a, MapProblemNode b) {
       int[] goalLoc = MapProblemNode.getGoalLocation();
       int[] aLoc = a.getLocation();
       int[] bLoc = b.getLocation();
       double aGoalDistance = Math.sqrt(Math.pow(goalLoc[0] - aLoc[0], 2) + Math.pow(goalLoc[1] - aLoc[1], 2));
       double bGoalDistance = Math.sqrt(Math.pow(goalLoc[0] - bLoc[0], 2) + Math.pow(goalLoc[1] - bLoc[1], 2));
       return Double.compare(aGoalDistance, bGoalDistance);
    }
}
