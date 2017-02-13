import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class MapSolver {
    
    public static void main(String[] args) throws IOException, FileNotFoundException {
        String mapStr = readMap(args[0]);
        /*MapProblemNode m = new MapProblemNode(mapStr, null);
        BFSearch bfs = new BFSearch(m);
        System.out.println("BFS:");
        System.out.println("Start at location " + m.getLocation()[0] + ", " + m.getLocation()[1]);
        MapProblemNode bfsSolution = (MapProblemNode) bfs.search();
        if (bfsSolution == null) 
            System.out.println("No path found!");
        else
            for (String p : bfsSolution.getPath()) {
                System.out.println(p);
            }
        System.out.println("DFS:");
        MapProblemNode m2 = new MapProblemNode(mapStr, null);
        DFSearch dfs = new DFSearch(m2);
        MapProblemNode dfsSolution = (MapProblemNode) dfs.search();
        if (dfsSolution == null)
            System.out.println("No path found!");
        else
            for (String p : dfsSolution.getPath()) {
                System.out.println(p);
            }*/

        System.out.println("A*:");
        MapProblemNode m3 = new MapProblemNode(mapStr, new MapHeuristicGeoDistance());
        AStarSearch aStar = new AStarSearch(m3);
        System.out.println("Start at location " + m3.getLocation()[0] + ", " + m3.getLocation()[1]);
        MapProblemNode aStarSolution = (MapProblemNode) aStar.search();
        if (aStarSolution == null) 
            System.out.println("No path found!");
        else
            for (String p : aStarSolution.getPath()) {
                System.out.println(p);
            }
    }

    private static String readMap(String filePath) throws IOException, FileNotFoundException{
        File mapFile = new File(filePath);
        long fileSize = mapFile.length();
        FileReader fr = new FileReader(mapFile);
        char[] map = new char[(int)fileSize];
        int currentChar = 0;
        while (currentChar < map.length) {
            int nextChar = fr.read();
            if (nextChar == -1) currentChar = map.length;
            else map[currentChar++] = (char) nextChar;
        }
        fr.close();
        return new String(map);
    }
}
