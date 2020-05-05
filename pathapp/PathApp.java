/*Project #3
 *Source code file: PathApp.java
 *Programmer: Rebecca Carbone
 *Due: 11/26/2019
 *Description: PathApp class allows the user to make changes to a Graph and/or
 * save/load Graph to/from a file
*/
package pathapp;

import java.util.Scanner;
import java.io.*;

public class PathApp {

    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');     // 0  (start)
        theGraph.addVertex('B');     // 1
        theGraph.addVertex('C');     // 2
        theGraph.addVertex('D');     // 3
        theGraph.addVertex('E');     // 4

        theGraph.addEdge(0, 1, 50);  // AB 50
        theGraph.addEdge(0, 3, 80);  // AD 80
        theGraph.addEdge(1, 2, 60);  // BC 60
        theGraph.addEdge(1, 3, 90);  // BD 90
        theGraph.addEdge(2, 4, 40);  // CE 40
        theGraph.addEdge(3, 2, 20);  // DC 20
        theGraph.addEdge(3, 4, 70);  // DE 70
        theGraph.addEdge(4, 1, 50);  // EB 50

        System.out.println("Shortest paths");
        theGraph.path();             // shortest paths
        System.out.println();

        //create UserInput object and run the menu
        UserInput UI = new UserInput();
        char choice = 'B';
        while (choice != 'Q') {
            System.out.println("C - change weight of an edge");
            System.out.println("A - add an edge");
            System.out.println("D - delete an edge");
            System.out.println("F - find a path");
            System.out.println("W - write graph to file");
            System.out.println("R - read graph from file");
            System.out.println("Q - quit");
            choice = UI.getChar("choice");

            switch (choice) {
                case 'C':
                    change(theGraph, UI);
                    break;
                case 'A':
                    add(theGraph, UI);
                    break;
                case 'D':
                    delete(theGraph, UI);
                    break;
                case 'F':
                    find(theGraph, UI);
                    break;
                case 'W':
                    writeGraph(theGraph, UI);
                    break;
                case 'R':
                    theGraph = readGraph(theGraph, UI);
                    break;
            }
        }
    }  // end main()

    /**
     * Method to change the weight of an edge on the graph. Does not matter if
     * the edge already exists
     * @param theGraph
     * @param UI 
     */
    public static void change(Graph theGraph, UserInput UI) {
        
        //Collect user input for to/from vertex
        System.out.println("Enter FROM vertex (A, B, C, D, or E)");
        char tempFrom = UI.getChar("vertex");
        System.out.println("Enter a TO vertex (A, B, C, D, or E)");
        char tempTo = UI.getChar("vertex");

        //convert vertexs to int
        int from = convertToInt(tempFrom);
        int to = convertToInt(tempTo);

        System.out.println("Enter new weight");
        int weight = UI.getInt();

        //add Edge to graph and readjust sPath array
        theGraph.addEdge(from, to, weight);
        readjust(theGraph);
    }

    /**
     * Method to convert char representing a vertex to corresponding integer value
     * @param vertex
     * @return 
     */
    public static int convertToInt(char vertex) {
        int num = 0;
        switch (vertex) {
            case 'A':
                num = 0;
                break;
            case 'B':
                num = 1;
                break;
            case 'C':
                num = 2;
                break;
            case 'D':
                num = 3;
                break;
            case 'E':
                num = 4;
                break;
        }

        return num;
    }

    /**
     * Method to convert integer value to corresponding char vertex
     * @param num
     * @return 
     */
    public static char convertToChar(int num) {
        char vertex = 'A';
        switch (num) {
            case 0:
                vertex = 'A';
                break;
            case 1:
                vertex = 'B';
                break;
            case 2:
                vertex = 'C';
                break;
            case 3:
                vertex = 'D';
                break;
            case 4:
                vertex = 'E';
                break;
        }

        return vertex;
    }

    /**
     * Method to find and display full path to a given vertex
     * @param theGraph
     * @param UI 
     */
    public static void find(Graph theGraph, UserInput UI) {
        //Get user input and convert to integer value
        System.out.println("Enter vertex to find(A, B, C, D, or E)");
        char tempFrom = UI.getChar("vertex");
        int from = convertToInt(tempFrom);
        int[] path = theGraph.displayFullPath(from);    //array of vertexs (int values)   
        for (int i = 0; i < path.length; i++) {
            //check whether path[i] is the final edge so it doesnt print an extra arrow
            if (i != path.length - 1) {
                System.out.print(convertToChar(path[i]) + " -> ");
            } else {
                System.out.println(convertToChar(path[i]));
            }
        }
        System.out.println();
        readjust(theGraph);
    }

    /**
     * Method to add an edge to the graph provided the edge does not already exist
     * @param theGraph
     * @param UI 
     */
    public static void add(Graph theGraph, UserInput UI) {
        //collect user input for to/from vertex
        System.out.println("Enter FROM vertex (A, B, C, D, or E)");
        char tempFrom = UI.getChar("vertex");
        System.out.println("Enter a TO vertex (A, B, C, D, or E)");
        char tempTo = UI.getChar("vertex");

        //convert to int values and check if edge exists
        int from = convertToInt(tempFrom);
        int to = convertToInt(tempTo);
        if (!theGraph.edgeExists(from, to)) {
            //if it does not exists get user input for weight and add edge to graph
            System.out.println("Enter weight");
            int weight = UI.getInt();

            theGraph.addEdge(from, to, weight);
            readjust(theGraph);
        } else {
            System.out.println("Edge already exists");
        }
    }

    /**
     * Method to delete an edge from the graph provided the edge exists
     * @param theGraph
     * @param UI 
     */
    public static void delete(Graph theGraph, UserInput UI) {
        //collect user input for to/from vertex
        System.out.println("Enter FROM vertex (A, B, C, D, or E)");
        char tempFrom = UI.getChar("vertex");
        System.out.println("Enter a TO vertex (A, B, C, D, or E)");
        char tempTo = UI.getChar("vertex");

        //convert to int values and check that the edge exists
        int from = convertToInt(tempFrom);
        int to = convertToInt(tempTo);
        if (theGraph.edgeExists(from, to)) {
            //if it exists add an edge with weight 1000000 (weight of an edge that DNE)
            theGraph.addEdge(from, to, 1000000);
            readjust(theGraph);
        } else {
            System.out.println("Edge does not exist");
        }
    }

    /**
     * Prints the shortest path array of the Graph 
     * @param theGraph 
     */
    public static void readjust(Graph theGraph) {
        System.out.println("Shortest paths");
        theGraph.path();             // shortest paths
        System.out.println();
    }

    /**
     * Method to write the graph to a disk file
     * @param theGraph
     * @param UI 
     */
    public static void writeGraph(Graph theGraph, UserInput UI) {
        //get user input for the file name
        System.out.println("Enter desired file name: ");
        String fileName = UI.getFileName();
        
        try {
            //use fileoutput stream to write the graph to the file
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(theGraph);
            objectOut.flush();
            objectOut.close();
        } catch (Exception e) {
            System.out.println("Error writing graph to file");
        }
    }

    /**
     * Read the Graph from a file if the file exists
     * @param theGraph
     * @param UI
     * @return 
     */
    public static Graph readGraph(Graph theGraph, UserInput UI) {
        //get user input for the file name
        System.out.println("Enter desired file name: ");
        String fileName = UI.getFileName();
        
        try {
            //use fileinputstream to read the graph from the file
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Graph newGraph = (Graph) objectIn.readObject();
            objectIn.close();
            //save the graph from the file to theGraph object
            theGraph = newGraph;
            readjust(theGraph);
        } catch (Exception e) {
            System.out.println("Error reading graph from file");
        }
        //return theGraph whether it is replaced with one from the file or not
        return theGraph;
    }
}  // end class PathApp
////////////////////////////////////////////////////////////////

