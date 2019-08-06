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
 * ����һ������1,000,000����¼���ı��ļ�������ÿ����¼��16���ֽ���ɡ�
 * ��¼��block�Ϸ�װʱ������non-spanned��ʽ��������С��һ����¼�Ŀռ䲻ʹ�á�
 * ���ڴ����1M�ֽڵĿռ������ⲿmerge-sort�� 
 *  
 * 
 */  
public class MergeSort1 {  
    private int size = 1000000;//�ܼ�¼��10000000  
    private int sizePerBlock = 256;//���ڴ�����ÿ��block��С��4KB,ÿ����¼��СΪ16B,����ÿ��block�ϼ�¼����Ϊ40  
    private int sizePerMemory = 65000;//����50M�ڴ�����ڴ�����,ÿ����¼��С100B�����Դ��ÿ������50W����¼,����ȡ������500000  
//    private int fileSize = size/sizePerMemory;//�鲢���ɵ�С�ļ���   16
    private int fileSize = 16;
    private int charBufferSizeOfReader = 4096; //�ڶ��׶ε�������ÿ�����б�ʹ��һ��block��С�Ļ�����  
    private int charBufferSizeOfWriter = 1048576; //�ڶ��׶ε����������ʹ�õĻ�������С1M  
     
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
                // ���ļ���. ·�������Ǹ�txt�ļ�·��
                in[i]  = new BufferedReader(new FileReader(new File("data"+i1+".txt")),charBufferSizeOfReader);
                 String str = null;
                 int index = 0;
                 // ���ж�ȡ
                 while((str=in[i].readLine())!=null){
                 // ��\t�ָ�
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
              
            int firstFalse = 0;//�ҵ���һ��û��д����ļ�����ֵ  
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
                out.write(new char[10]);//���10��byte  
//                out.newLine();//ռ����byte  
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
        System.out.println("�ⲿ�����ʱ :"+(end - start)+"ms");    
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
        System.out.println("�ָ��ڴ�飬��ʱ"+time+"ms");
        
        QuickSort qs = new QuickSort();
        qs.main(args);
        
        ms.mergeSort();  
          
  
    }  
}  
