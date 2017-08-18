import java.io.*;
import java.util.*;
public class Question2 {
    private static int n = 0;
    private static int w = 0;
    private static ArrayList<KnapsackItem> itemsArray = new ArrayList<KnapsackItem>();
    private static HashMap<String, Integer> cache = new HashMap<String, Integer>();
    public static void main(String[] args) {
        input();
        System.out.println(processKnapsack(itemsArray.size() - 1, w));
    }
    private static int processKnapsack(int i, int w) {
        int solution = getSolutionFromCache(i, w);
        if(solution == -1) {
            if(i < 0) {
                return 0;
            }
            KnapsackItem currentItem = itemsArray.get(i);
            if(currentItem.getWeight() > w) {
                solution = processKnapsack(i - 1, w);
            }
            else{
                solution = Math.max(processKnapsack(i - 1, w), processKnapsack(i - 1, w - currentItem.getWeight()) + currentItem.getValue());   
            }
            saveSolutionInChache(i, w, solution);
        }   
            
        return solution;
    }
    private static void saveSolutionInChache(int i, int w, int solution) {
        cache.put("Item" + i + ":" + w, solution);
    }
    private static int getSolutionFromCache(int i, int w) {
        int cachedSolution = -1;
        String key = "Item" + i + ":" + w;
        if(cache.containsKey(key)){
            cachedSolution = cache.get(key);
        }
        return cachedSolution;
    }
    private static void input() {
        try {
            FileInputStream f = new FileInputStream("knapsack_big.txt");
            DataInputStream d = new DataInputStream(f);
            BufferedReader b =  new BufferedReader(new InputStreamReader(d));
            StringTokenizer tokenizer = new StringTokenizer(b.readLine());
            w = Integer.parseInt(tokenizer.nextToken());
            n = Integer.parseInt(tokenizer.nextToken());
            int value;
            int weight;
            String str;
            while((str=b.readLine())!=null) {
                tokenizer = new StringTokenizer(str);
                value = Integer.parseInt(tokenizer.nextToken());
                weight = Integer.parseInt(tokenizer.nextToken());
                itemsArray.add(new KnapsackItem(value, weight));
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        } 
    }
}