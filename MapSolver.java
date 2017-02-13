import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class MapSolver {
    
    public static void main(String[] args) throws IOException, FileNotFoundException {
        String mapStr = readMap(args[0]);
        MapProblemNode m = new MapProblemNode(mapStr, new MapHeuristicStraightLine());
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
        MapProblemNode m2 = new MapProblemNode(mapStr, new MapHeuristicStraightLine());
        DFSearch dfs = new DFSearch(m2);
        MapProblemNode dfsSolution = (MapProblemNode) dfs.search();
        if (dfsSolution == null)
            System.out.println("No path found!");
        else
            for (String p : dfsSolution.getPath()) {
                System.out.println(p);
            }
    }

    private static String readMap(String filePath) throws IOException, FileNotFoundException{
        String map = "";
        FileReader fr = new FileReader(filePath);
        boolean read = true;
        while (read) {
            int nextChar = fr.read();
            if (nextChar == -1) read = false;
            else map += (char)nextChar;
        }
        return map;
    }
}
