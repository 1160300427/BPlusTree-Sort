package sort;
import java.text.DecimalFormat;  
import java.util.Random;  
  
public class Record {  
    private int A;  
    private String B;  
    private String C;  
      
    @Override  
    public String toString() {  
    	return A+"\t"+B+"\r\n";
    }  
     
    public Record() {  
        super();  
    }  
  
    public Record(String line) {  
        super();  
        String [] t = line.split("\t");  
        this.A = Integer.valueOf(t[0]);  
        this.B = t[1];  
       // this.C = t[2];  
    }  
  
      
    public int getA() {  
        return A;  
    }  
  
  
    public void setA(int a) {  
        A = a;  
    }  
  
  
    public String getB() {  
        return B;  
    }  
  
  
    public void setB(String b) {  
        B = b;  
    }  
  
  
      
}  