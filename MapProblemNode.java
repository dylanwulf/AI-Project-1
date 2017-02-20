//Dylan Wulf
//Artificial Intelligence Project 1
//Feb 12, 2017

import java.util.LinkedList;

//This class describes a node in a map/maze problem.
public class MapProblemNode implements ProblemNode {

    private static char[][] map; //static representation of the whole map/maze
    private int[] location; //location of this node
    private static int[] startLocation; //static start location in the map
    private static int[] goalLocation; //static goal location in the map
    MapProblemNode parent; //parent node of this node
    private static boolean[][] exploredLocations; //array of locations already explored
    private static long createdNodes; //static counter for number of nodes created
    private int pathCost; //cost of the path described by path
    private MapHeuristic heuristic; //object which contains the heuristic used in compareTo method

    //Public constructor available from outside this class
    public MapProblemNode(String mapStr, MapHeuristic heuristic) {
        //split map string by newline characters
        String[] rows = mapStr.split("\n");

        //get width and height from first line
        int width = Integer.parseInt(rows[0].split(" ")[0].trim());
        int height = Integer.parseInt(rows[0].split(" ")[1].trim());

        //Initialize variables
        createdNodes = 1; //set to one, since this will always be the first one
        parent = null;
        map = new char[width][height];
        exploredLocations = new boolean[width][height];
        location = new int[2];
        startLocation = new int[2];
        goalLocation = new int[2];
        pathCost = 0;
        this.heuristic = heuristic;

        //fill in exploredLocations with all false
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                exploredLocations[x][y] = false;
            }
        }

        //fill in map with the corresponding characters
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[x][y] = rows[y + 1].charAt(x);
                //Set current location to start location
                if (map[x][y] == 's') {
                    location[0] = x;
                    location[1] = y;
                    startLocation[0] = x;
                    startLocation[1] = y;
                }
                if (map[x][y] == 'g') {
                    goalLocation[0] = x;
                    goalLocation[1] = y;
                }
            }
        }
    }

    //Private constructor only available from within this class
    private MapProblemNode(MapProblemNode parent, int[] location, MapHeuristic heuristic) {
        createdNodes++; //increment number of nodes created
        this.parent = parent;
        this.location = location;
        //Clone parent's path and add current location to it.
        //it's only a shallow copy, but that's ok because the objects do not get changed after this.
        char me = map[location[0]][location[1]]; //character at this node's location
        if (me == '.' || me == 's' || me == 'g')
            pathCost = parent.getPathCost() + 1; //period, s, g cost 1
        if (me == ',')
            pathCost = parent.getPathCost() + 2; //comma costs 2
        this.heuristic = heuristic; //make sure it has the same heuristic as its parent
    }

    //Returns a linked list of all the nodes that result from all possible moves
    //from this object's location.
    //Will not move to a location that has been previously explored
    public LinkedList<ProblemNode> getChildNodes() {
        //mark this object's location as explored
        exploredLocations[location[0]][location[1]] = true;
        LinkedList<ProblemNode> childNodes = new LinkedList<ProblemNode>();

        //check left move; if available, make a new node and add it to the list
        if (location[0] - 1 >= 0 && map[location[0]-1][location[1]] != '#' && exploredLocations[location[0]-1][location[1]] == false) {
            int[] childLocation = new int[2];
            childLocation[0] = location[0] - 1;
            childLocation[1] = location[1];
            ProblemNode c = new MapProblemNode(this, childLocation, heuristic);
            childNodes.add(c);
        }

        //check right move; if available, make a new node and add it to the list
        if (location[0] + 1 < map.length && map[location[0]+1][location[1]] != '#' && exploredLocations[location[0]+1][location[1]] == false) {
            int[] childLocation = new int[2];
            childLocation[0] = location[0] + 1;
            childLocation[1] = location[1];
            ProblemNode c = new MapProblemNode(this, childLocation, heuristic);
            childNodes.add(c);
        }

        //check up move; if available, make a new node and add it to the list
        if (location[1] - 1 >= 0 && map[location[0]][location[1]-1] != '#' && exploredLocations[location[0]][location[1]-1] == false) {
            int[] childLocation = new int[2];
            childLocation[0] = location[0];
            childLocation[1] = location[1] - 1;
            ProblemNode c = new MapProblemNode(this, childLocation, heuristic);
            childNodes.add(c);
        }

        //check down move; if available, make a new node and add it to the list
        if (location[1] + 1 < map[0].length && map[location[0]][location[1]+1] != '#' && exploredLocations[location[0]][location[1]+1] == false) {
            int[] childLocation = new int[2];
            childLocation[0] = location[0];
            childLocation[1] = location[1] + 1;
            ProblemNode c = new MapProblemNode(this, childLocation, heuristic);
            childNodes.add(c);
        }

        return childNodes;
    }

    //Checks whether this node's location is the goal
    public boolean isGoalNode() {
        return map[location[0]][location[1]] == 'g';
    }

    //Return the string path from the start to this node's location
    public LinkedList<String> getPath() {
        if (location[0] == startLocation[0] && location[1] == startLocation[1]) {
            LinkedList<String> path = new LinkedList<String>();
            path.add("Start at location " + location[0] + ", " + location[1]);
            return path;
        }
        else {
            LinkedList<String> path = parent.getPath();
            int[] parentLoc = parent.getLocation();
            String direction = "";
            if (parentLoc[0] - 1 == location[0])
                direction = "left";
            else if (parentLoc[0] + 1 == location[0])
                direction = "right";
            else if (parentLoc[1] - 1 == location[1])
                direction = "up";
            else
                direction = "down";
            path.add("Move " + direction + " to " + location[0] + ", " + location[1]);
            return path;
        }
    }

    //Return the total path cost of the path described by the string stored in 'path'
    public int getPathCost() {
        return pathCost;
    }

    //get this node's location in the map
    //creates a new location array so that this object's array doesn't get changed
    public int[] getLocation() {
        int[] loc = new int[2];
        loc[0] = location[0];
        loc[1] = location[1];
        return loc;
    }

    //get the location of the goal in the map
    //creates a new location array so that this object's array doesn't get changed
    public static int[] getGoalLocation() {
        int[] loc = new int[2];
        loc[0] = goalLocation[0];
        loc[1] = goalLocation[1];
        return loc;
    }

    //Get the location of the start in the map
    //creates a new location array so that this object's array doesn't get changed
    public static int[] getStartLocation() {
        int[] loc = new int[2];
        loc[0] = startLocation[0];
        loc[1] = startLocation[1];
        return loc;
    }

    //CompareTo method is used by PriorityQueue in the AStarSearch strategy
    //uses the heuristic that was set in the constructor
    public int compareTo(ProblemNode b) {
        return heuristic.compareNodes(this, (MapProblemNode) b);
    }

    //get the total number of created nodes so far
    public static long getCreatedNodes() {
        return createdNodes;
    }
}
