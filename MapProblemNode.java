import java.util.LinkedList;

public class MapProblemNode implements ProblemNode {

    private static char[][] map;
    private int[] location;
    private static int[] startLocation;
    private static int[] goalLocation;
    MapProblemNode parent;
    private static boolean[][] exploredLocations;
    private int pathCost;
    private LinkedList<int[]> path;

    public MapProblemNode(String mapStr) {
        //split map string by newline characters
        String[] rows = mapStr.split("\n");

        //get width and height from first line
        int width = Integer.parseInt(rows[0].split(" ")[0].trim());
        int height = Integer.parseInt(rows[0].split(" ")[1].trim());

        //Initialize variables
        parent = null;
        map = new char[width][height];
        exploredLocations = new boolean[width][height];
        location = new int[2];
        startLocation = new int[2];
        goalLocation = new int[2];
        pathCost = 0;

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

    private MapProblemNode(MapProblemNode parent, int[] location) {
        this.parent = parent;
        this.location = location;
        this.path = (LinkedList<int[]>) parent.getPath().clone();
        this.path.add(this.location);
        char me = map[location[0]][location[1]];
        if (me == '.' || me == 's' || me == 'g')
            pathCost = parent.getPathCost() + 1;
        if (me == ',')
            pathCost = parent.getPathCost() + 2;
    }

    public LinkedList<ProblemNode> getChildNodes() {
        exploredLocations[location[0]][location[1]] = true;
        LinkedList<ProblemNode> childNodes = new LinkedList<ProblemNode>();

        if (location[0] - 1 >= 0 && map[location[0]-1][location[1]] != '#' && exploredLocations[location[0]-1][location[1]] == false) {
            int[] childLocation = new int[2];
            childLocation[0] = location[0] - 1;
            childLocation[1] = location[1];
            ProblemNode c = new MapProblemNode(this, childLocation);
            childNodes.add(c);
        }

        if (location[0] + 1 < map.length && map[location[0]+1][location[1]] != '#' && exploredLocations[location[0]+1][location[1]] == false) {
            int[] childLocation = new int[2];
            childLocation[0] = location[0] + 1;
            childLocation[1] = location[1];
            ProblemNode c = new MapProblemNode(this, childLocation);
            childNodes.add(c);
        }

        return childNodes;
    }

    public boolean isGoalNode() {
        return map[location[0]][location[1]] == 'g';
    }

    public LinkedList<int[]> getPath() {
        return path;
    }

    public int getPathCost() {
        return pathCost;
    }
}
