import java.util.*;
import java.io.*;

public class MedianSample {
    public static int[] list = new int[10000];
    public static void main(String[] args) {
    calculateMedian();
    }
    private static void input() {
        String path = "C:\\Users\\sriv\\Desktop\\Algorithms\\Course - 2\\week - 3\\Median.txt";
        System.out.println("Processing input file...");
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while (sc.hasNextInt()) {
                for (int i = 0;i<10000;i++) {
                    list[i] = sc.nextInt();
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void calculateMedian() {
        long sum = 0;
        input();
        for (int i = 0; i < list.length; i++) {
            int v = list[i];
            Arrays.sort(list, 0, i+1);
            sum+=list[i/2];
        }
        System.out.println(sum%10000);
        
    }
}