
public class UnionFind
{
    private int[] id;
    private int count;
    public UnionFind(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }
    public int count() {
        return count;
    }
    public int find(int p) {
        int parent = p;
        while(parent != id[parent]){
            parent = id[parent];
        }
        while (p != parent) {
            int newp = id[p];
            id[p] = parent;
            p = newp;
        }
        return parent;
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j){
            return;
        }
        id[i] = j;
        count--;
    }
}