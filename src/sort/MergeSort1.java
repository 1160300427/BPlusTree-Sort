package sort;

import java.io.BufferedReader;  
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;  
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;  
import java.util.Comparator;  
import java.util.Date;  
  
/** 
 * 生成一个具有1,000,000个记录的文本文件，其中每个记录由16个字节组成。
 * 记录在block上封装时，采用non-spanned方式，即块上小于一个记录的空间不使用。
 * 在内存分配1M字节的空间用于外部merge-sort。 
 *  
 * 
 */  
public class MergeSort1 {  
    private int size = 1000000;//总记录数10000000  
    private int sizePerBlock = 256;//由于磁盘上每个block大小是4KB,每条记录大小为16B,所以每个block上记录条数为40  
    private int sizePerMemory = 65000;//分配50M内存进行内存排序,每个记录大小100B，所以大概每次排序50W条记录,这里取个整数500000  
//    private int fileSize = size/sizePerMemory;//归并生成的小文件数   16
    private int fileSize = 16;
    private int charBufferSizeOfReader = 4096; //第二阶段的排序中每个子列表使用一个block大小的缓冲区  
    private int charBufferSizeOfWriter = 1048576; //第二阶段的排序中输出使用的缓冲区大小1M  
     
    private String sortedRecordFile ="datasort.txt";  
      

      
    public BufferedReader in[] = new BufferedReader[fileSize];  
    public String[][] linestr = new String[fileSize][62500];
    public void mergeSort() throws Exception{  
        long start = new Date().getTime();  
        BufferedWriter out = new BufferedWriter(new FileWriter(sortedRecordFile),charBufferSizeOfWriter);         
        Record rs[] = new Record[fileSize];       
        Boolean finish [] = new Boolean[fileSize]; 
        String[][] rows = new String[62500][2];
        for(int i =0;i<fileSize;i++){  
        	int i1 = i+1;    
            try {
            	File writeName1 = new File("data"+i1+".txt"); 
                writeName1.createNewFile(); 
                // 读文件了. 路径就是那个txt文件路径
                in[i]  = new BufferedReader(new FileReader(new File("data"+i1+".txt")),charBufferSizeOfReader);
                 String str = null;
                 int index = 0;
                 // 按行读取
                 while((str=in[i].readLine())!=null){
                 // 用\t分隔
                     rows[index] = str.split("\t");
                     linestr[i][index] = str;
                     index++;
                 }
                                               
             } catch (FileNotFoundException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
        }
        
        int lineindex = 0;
        for(int i =0;i<fileSize;i++) {  
        	String str = linestr[i][lineindex];
        	if(str!=null){
                rs[i]=new Record(linestr[i][lineindex]);  
        	}
        	
        	 finish[i]= false;  
        	
        } 
        
       
        Record min;  
        String line;  
        int finishCount = 0;  
        int count = 0;  
        while(count <= size){  
              
            int firstFalse = 0;//找到第一个没有写完的文件序列值  
            for(int i=0;i<fileSize;i++){  
                if(finish[i]==true)  
                    firstFalse =i+1;  
                else  
                    break;  
            }  
            if(firstFalse>=fileSize) break;  
            if(finishCount>=fileSize) break;  
            min = rs[firstFalse];  
            int j =firstFalse;  
              
            for(int i =firstFalse+1;i<fileSize;i++){  
                if(!finish[i]&&(rs[i].getA()<min.getA())){  
                    min = rs[i];  
                    j = i;  
                }  
            }  
            if((count!=0)&&(count%sizePerBlock==0)){  
                out.write(new char[10]);//填充10个byte  
//                out.newLine();//占两个byte  
            } 
            if(count >0 )
            	out.write(min.toString());  
//            System.out.println(min.toString());
  //          out.newLine();  
              
              
            if(!finish[j]){  
                line = linestr[j][lineindex];  
                if(line!=null){  
                    if("".equals(line.trim()))  
                    {  
                        line = linestr[j][lineindex];  
                        if(line==null){  
                            finish[j] = true;  
                            finishCount++;  
                        }  
                    }else {
                        rs[j]= new Record(line);  
                    }  
                }else {  
                    finish[j] = true;  
                    finishCount++;  
                    //System.out.println("line == null");
                }  
            }  
            count++; 
            if(lineindex<62499){
            	lineindex++;
            }
            else
            	lineindex = 0;
        }  
        lineindex = 0;
        for(int i =0;i<fileSize;i++){  
            in[i].close();  
        }  
        out.close();  
          
        long end = new Date().getTime();  
        System.out.println("外部排序耗时 :"+(end - start)+"ms");    
    }  
      
    public static void main(String [] args) throws Exception{  
  
//      long start = new Date().getTime();  
    	long time1,time2,time;
        MergeSort1 ms = new MergeSort1();  
         
        CutData cut = new CutData();
        time1 = System.currentTimeMillis();
        cut.main(args);
        time2 = System.currentTimeMillis(); 
		time = time2 - time1;
        System.out.println("分割内存块，耗时"+time+"ms");
        
        QuickSort qs = new QuickSort();
        qs.main(args);
        
        ms.mergeSort();  
          
  
    }  
}  
