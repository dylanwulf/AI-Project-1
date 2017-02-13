//Dylan Wulf
//Artificial Intelligence Project 1
//Feb 12, 2017

//This is the Straight Line heuristic, used in the AStarSearch strategy.
//This heuristic sorts the priority queue by which node is closest to the goal
//in a straight line, while also taking into account the current path cost.
//Uses the distance formula sqrt((x2-x1)^2 + (y2-y1)^2)
public class MapHeuristicStraightLine implements MapHeuristic {

    public int compareNodes(MapProblemNode a, MapProblemNode b) {
       int[] goalLoc = MapProblemNode.getGoalLocation();
       int[] aLoc = a.getLocation();
       int[] bLoc = b.getLocation();
       double aGoalDistance = Math.sqrt(Math.pow(goalLoc[0] - aLoc[0], 2) + Math.pow(goalLoc[1] - aLoc[1], 2));
       double bGoalDistance = Math.sqrt(Math.pow(goalLoc[0] - bLoc[0], 2) + Math.pow(goalLoc[1] - bLoc[1], 2));
       return Double.compare(aGoalDistance + a.getPathCost(), bGoalDistance + b.getPathCost());
    }
}
