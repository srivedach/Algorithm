

import java.util.ArrayList;

public class Graph {
	private Vertex[] vertexs;
	
	public Graph(int size) {
		vertexs = new Vertex[size];
	}
	
	public void addVertex(int data) {
		vertexs[data - 1] = new Vertex(data);
	}
	
	public void addEdges(int index, ArrayList<Integer> edges) {
		vertexs[index].addEdges(edges);
	}
	
	public Vertex[] getVertexs() {
		return vertexs;
	}
	
	public void print() {
		for(Vertex vertex : vertexs) {
			System.out.print(vertex.getData() + "\t");
			Edge edge = vertex.getFirstArc();
			while(edge != null) {
				System.out.print(edge.getToVertex() + "\t");
				edge = edge.getNextArc();
			}
			System.out.println();
		}
	}
}
