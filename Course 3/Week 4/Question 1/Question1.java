import java.io.*;
import java.util.*;
public class Question1 {
    private static int n = 0; 
    private static int w = 0; 
    private static ArrayList<KnapsackItem> itemsArray = new ArrayList<KnapsackItem>();
    public static void main(String[] args) {
        input();
        int[][] A = new int[n + 1][w + 1];
        for(int j = 0; j <= w; j++) {
            A[0][j] = 0;
        }
        for(int i = 1; i <= n; i++) {
            KnapsackItem currentItem = itemsArray.get(i - 1);
            for(int k = 0; k <= w; k++) {
                int firstItem = A[i - 1][k];
                int secondItem = 0;
                if(k - currentItem.getWeight() >= 0 && k - currentItem.getWeight() <= w) {
                    secondItem = A[i - 1][k - currentItem.getWeight()] + currentItem.getValue();
                }
                A[i][k] = Math.max(firstItem, secondItem);
            }
        }
        
        System.out.println(A[n][w]);
    }
    private static void input() {
        try {
            FileInputStream f = new FileInputStream("knapsack1.txt");
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