
import java.io.*;
import java.util.*;

public class RandomizedContraction {
	private static Graph graph;					// hold input graph
	private static ArrayList<Edge> edgePool;	// edges for random select
	
	public static void input() {
		String path = "C:\\Users\\sriv\\Desktop\\Algorithms\\Course - 1\\week - 4\\kargerMinCut.txt";
		BufferedReader br = null;
		String line = null;
		graph = new Graph(200);
		try {
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				String[] nodes = line.split("\t");
				int data = Integer.parseInt(nodes[0]);
				graph.addVertex(data);
				ArrayList<Integer> edges = new ArrayList<Integer>();
				for(int i = 1; i < nodes.length; i++) {
					edges.add(Integer.parseInt(nodes[i]));
				}
				graph.addEdges(data - 1, edges);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readEdges() {
		edgePool = new ArrayList<Edge>();
		Vertex[] vertexs = graph.getVertexs();
		for(Vertex vertex : vertexs) {
			Edge edge = vertex.getFirstArc();
			while(edge != null) {
				// we just need one of two directions edge
				if(edge.getFromVertex() < edge.getToVertex()) {
					edgePool.add(edge);
				}
				edge = edge.getNextArc();
			}
		}
	}
	
	public static int randomizedContraction() {
		int count = graph.getVertexs().length;
		int size = edgePool.size();
		Random seed = new Random();
		int index, from, to, v, d;
		Edge edge, e1, e2, temp;
		Vertex fromVertex, toVertex, v1, v2;
		while(count > 2) {	// contraction until left two vertices
			do {
				index = seed.nextInt(size);
				edge = edgePool.get(index);
				from = edge.getFromVertex();
				to = edge.getToVertex();
				fromVertex = graph.getVertexs()[from - 1];
				toVertex = graph.getVertexs()[to - 1];
			} while(!edge.isActive() || !fromVertex.isActive() || !toVertex.isActive());	// chose a valid edge
			edge.deactivate();	// do not chose next time
			// contract (from, to) -> from
			toVertex.deactivate();
			// process to vertex's edge
			e1 = toVertex.getFirstArc();
			while(e1 != null) {
				d = e1.getToVertex();
				v2 = graph.getVertexs()[d - 1];
				if(d == from || !e1.isActive() || !v2.isActive()) {
					e1 = e1.getNextArc();
					continue;
				}
				// set outgoing edge
				temp = e1.getNextArc();
				e1.setFromVertex(from);
				e1.setNextArc(null);
				fromVertex.addEdge(e1);
				// set incoming edge
				v = e1.getToVertex();
				v1 = graph.getVertexs()[v - 1];
				e2 = v1.getFirstArc();
				while(e2 != null) {
					if(e2.getToVertex() == to) {
						e2.setToVertex(from);
						break;
					}
					e2 = e2.getNextArc();
					//System.out.println(e2);
				}
				e1 = temp;
			}
			count--;
			//System.out.println(count);
		}
		int cut = 0, data1 = -1, data2 = -1;
		for(Vertex vertex : graph.getVertexs()) {
			if(vertex.isActive()) {
				if(data1 == -1) {
					data1 = vertex.getData();
				}
				else if(data2 == -1) {
					data2 = vertex.getData();
					break;
				}
			}
		}
		Vertex vertex1 = graph.getVertexs()[data1 - 1]; //, vertex2 = graph.getVertexs()[data2 - 1];
		edge = vertex1.getFirstArc();
		while(edge != null) {
			if(edge.getToVertex() == data2) {
				cut++;
			}
			edge = edge.getNextArc();
		}
		return cut;
	}

	public static void main(String[] args) {
		int minCut = Integer.MAX_VALUE;
		for(int i = 0; i < 200; i++) {
			input();
			// graph.print();
			readEdges();
			// System.out.println(edgePool.size());
			int cut = randomizedContraction();
			//System.out.println(cut);
			if(minCut > cut)
				minCut = cut;
		}
		System.out.println(minCut);
	}
}