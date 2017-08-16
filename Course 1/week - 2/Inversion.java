import java.util.*;
import java.io.*;

class Inversion {
	public static ArrayList<Long> intArray = new ArrayList<Long> ();
	public static void main(String args[]) {
		readInput();
		long count = bruteForce();
		System.out.println(count);
	}
	public static void readInput() {
		FileReader fr = null;
		try {
			fr = new FileReader("C:\\Users\\sriv\\Desktop\\Algorithms\\Course - 1\\IntegerArray.txt");
			Scanner sc = new Scanner(fr);
			while(sc.hasNextLong()) {
				intArray.add(sc.nextLong());
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static long bruteForce() {
		long count = 0;
		for (int i = 0;i<intArray.size();i++) {
			for (int j = i+1;j<intArray.size();j++) {
				if(intArray.get(j) < intArray.get(i))
					count++;
			}
		}
		return count;
	}
	
}