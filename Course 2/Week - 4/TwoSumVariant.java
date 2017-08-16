
import java.io.*;
import java.util.*;

public class TwoSumVariant {
	private static ArrayList<Long> numbers;
	private final static int DefaultSize = 1000000;

	private static void input() {
		String path = "C:\\Users\\sriv\\Desktop\\Algorithms\\Course - 2\\week - 4\\2sum.txt";
		System.out.println("Processing input file...");
		try {
			numbers = new ArrayList<Long>(DefaultSize);
			File file = new File(path);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLong())
				numbers.add(sc.nextLong());
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static int twoSumVariant() {
		Collections.sort(numbers);
		int count = 0;
		for (long target = -10000; target <= 10000; target++) {
			int first = 0, last = numbers.size() - 1;
			while (first < last) {
				long sum = numbers.get(first) + numbers.get(last);
				if (sum == target) {
					if (numbers.get(first) != numbers.get(last))
						count++;
					break;
				} else if (sum < target) {
					first++;
				} else {
					last--;
				}
			}
		}
		return count;
	}
	public static void main(String[] args) {
		input();
		System.out.println(twoSumVariant());
	}
}