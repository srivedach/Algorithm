import java.io.*;
import java.util.*;


class JobsComparatorQ1 implements Comparator<JobQ1> {
    public int compare(JobQ1 job1, JobQ1 job2) 
    {
        int result;
        
        if(job1.getWeight() - job1.getLength() == job2.getWeight() - job2.getLength()){
            result = job1.getWeight() - job2.getWeight();
        }
        else{
            result = (job1.getWeight() - job1.getLength()) - (job2.getWeight() - job2.getLength());
        }
        
        return result * (-1);
    }
}