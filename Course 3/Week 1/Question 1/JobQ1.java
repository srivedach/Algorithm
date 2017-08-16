import java.util.*;
import java.io.*;
class JobQ1
{
    private int weight;
    private int length;
        
    public JobQ1(int weight, int length){
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
    
    public int jobRangeValue(){
        return weight - length;
    }
 }