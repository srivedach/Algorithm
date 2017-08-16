import java.util.*;
import java.io.*;

class QuickSortImplementation {
	public static int[] inputArray = new int[10000];
	public static void main(String args[]) {
		readInput();
		int count = quickSort(inputArray,0,inputArray.length-1);
		System.out.println(count);
	}
	public static void readInput() {
		FileReader fr = null;
		try {
			fr = new FileReader("C:\\Users\\sriv\\Desktop\\Algorithms\\Course - 1\\week - 3\\QuickSort.txt");
			Scanner sc = new Scanner(fr);
			while(sc.hasNextInt()) {
				for(int i = 0;i<inputArray.length;i++) {
					inputArray[i] = sc.nextInt();
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static int quickSort(int[] inputArray, int start, int end) {
		if(start >= end) 
			return 0;
			//int pindex = partition(inputArray,start,end);
			int comparisons = end - start;
			// Question 1
			int p = partition(inputArray, start, end);
			// Question 2
			//int p = partition2(inputArray, start, end);
			// Question 3
			//int p = partition3(inputArray, start, end);
			comparisons += quickSort(inputArray,start,p-1);
			comparisons += quickSort(inputArray,p+1,end);
			return comparisons;	
	}
	public static int partition(int[] inputArray,int start,int end) {
		int pivot = inputArray[end];
		int i = start + 1, j = start + 1;
		while(j <= end) {
			if(inputArray[j] < pivot) {
				swap(inputArray, i++, j);
			}
			j++;
		}
		swap(inputArray, start, i - 1);
		return i - 1;
	}
	public static void swap(int[] inputArray,int i, int j) {
		int temp = inputArray[i];
		inputArray[i] = inputArray[j];
		inputArray[j] = temp; 
	}

	public static int partition2(int[] inputArray,int start,int end) {
		swap(inputArray,start,end);
		return partition(inputArray,start,end);
	}
	public static int partition3(int[] inputArray,int start,int end) {
		medianOfThree(inputArray,start,end);
		return partition(inputArray,start,end);

	}
	public static void medianOfThree(int[] inputArray, int start, int end) {
		int length = end - start + 1;
		int mid = start + (end - start) / 2;
		if(inputArray[end] < inputArray[start]) 
			swap(inputArray,start,end);
		if(inputArray[mid] < inputArray[start])
			swap(inputArray, mid, start);
		if(inputArray[mid] < inputArray[end])
			swap(inputArray, end, mid);
	}
}