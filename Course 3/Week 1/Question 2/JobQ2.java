import java.util.*;
import java.io.*;
class JobQ2
{
    private int weight;
    private int length;
        
    public JobQ2(int weight, int length){
        super();
        this.weight = weight;
        this.length = length;
    }
        
    public int getWeight(){
        return weight;
    
 }       
    public int getLength(){
        return length;
    }
    
    public float jobRangeValue(){
        return (float)(weight/length);
    }
 }