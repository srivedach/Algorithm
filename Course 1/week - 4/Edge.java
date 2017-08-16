
public class Edge {
	private int from;
	private int to;
	private Edge nextArc;
	private boolean active;
	
	public Edge(int from, int to) {
		this.from = from;
		this.to = to;
		active = true;
	}
	
	public int getFromVertex() {
		return from;
	}
	
	public int getToVertex() {
		return to;
	}
	
	public void setFromVertex(int from) {
		this.from = from;
	}
	
	public void setToVertex(int to) {
		this.to = to;
	}
	
	public Edge getNextArc() {
		return nextArc;
	}
	
	public void setNextArc(Edge arc) {
		nextArc = arc;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void deactivate() {
		active = false;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", active=" + active + "]";
	}
}
