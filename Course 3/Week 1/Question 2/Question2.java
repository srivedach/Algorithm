import java.util.*;
import java.io.*;
class Question2 {
	public static void main(String[] args) {
		ArrayList<JobQ2> listOfJobs = input();
		Collections.sort(listOfJobs,new JobsComparatorQ2());
		long totalWeight = 0;
		long totalLength = 0;
		for(JobQ2 j:listOfJobs) {
			totalLength += j.getLength();
			totalWeight += totalLength * j.getWeight();
		}
		System.out.println("totalWeight-->"+" "+totalWeight);
	}
	private static ArrayList<JobQ2> input() {
		ArrayList<JobQ2> jobsList = new ArrayList<JobQ2> ();
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("jobs.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int index = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if(index != 0) {
                    StringTokenizer tokens = new StringTokenizer(line);
                    Integer weight = new Integer(tokens.nextToken());
                    Integer length = new Integer(tokens.nextToken());
                    jobsList.add(new JobQ2(weight.intValue(), length.intValue()));
                }
                index++;
            }
            br.close();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return jobsList;
	}
}