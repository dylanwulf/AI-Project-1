//Dylan Wulf
//Artificial Intelligence Project 1
//Feb 12, 2017

import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class MapSolver {
    
    public static void main(String[] args) {
        //Check for enough arguments
        if (args.length < 2) {
            System.err.println("Not enough arguments.");
            System.err.println("Usage: java MapSolver path/to/file.ext search-strategy");
            System.err.println("Exiting.");
            System.exit(1);
        }

        //Read in the file and save it as a string
        //Check for thrown exceptions such as file not found and ioexception
        String mapStr = "";
        try {
            mapStr = readMap(args[0]);
        }
        catch (FileNotFoundException e) {
            System.err.println("Could not find file " + args[0] + ". Exiting.");
            System.exit(1);
        }
        catch (IOException e) {
            System.err.print("Something went wrong when reading the file. ");
            System.err.println("Please make sure you have permission to read the file.");
            System.err.println("Exiting.");
            System.exit(1);
        }

        //Get search strategy object using argument and map
        SearchStrategy strategy = getSearchStrategy(args[1], mapStr);
        if (strategy == null) {
            System.err.println("Unknown search strategy.");
            System.err.println("Possible strategies: BFS, DFS, A*-straight-line, A*-taxi-distance");
            System.err.println("Exiting.");
            System.exit(1);
        }

        //Search for a solution and record time taken
        long startTime = System.nanoTime();

        MapProblemNode goalNode = (MapProblemNode) strategy.search();

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        if (goalNode == null) {
            System.out.println("No solution found.");
        }
        else {
            int startX = MapProblemNode.getStartLocation()[0];
            int startY = MapProblemNode.getStartLocation()[1];
            System.out.println("Solution:");
            for (String step : goalNode.getPath())
                System.out.println(step);
            System.out.println("Path cost: " + goalNode.getPathCost());
        }

        System.out.println("Time taken: " + timeTaken + " nanoseconds");
        System.out.println("Nodes created: " + MapProblemNode.getCreatedNodes());
    }

    //Takes in the search strategy argument and the string representation of the map
    //and creates a search strategy object. If the command line argument does not match
    //any known strategy, returns null. 
    private static SearchStrategy getSearchStrategy(String strategyName, String mapStr) {
        if (strategyName.equalsIgnoreCase("BFS"))
            return new BFSearch(new MapProblemNode(mapStr, null));
        else if (strategyName.equalsIgnoreCase("DFS"))
            return new DFSearch(new MapProblemNode(mapStr, null));
        else if (strategyName.equalsIgnoreCase("A*-straight-line"))
            return new AStarSearch(new MapProblemNode(mapStr, new MapHeuristicStraightLine()));
        else if (strategyName.equalsIgnoreCase("A*-taxi-distance"))
            return new AStarSearch(new MapProblemNode(mapStr, new MapHeuristicTaxiDistance()));
        else
            return null;
    }

    //Reads the file specified in the command line arguments into a String.
    private static String readMap(String filePath) throws IOException, FileNotFoundException{
        File mapFile = new File(filePath);
        long fileSize = mapFile.length(); //get file size
        FileReader fr = new FileReader(mapFile);
        char[] map = new char[(int)fileSize]; //make array that's the same size as the file
        int currentChar = 0;
        while (currentChar < map.length) {
            int nextChar = fr.read(); //read char by char
            if (nextChar == -1) currentChar = map.length; //if we hit EOF, exit loop
            else map[currentChar++] = (char) nextChar; //put char into array, then increment
        }
        fr.close();
        return new String(map);
    }
}
