import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class MapSolver {
    
    public static void main(String[] args) throws IOException, FileNotFoundException {
        String mapStr = readMap(args[0]);
        MapProblemNode m = new MapProblemNode(mapStr);
        BFSearch bfs = new BFSearch(m);
        System.out.println("BFS:");
        LinkedList<int[]> path = bfs.search();
        if (path == null) 
            System.out.println("No path found!");
        else
            for (int[] p : path) {
                System.out.print(p[0] + ", " + p[1]);
                System.out.println();
            }
        System.out.println("DFS:");
        MapProblemNode m2 = new MapProblemNode(mapStr);
        DFSearch dfs = new DFSearch(m2);
        LinkedList<int[]> dfspath = dfs.search();
        if (dfspath == null)
            System.out.println("No path found!");
        else
            for (int[] p : dfspath) {
                System.out.print(p[0] + ", " + p[1]);
                System.out.println();
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
