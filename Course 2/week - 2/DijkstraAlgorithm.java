
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DijkstraAlgorithm {
	private static Graph graph;		// hold input graph
	private static int[] targets = {7,37,59,82,99,115,133,165,188,197}; 
	
	private static void input() {
		String path = "C:\\Users\\sriv\\Desktop\\Algorithms\\Course - 2\\week - 2\\dijkstraData.txt";
		BufferedReader br = null;
		String line = null;
		graph = new Graph();
		try {
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				String[] nodes = line.split("\t");
				int from = Integer.parseInt(nodes[0]);
				
				while(from > graph.size()) {	// need create new vertex
					int data = graph.size() + 1;
					graph.addVertex(data);
				}
				
				for(int i = 1; i < nodes.length; i++) {
					String[] tuples = nodes[i].split(",");
					int to = Integer.parseInt(tuples[0]);
					int length = Integer.parseInt(tuples[1]);
					graph.addEdge(from, to, length);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void updateNeighbours(Vertex vert, int[] dist) {
		Edge edge = vert.getFirstArc();
		while(edge != null) {
			Vertex toVert = graph.getVertex(edge.getToVertex());
			int newDist = dist[vert.getIndex()] + edge.getLength();
			if(newDist < dist[toVert.getIndex()]) {
				dist[toVert.getIndex()] = newDist;	// update
			}
			edge = edge.getNextArc();
		}
	}
	
	private static Vertex minVertex(int[] dist) {
		int min = Graph.MAX_DISTANCE + 1, minId = -1;
		for(int id = 1; id <= dist.length; id++) {
			Vertex v = graph.getVertex(id);
			if(v.isCovered())
				continue;
			if(dist[id - 1] < min) {
				min = dist[id - 1];
				minId = id;
			}
		}
		return graph.getVertex(minId);
	}
	
	private static int[] getResult(int[] dist) {
		int[] result = new int[targets.length];
		for(int i = 0; i < targets.length; i++) {
			int id = targets[i];
			result[i] = dist[id - 1];
		}
		return result;
	}
	
	private static int[] dijkstra() {
		System.out.println("Running Dijkstra algorithm...");
		// initial
		Vertex source = graph.getVertex(1);
		source.setCovered();
		int[] dist = new int[graph.size()];
		for(int i = 1; i < dist.length; i++)
			dist[i] = Graph.MAX_DISTANCE;
		int covered = 1;
		Vertex last = source;	// record last added vertex
		while(covered < graph.size()) {
			updateNeighbours(last, dist);
			Vertex min = minVertex(dist);
			min.setCovered();
			last = min;
			covered++;
		}
		return getResult(dist);
	}
	
	private static void updateNeighbours(Vertex vert, Heap heap) {
		Edge edge = vert.getFirstArc();
		while(edge != null) {
			Vertex toVert = graph.getVertex(edge.getToVertex());
			if(toVert.isCovered()) {
				edge = edge.getNextArc();
				continue;
			}
			int newDist = vert.getDist() + edge.getLength();
			if(newDist < toVert.getDist())
				toVert.setDist(newDist);
			heap.insert(toVert);
			edge = edge.getNextArc();
		}
	}
	
	private static int[] dijkstraWithHeap() {
		System.out.println("Running Dijkstra algorithm with heap...");
		// initial
		Vertex source = graph.getVertex(1);
		source.setDist(0);
		Heap heap = new Heap(graph.size());
		heap.insert(source);
		int covered = 1;
		int[] dist = new int[graph.size()];
		for(int i = 1; i < dist.length; i++)
			dist[i] = Graph.MAX_DISTANCE;
		while(covered < graph.size()) {
			Vertex min = heap.extractMin();
			dist[min.getIndex()] = min.getDist();
			min.setCovered();
			updateNeighbours(min, heap);
			covered++;
		}
		return getResult(dist);
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
		// graph.print();
		 print(dijkstra());
		//print(dijkstraWithHeap());
	}
}

