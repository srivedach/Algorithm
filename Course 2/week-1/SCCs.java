
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class SCCs {
	private static Graph graph;		// hold input graph
	private static Graph graphR;	// hold reverse graph
	private static int t;			// # of nodes processed so far
	private static Vertex s;		// for leaders in 2nd process
	private static Stack<Vertex> stack;	// for finish time
	
	private static void input() {
		System.out.println("Processing input file...");
		String path = "C:\\Users\\sriv\\Desktop\\Algorithms\\Course - 2\\week-1\\SCC.txt";
		BufferedReader br = null;
		String line = null;
		graph = new Graph();
		try {
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				String[] nodes = line.split(" ");
				int from = Integer.parseInt(nodes[0]);
				int to = Integer.parseInt(nodes[1]);
				while(from > graph.size()) {	// need create new vertex
					int data = graph.size() + 1;
					graph.addVertex(data);
				}
				graph.addEdge(from, to);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void generateReverseGraph() {
		System.out.println("Generate reverse graph...");
		graphR = new Graph();
		for(int i = 1; i <= graph.size(); i++) {
			graphR.addVertex(i);
		}
		for(Vertex v : graph.getVertices()) {
			Edge e = v.getFirstArc();
			while(e != null) {
				graphR.addEdge(e.getToVertex(), e.getFromVertex());	// add reverse edge
				e = e.getNextArc();
			}
		}
	}
	
	private static void firstDFSLoop() {
		System.out.println("First DFS-Loop...");
		t = 0;
		stack = new Stack<Vertex>();
		for(int id = graphR.size(); id >= 1; id--) {
			
			Vertex vertex = graphR.getVertex(id);
			if(!vertex.isExplored()) {
				firstDFSProcess(vertex);
			}
		}
	}
	
	private static void firstDFSProcess(Vertex vertex) {
		vertex.setExplored();
		Edge edge = vertex.getFirstArc();
		while(edge != null) {
			Vertex toVertex = graphR.getVertex(edge.getToVertex());
			if(!toVertex.isExplored()) {
				firstDFSProcess(toVertex);
			}
			edge = edge.getNextArc();
		}
		t++;
		Vertex v = graph.getVertex(vertex.getId());
		stack.push(v);
	}
	
	private static void secondDFSLoop() {
		System.out.println("Second DFS-Loop...");
		s = null;
		while(!stack.isEmpty()) {
			Vertex vertex = stack.pop();
			s = vertex;
			if(!vertex.isExplored()) {
				secondDFSProcess(vertex);
			}
		}
	}
	
	private static void secondDFSProcess(Vertex vertex) {
		vertex.setExplored();
		vertex.setLeader(s);
		Edge edge = vertex.getFirstArc();
		while(edge != null) {
			Vertex toVertex = graph.getVertex(edge.getToVertex());
			if(!toVertex.isExplored()) {
				secondDFSProcess(toVertex);
			}
			edge = edge.getNextArc();
		}
	}
	
	private static int[] SCC() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(Vertex vertex : graph.getVertices()) {
			int leader = vertex.getLeaderId();
			if(map.containsKey(leader)) {
				map.put(leader, map.get(leader) + 1);
			}
			else {
				map.put(leader, 1);
			}
		}
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 > o2) return -1;
				else if(o1 < o2) return 1;
				else return 0;
			}
		});
		
		for(int v : map.values()) {
			heap.add(v);
		}
		int[] result = new int[5];
		int i = 0;
		while(!heap.isEmpty() && i < result.length) {
			int v = heap.poll();
			result[i++] = v;
		}
		return result;
	}
	
	private static void print(int[] result) {
		for(int i = 0; i < result.length; i++) {
			System.out.print(result[i]);
			if(i < result.length - 1)
				System.out.print(",");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		input();
		generateReverseGraph();
		firstDFSLoop();
		secondDFSLoop();
		print(SCC());
	}
}
