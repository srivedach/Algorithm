class Edge {
    private int headNode;
    private int tailNode;
    private int length; 
    public Edge(int headNode, int tailNode, int length)
    {
        this.headNode = headNode;
        this.tailNode = tailNode;
        this.length = length;
    }
    public int getHeadNode() {
        return headNode;
    }
    public void setHeadNode(int headNode) {
        this.headNode = headNode;
    }

    public int getTailNode() {
        return tailNode;
    }
    public void setTailNode(int tailNode) {
        this.tailNode = tailNode;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
}