import java.io.*;
import java.util.*;
public class Question1 {
    private static final int MAX_INT_VALUE = 999999;
    public static void main(String[] args) {
        HashMap<String, Edge> firstGraph = new HashMap<String, Edge>();
        int numberOfNodesG1 = input("g1.txt", firstGraph);
        int firstGraphComputation = computeFloydWarshallAlgorithm(firstGraph, numberOfNodesG1);
        System.out.println(firstGraphComputation);
        HashMap<String, Edge> secondGraph = new HashMap<String, Edge>();
        int numberOfNodesG2 = input("g2.txt", secondGraph);
        int secondGraphComputation = computeFloydWarshallAlgorithm(secondGraph, numberOfNodesG2);
        System.out.println(secondGraphComputation);
        HashMap<String, Edge> thirdGraph = new HashMap<String, Edge>();
        int numberOfNodesG3 = input("g3.txt", thirdGraph);
        int thirdGraphComputation = computeFloydWarshallAlgorithm(thirdGraph, numberOfNodesG3);
        System.out.println(thirdGraphComputation);
    }
    private static int computeFloydWarshallAlgorithm(HashMap<String, Edge> graph, int numberOfNodes) {
    	int shortestPathLength = 0;
        int[][][] A = new int[numberOfNodes][numberOfNodes][numberOfNodes];
        for(int i = 0; i < numberOfNodes; i++) {
            for(int j = 0; j < numberOfNodes; j++) {
                for(int k = 0; k < numberOfNodes; k++) {
                    if(k == 0) {
                        if(i == j) {
                            A[i][j][0] = 0;
                        }
                        else {
                            String key = i + "|" + j;
                            Edge edge = graph.get(key);
                   			if(edge != null) {
                                A[i][j][0] = edge.getLength();
                            }
                            else {
                                A[i][j][0] = MAX_INT_VALUE;
                            }
                        }
                    }
                    else {
                        if(i == j) {
                            A[i][j][k] = 0;
                        }
                        else {
                            A[i][j][k] = MAX_INT_VALUE;
                        }
                    }
                }
            }
        }
        for(int k = 1; k < numberOfNodes; k++) {  
            for(int i = 0; i < numberOfNodes; i++) {
                for(int j = 0; j < numberOfNodes; j++) {
                    int firstItem = A[i][j][k - 1];
                    int secondItem = A[i][k][k - 1] + A[k][j][k - 1];
                    A[i][j][k] = Math.min(firstItem, secondItem);
                }
            }
        }
        boolean negativeCycles = false;
        for(int i = 0; i < numberOfNodes; i++) {  
            if(A[i][i][numberOfNodes - 1] < 0) {  
                negativeCycles = true;
                break;  
            }  
        } 
        if(!negativeCycles) {
            for(int i = 0; i < numberOfNodes; i++) {
                for(int j = 0; j < numberOfNodes; j++) {
                    shortestPathLength = Math.min(shortestPathLength, A[i][j][numberOfNodes - 1]);
                }
            }
        }
        else {
            shortestPathLength = -1;
        }
        return shortestPathLength;
    }
    private static int input(String path, HashMap<String, Edge> graph) {
        int numberOfNodes = 0;
        try {
            FileInputStream file = new FileInputStream(path);
            DataInputStream dis = new DataInputStream(file);
            BufferedReader br =  new BufferedReader(new InputStreamReader(dis));
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            numberOfNodes = Integer.parseInt(tokenizer.nextToken());
            int numberOfVertex = Integer.parseInt(tokenizer.nextToken());
            int tailNode;
            int headNode;
            int length;
            String str;
            String key;
            Edge edge;
            while((str=br.readLine())!=null) {
                tokenizer = new StringTokenizer(str);
                tailNode = Integer.parseInt(tokenizer.nextToken());
                headNode = Integer.parseInt(tokenizer.nextToken());
                length = Integer.parseInt(tokenizer.nextToken());
                key = headNode + "|" + tailNode;
                edge = new Edge(headNode, tailNode, length);
                graph.put(key, edge);
            }
        }
        catch(Exception ex) {
        	System.out.println(ex);
        }
        return numberOfNodes;
    }
}