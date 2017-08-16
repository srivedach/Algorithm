class Node
{
    private int nodeIndex;
    private int position; 
    
    public Node(int nodeIndex, int position){
        super();
        this.nodeIndex = nodeIndex;
        this.position = position;
    }
    
    public int getNodeIndex(){
        return nodeIndex;
    }
    
    public void setNodeIndex(int nodeIndex){
        this.nodeIndex = nodeIndex;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}