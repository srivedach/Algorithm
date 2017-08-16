import java.io.*;
import java.util.*;
public class Question2 {
    private static int numberOfBits;
    private static int numberOfNodes;
    public static void main(String[] args) {
        String[] nodesArray = input();
        HashMap<String, Integer> auxNodesMap = new HashMap<String, Integer>();
        for (int i = 0; i < nodesArray.length; i++) {
            auxNodesMap.put(nodesArray[i], i);
        }
        numberOfNodes = auxNodesMap.size();
        int value = 0;
        HashMap<String, Integer> nodesMap = new HashMap<String, Integer>();
        for(Iterator<String> it = auxNodesMap.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            nodesMap.put(key, value);
            value++;
        }
        UnionFind unionFind = new UnionFind(numberOfNodes);
        for(String key : nodesMap.keySet()) {
            String[] closeNodes = getCloseNodes(key);
            for(int j = 0; j < closeNodes.length; j++) {
                String k = closeNodes[j];
                if(nodesMap.containsKey(k)) {
                    unionFind.union(nodesMap.get(key), nodesMap.get(k));
                }
            }
        }
        System.out.println(unionFind.count());
    }
    private static String[] getCloseNodes(String node) {
        int[] nodeBinary = getBinaryIntArrayFromString(node);
        String[] output = new String[numberOfBits + (numberOfBits * (numberOfBits - 1)) / 2];
        int count = 0;
        for(int i = 0; i < numberOfBits; i++) {
            for(int j = i; j < numberOfBits; j++) {
                int[] newNodeBinary = nodeBinary.clone();
                if(i != j) {
                    newNodeBinary[i] = (nodeBinary[i] + 1) % 2;
                    newNodeBinary[j] = (nodeBinary[j] + 1) % 2;
                } 
                else {
                    newNodeBinary[i] = (nodeBinary[i] + 1) % 2;
                }
                output[count] = getStringFromBinaryIntArray(newNodeBinary);
                count++;
            }
        }
        return output;
    }
    public static int[] getBinaryIntArrayFromString(String node) {
        int[] nodeBinary = new int[numberOfBits];
        int count = 0;
        for(int i = 0; i < node.length(); i++) {
            if(node.charAt(i) != ' ') {
                nodeBinary[count] = node.charAt(i) - '0';
                count++;
            }
        }
        return nodeBinary;
    }
    public static String getStringFromBinaryIntArray(int[] binArray) {
        StringBuilder sb = new StringBuilder((numberOfBits * 2) - 1);
        for(int i = 0; i < numberOfBits; i++) {
            sb.append(binArray[i]);
            if(i < numberOfBits - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    private static String[] input() {
        String[] nodesArray = new String[100];
        try {
            FileInputStream file = new FileInputStream("clustering_big.txt");
            DataInputStream dis = new DataInputStream(file);
            BufferedReader br =  new BufferedReader(new InputStreamReader(dis));
            String str = br.readLine();
            numberOfNodes = Integer.parseInt(str.split(" ")[0]);
            nodesArray = new String[numberOfNodes];
            numberOfBits = Integer.parseInt(str.split(" ")[1]);
            int index = 0;
            while((str=br.readLine())!=null) {
                nodesArray[index] = str.trim();
                index++;
            }
            br.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
       return nodesArray;
    }
}
