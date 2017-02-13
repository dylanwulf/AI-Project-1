//This is the Taxi Distance heuristic, used in the AStarSearch strategy. 
//This heuristic sorts the priority queue by which node is closest to the goal
//in taxicab distance (also known as city block distance, Manhatten distance, snake distance, etc)
//while also taking into account the current path cost.
//Simply calculates (x2-x1) + (y2-y1). It seems similar to the straight line distance, but can
//give some pretty different results.
public class MapHeuristicTaxiDistance implements MapHeuristic {

    public int compareNodes(MapProblemNode a, MapProblemNode b) {
       int[] goalLoc = MapProblemNode.getGoalLocation();
       int[] aLoc = a.getLocation();
       int[] bLoc = b.getLocation();
       int aGeoDistance = (goalLoc[0] - aLoc[0]) + (goalLoc[1] - aLoc[1]);
       int bGeoDistance = (goalLoc[0] - bLoc[0]) + (goalLoc[1] - bLoc[1]);
       return Integer.compare(aGeoDistance + a.getPathCost(), bGeoDistance + b.getPathCost());
    }
}
