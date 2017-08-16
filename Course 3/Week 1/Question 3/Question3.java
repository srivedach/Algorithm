import java.io.*;
import java.util.*;
public class Question3 
{
    private static int[][] graph = null;
    private static int numberOfNodes = 0;
    private static HashMap<Integer,Node> spanningTree = new HashMap<Integer, Node>();
    public static void main(String[] args) {
       input();
       System.out.println(primsAlgorithm());
    }
    private static int primsAlgorithm()
    {
	int overallCost = 0;
    int position = 0;
    spanningTree.put(0, new Node(0, position));
    int minimumCost = Integer.MAX_VALUE;
	int minimumI = 0;
	int minimumJ = 0;
	while(!allNodesExplored()) {
        for(int i = 0; i < numberOfNodes; i++) { 
            if(isNodeExplored(i)) {
                for(int j = 0; j < numberOfNodes; j++) {
                    if(!isNodeExplored(j)) {
                        if(minimumCost > graph[i][j]) {
                                minimumCost = graph[i][j];
                                minimumI = i;
                                minimumJ = j;
                            }					
                        }
                    }
                }
            }
		spanningTree.put(minimumJ, new Node(minimumJ, position));
        overallCost += graph[minimumI][minimumJ];
        minimumCost = Integer.MAX_VALUE;
	}
return overallCost;
}
    
    private static boolean allNodesExplored(){
        return (spanningTree.size() == numberOfNodes);
    }
    
    private static boolean isNodeExplored(int index){   
        return (spanningTree.get(index) != null);
    }
    private static void input() {
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("edges.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            StringTokenizer tokens = new StringTokenizer(line);
            numberOfNodes = Integer.parseInt(tokens.nextToken());
            graph = new int[numberOfNodes][numberOfNodes];
            for (int i = 0; i < numberOfNodes; i++) {
		      for(int j = 0; j < numberOfNodes; j++) {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
            while ((line = br.readLine()) != null) {
                tokens = new StringTokenizer(line); 
                String vertex1 = tokens.nextToken();
                String vertex2 = tokens.nextToken();
                String distance = tokens.nextToken();  
                int i = Integer.parseInt(vertex1) - 1;
                int j = Integer.parseInt(vertex2) - 1;
                graph[i][j] = Integer.parseInt(distance);
                graph[j][i] = Integer.parseInt(distance);  
            }
            br.close();
        } catch (Exception ex) {
                ex.printStackTrace();
        }
    }
}


