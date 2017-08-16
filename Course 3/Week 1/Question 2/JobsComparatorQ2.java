import java.io.*;
import java.util.*;


class JobsComparatorQ2 implements Comparator<JobQ2> {
    public int compare(JobQ2 job1, JobQ2 job2) 
    {
         int result = 0;

        if(job1.jobRangeValue() < job2.jobRangeValue()){
            result = 1;
        }
        else if(job1.jobRangeValue() > job2.jobRangeValue()){
            result = -1;
        }
        
        return result * (-1);
    }
}