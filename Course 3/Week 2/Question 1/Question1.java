import java.util.*;
import java.io.*;
public class Question1
{
    private static int k = 4;
    private static int numberOfEdges = 0;
    private static int[] parents;
    public static void main(String[] args) {
        List<Edge> edgesArray = input();
        Collections.sort(edgesArray);
        UnionFind unionFind = new UnionFind(numberOfEdges);
        for(Edge e : edgesArray) {
            unionFind.union(e.getI(), e.getJ());
            if(unionFind.count() == k) {
                break;				
            }
        }
        int max = Integer.MAX_VALUE;
		for(Edge e : edgesArray) {
            if(unionFind.find(e.getI()) != unionFind.find(e.getJ())){
                max = Math.min(max, e.getCost());				
            }
	   }
	 System.out.println(max);
    }
    
    private static ArrayList<Edge> input() {
        ArrayList<Edge> edgesArray = new ArrayList<Edge> ();
        try {
            FileInputStream file = new FileInputStream("clustering1.txt");
            DataInputStream dis = new DataInputStream(file);
            BufferedReader br =  new BufferedReader(new InputStreamReader(dis));
            numberOfEdges = Integer.parseInt(br.readLine());
            edgesArray = new ArrayList<Edge>(numberOfEdges);
            parents = new int[numberOfEdges];			
            for(int i = 0; i < numberOfEdges; i++) {
                parents[i] = -1;				
            }
            String str; 
            StringTokenizer tokenizer;
            int i, j, v;
            while((str=br.readLine())!=null) {
                tokenizer = new StringTokenizer(str);
                i = Integer.parseInt(tokenizer.nextToken());
                j = Integer.parseInt(tokenizer.nextToken());
                v = Integer.parseInt(tokenizer.nextToken());
                edgesArray.add(new Edge(i - 1, j - 1, v));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        } 
        return edgesArray;
    }
}	
