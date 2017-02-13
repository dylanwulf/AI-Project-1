import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MapSolver {
    
    public static void main(String[] args) throws IOException, FileNotFoundException {
        String mapStr = readMap(args[0]);
        MapProblemNode m = new MapProblemNode(mapStr);
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
